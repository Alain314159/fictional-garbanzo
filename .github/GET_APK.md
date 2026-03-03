# 🚀 ¡OBTENER APK FÁCIL!

## 🐷 Cerdita - Build Automático de APK

---

## 📱 ¿Cómo obtener el APK?

### Opción 1: Desde GitHub Actions (RECOMENDADO)

1. **Ve a la pestaña "Actions"** en GitHub
   ```
   https://github.com/TU_USUARIO/cerdita/actions
   ```

2. **Click en el workflow más reciente** (el de arriba)
   - Se llama: "🚀 BUILD APK - ¡OBLIGATORIO!"

3. **Baja hasta "Artifacts"**

4. **Click en "🐷 CERDITA-APK-xxxxx"**

5. **¡Se descarga el APK!** 🎉

6. **Instala en tu Android:**
   - Conecta tu teléfono o envía el APK
   - Activa "Instalar apps desconocidas"
   - Instala y ¡listo!

---

### Opción 2: Desde Releases

1. **Ve a la pestaña "Releases"** en GitHub
   ```
   https://github.com/TU_USUARIO/cerdita/releases
   ```

2. **Click en el release más reciente**

3. **En "Assets", click en "app-debug.apk"**

4. **¡Se descarga el APK!** 🎉

---

## ⚡ ¿Cuándo se genera el APK?

**¡AUTOMÁTICAMENTE!** en cada:

| Evento | ¿Genera APK? |
|--------|--------------|
| ✅ Push a CUALQUIER branch | ✅ SÍ |
| ✅ Pull Request | ✅ SÍ |
| ✅ Push a `main` | ✅ SÍ + Release automático |
| ✅ Ejecución manual | ✅ SÍ |

---

## 🎯 Ejecución Manual

Si quieres el APK **YA** sin hacer push:

1. Ve a **Actions** → "🚀 BUILD APK - ¡OBLIGATORIO!"
2. Click en **"Run workflow"**
3. Selecciona el branch
4. Click en **"Run workflow"**
5. Espera 3-5 minutos
6. Descarga el APK desde artifacts

---

## 📊 Información del Build

Cada APK incluye:

```
🐷 App: Cerdita
📅 Fecha: 2026-03-03 12:00:00
🌿 Branch: main
💻 Commit: abc1234
📝 Mensaje: Tu commit message
👤 Autor: Tu nombre
```

---

## 🔧 Solución de Problemas

### ❌ El workflow falla

**Revisa:**
1. Click en el workflow fallido
2. Click en el job "🏗️ GENERAR APK"
3. Lee los logs para ver el error
4. Corrige el código
5. Haz push de nuevo (se ejecuta solo)

### ❌ No encuentro el APK

**Verifica:**
1. ¿El workflow completó con ✅?
2. Baja hasta la sección "Artifacts"
3. Si no aparece, el workflow falló

### ❌ No puedo instalar el APK

**En tu Android:**
1. Ve a **Ajustes** → **Seguridad**
2. Activa **"Orígenes desconocidos"** o **"Instalar apps desconocidas"**
3. Intenta instalar de nuevo

---

## 📱 Requisitos del APK

| Requisito | Valor |
|-----------|-------|
| **Android mínimo** | 8.0 (API 26) |
| **Android recomendado** | 10+ |
| **Espacio necesario** | ~50 MB |
| **Tipo** | Debug (para testing) |

---

## 🎯 Flujo Rápido

```
1. Haces commit → git commit -m "algo"
2. Haces push → git push
3. Workflow se ejecuta SOLO
4. Esperas 3-5 minutos
5. Vas a Actions
6. Descargas APK
7. Instalas en tu teléfono
8. ¡LISTO! 🎉
```

---

## ⏱️ Tiempo de Build

| Etapa | Tiempo |
|-------|--------|
| Checkout | 30 segundos |
| Setup JDK | 30 segundos |
| Download dependencias | 1-2 minutos |
| Build APK | 2-3 minutos |
| Upload artifact | 30 segundos |
| **TOTAL** | **~5 minutos** |

---

## 💡 Tips

1. **El APK expira a los 30 días** - Descárgalo pronto
2. **Cada push genera un APK nuevo** - Usa el más reciente
3. **El nombre incluye el commit** - Sabrás cuál es cuál
4. **Push a `main` crea release** - Más fácil de encontrar
5. **Puedes ejecutar manualmente** - Sin hacer push

---

## 🔗 Enlaces Rápidos

| Enlace | ¿Qué es? |
|--------|----------|
| [Actions](../../actions) | Ver builds |
| [Releases](../../releases) | Releases automáticos |
| [Workflow](../../blob/main/.github/workflows/build-apk-always.yml) | Ver configuración |

---

## 🎉 ¡Eso es todo!

**Cada vez que hagas push, tendrás tu APK listo en 5 minutos.**

¡No tienes que hacer nada manual! 🚀

---

**¡Disfruta Cerdita!** 🐷💕🐨
