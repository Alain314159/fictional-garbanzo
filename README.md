# Juego "¿Me amas?" — temática crochet

Pequeño juego para móvil con estética y elementos de crochet. El repositorio contiene un único archivo `index.html` que incluye HTML, CSS y JS embebidos para facilidad de despliegue.

Características principales
- Interfaz dividida 70% escena / 30% controles (joystick + botón OK).
- Botón verde `Sí` con animación sensual; al tocarlo aparece el mensaje: "wiii yo tambien te amo cerdita" acompañado de un icono de hilo.
- Botón rojo `No` nunca puede ser tocado directamente: huye al acercarse y lanza agujas/ganchillos (efecto visual).
- Estética crochet: textura tipo hilo, colores pastel, agujas/ganchillos estilizados.

Archivos
- `index.html`: archivo único que contiene todo (HTML + CSS + JS).
- `test.js`: pruebas headless usando `jsdom` para validar comportamiento básico (modal y creación de agujas).
- `package.json`: script `npm test` que ejecuta `node test.js`.

Cómo probar localmente
1. Instala dependencias (solo para tests):

```bash
cd /workspaces/fictional-garbanzo
npm install
```

2. Ejecutar pruebas headless:

```bash
npm test
```

3. Probar en un móvil (recomendado): levantar servidor estático y abrir desde tu Android

```bash
python3 -m http.server 8000
# en el móvil abrir: http://<tu-ip-local>:8000
```

Notas de diseño y accesibilidad
- El botón `No` tiene `pointer-events: none` para evitar ser tocado directamente, como pide el juego.
- Se han añadido indicadores visuales (emoji de hilo) y texturas para reforzar la temática crochet.

¿Quieres ajustes?
- Puedo afinar la sensibilidad del joystick, cambiar paleta de colores, o generar una versión con assets (SVGs de ganchillos) si deseas mayor detalle visual.
# Mejoras visuales añadidas en esta versión
- SVGs embebidos para bola de hilo, aguja y ganchillo (sin recursos externos).
- Agujas lanzadas renderizadas como SVG para mejor nitidez en móviles.
- Estética reforzada: texturas tipo hilo, sombras y animaciones en botones.
# fictional-garbanzo