const fs = require('fs');
const { JSDOM } = require('jsdom');

async function run(){
  const html = fs.readFileSync('index.html','utf8');
  // extraer script embebido del index.html
  const scriptMatch = html.match(/<script[^>]*>([\s\S]*?)<\/script>/);
  const scriptSrc = scriptMatch ? scriptMatch[1] : '';

  const dom = new JSDOM(html, { runScripts: 'outside-only' });
  const { window } = dom;
  const { document } = window;

  // basic layout sizes for testing (mobile-like)
  window.innerWidth = 360; window.innerHeight = 740;

  // polyfill animate (not available in jsdom)
  window.Element.prototype.animate = function(keyframes, opts){
    const handle = {};
    const dur = (opts && opts.duration) || 600;
    setTimeout(()=>{ if(typeof handle.onfinish === 'function') handle.onfinish(); }, dur);
    return handle;
  };

  // deterministic getBoundingClientRect based on element id
  window.HTMLElement.prototype.getBoundingClientRect = function(){
    if(this.id === 'scene') return { width: 360, height: 520, left:0, top:0, right:360, bottom:520 };
    if(this.id === 'btnYes') return { width: 120, height: 80, left: 40, top: 160, right: 160, bottom:240 };
    if(this.id === 'btnNo') return { width: 100, height: 80, left: 200, top: 160, right:300, bottom:240 };
    if(this.id === 'player') return { width:40, height:40, left:180, top:380, right:220, bottom:420 };
    return { width:100, height:40, left:100, top:100, right:200, bottom:140 };
  };

  // inject script (it registers DOMContentLoaded handlers)
  if(scriptSrc) window.eval(scriptSrc);

  // trigger DOMContentLoaded so the app initializes
  document.dispatchEvent(new window.Event('DOMContentLoaded'));

  // small helper to wait
  const wait = ms => new Promise(r=>setTimeout(r,ms));

  // Test 1: click green button -> modal should appear with expected text
  const btnYes = document.getElementById('btnYes');
  if(!btnYes) throw new Error('btnYes not found');
  btnYes.click();
  await wait(100);
  const modal = document.getElementById('modal');
  const modalContent = document.getElementById('modalContent');
  if(modal.getAttribute('aria-hidden') === 'false' && /wiii yo tambien te amo cerdita/i.test(modalContent.textContent)){
    console.log('Test GREEN: ✅ modal shown with expected text');
  } else {
    console.error('Test GREEN: ❌ modal did not show or text mismatch');
    process.exitCode = 2; return;
  }

  // close modal
  const modalClose = document.getElementById('modalClose');
  modalClose.click();
  await wait(50);

  // Test 2: simular acercamiento al NO -> debería disparar agujas
  const no = document.getElementById('btnNo');
  if(!no) throw new Error('btnNo not found');
  const needlesLayer = document.getElementById('needles');
  const before = needlesLayer.querySelectorAll('.needle').length;
  // calcular punto cerca del botón NO (centro)
  const rect = no.getBoundingClientRect();
  const cx = rect.left + rect.width/2; const cy = rect.top + rect.height/2;
  // dispatch pointerdown en la escena cerca del NO para mover al jugador y disparar la reacción
  const ev = new window.PointerEvent('pointerdown', { bubbles: true, clientX: cx, clientY: cy });
  const scene = document.getElementById('scene');
  scene.dispatchEvent(ev);
  // esperar suficiente tiempo para que se mueva y se disparen las agujas
  await wait(900);
  const after = needlesLayer.querySelectorAll('.needle').length;
  if(after > before){
    console.log('Test RED: ✅ needles spawned on pointerdown');
  } else {
    console.error('Test RED: ❌ no needles were spawned');
    process.exitCode = 3; return;
  }

  console.log('All tests passed');
}

run().catch(err=>{ console.error('Error running tests:', err); process.exit(1); });
