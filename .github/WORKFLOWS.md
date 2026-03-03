# 🐷 Cerdita - GitHub Workflows

## Workflows Disponibles

### 1. 🚨 BUILD EXTREMO - DEBUG + RELEASE + LOGS ⭐ RECOMENDADO

**Archivo:** `build-extremo-logs.yml`

**Qué hace:**
- ✅ Genera **DEBUG APK** y **RELEASE APK**
- ✅ Muestra **LOGS EXTREMADAMENTE DETALLADOS** si falla
- ✅ Sube **TODO** como artifacts para debugging
- ✅ **Analiza automáticamente los fallos**
- ✅ Muestra información del sistema, archivos, dependencias
- ✅ Genera reportes completos de errores

**Cuándo se ejecuta:**
- En CADA push a cualquier branch
- En cada Pull Request
- Manualmente (workflow_dispatch)

**Artifacts que sube:**
| Artifact | Contenido |
|----------|-----------|
| 🐷 CERDITA-DEBUG-APK | APK de debug |
| 🐷 CERDITA-RELEASE-APK | APK de release (si existe) |
| 📊 BUILD-INFORMATION | Info detallada |
| 📝 FULL-BUILD-LOGS | Logs completos, reports |
| 🔍 FAILURE-ANALYSIS | Análisis de fallo (si falla) |

**Cómo obtener los APKs:**
1. Ve a **Actions** → "🚨 BUILD EXTREMO"
2. Click en el build más reciente
3. Baja a **Artifacts**
4. Click en **"🐷 CERDITA-DEBUG-APK"** (y RELEASE si existe)
5. ¡Descarga los APKs!

**Ver guía completa:** [BUILD_EXTREMO.md](BUILD_EXTREMO.md)

---

### 2. 🚀 BUILD APK - ¡OBLIGATORIO!

**Archivo:** `build-apk-always.yml`

**Qué hace:**
- ✅ Se ejecuta en CUALQUIER push (a cualquier branch)
- ✅ Compila la app automáticamente
- ✅ Sube el APK como artifact (fácil de descargar)
- ✅ Si es push a `main`, crea release automático
- ✅ Incluye información del commit en el build

**Cuándo se ejecuta:**
- En CADA push a cualquier branch
- En cada Pull Request
- Manualmente (workflow_dispatch)

**Cómo obtener el APK:**
1. Ve a **Actions** → "🚀 BUILD APK - ¡OBLIGATORIO!"
2. Click en el build más reciente
3. Baja a **Artifacts**
4. Click en **"🐷 CERDITA-APK-xxxxx"**
5. ¡Descarga el APK!

**Ver guía completa:** [GET_APK.md](GET_APK.md)

---

### 3. 🔄 CI/CD (Continuous Integration)

**Archivo:** `android-ci.yml`

**Qué hace:**
- Se ejecuta en cada push o Pull Request
- Compila la app en versión debug
- Ejecuta tests unitarios
- Verifica que el código compile correctamente
- Ejecuta Android Lint

**Cuándo se ejecuta:**
- En cada push a `main` o `develop`
- En cada Pull Request

---

### 4. 📦 Release APK

**Archivo:** `release-apk.yml`

**Qué hace:**
- Genera APK firmado para release
- Crea release en GitHub automáticamente
- Sube el APK a los assets del release

**Cuándo se ejecuta:**
- Cuando creas un release en GitHub

**Configuración necesaria:**
1. Crear keystore para firmar la app
2. Añadir secrets en GitHub:
   - `KEYSTORE` (tu keystore en base64)
   - `KEYSTORE_PASSWORD`
   - `KEY_PASSWORD`
   - `KEY_ALIAS`

---

### 5. 🔄 Actualización de Dependencias

**Archivo:** `dependency-updates.yml`

**Qué hace:**
- Revisa si hay actualizaciones de dependencias
- Crea un issue con las actualizaciones disponibles

**Cuándo se ejecuta:**
- Todos los lunes a las 9:00 AM

---

### 6. 🤖 Auto Assign Issues

**Archivo:** `auto-assign.yml`

**Qué hace:**
- Asigna automáticamente issues al creador
- Añade label "triage"

**Cuándo se ejecuta:**
- Cuando alguien crea un issue

---

## 📋 Comparación Rápida

| Workflow | DEBUG APK | RELEASE APK | Logs Detallados | Análisis de Fallos |
|----------|-----------|-------------|-----------------|-------------------|
| 🚨 BUILD EXTREMO | ✅ SÍ | ✅ SÍ | ✅ COMPLETOS | ✅ SÍ |
| 🚀 BUILD APK | ✅ SÍ | ❌ NO | ⚠️ BÁSICOS | ❌ NO |
| 🔄 CI/CD | ✅ SÍ | ❌ NO | ⚠️ BÁSICOS | ❌ NO |
| 📦 Release APK | ❌ NO | ✅ SÍ (firmado) | ⚠️ BÁSICOS | ❌ NO |

---

## 🎯 Recomendado

**Para obtener ambos APKs con logs detallados:** Usa **🚨 BUILD EXTREMO**

Es el más completo y te da TODA la información si algo falla.

---

## 📚 Documentación

| Archivo | Contenido |
|---------|-----------|
| [BUILD_EXTREMO.md](BUILD_EXTREMO.md) | **🆕 Guía completa del BUILD EXTREMO** |
| [GET_APK.md](GET_APK.md) | Guía para obtener el APK FÁCIL |
| [WORKFLOWS_SETUP.md](WORKFLOWS_SETUP.md) | Configuración completa |
| [WORKFLOWS.md](WORKFLOWS.md) | Este archivo |

---

## 🚫 Workflows Eliminados

No había workflows existentes, así que no hubo nada que eliminar.

---

## 📋 Próximos Pasos

1. **Subir código a GitHub:**
   ```bash
   git add .
   git commit -m "✨ Configurar workflows extremos de GitHub Actions"
   git push origin main
   ```

2. **Verificar que BUILD EXTREMO funcione:**
   - Ir a **Actions** en GitHub
   - Ver "🚨 BUILD EXTREMO" ejecutándose
   - Esperar 5-10 minutos
   - Descargar APKs desde artifacts

3. **Configurar Release APK (opcional):**
   - Generar keystore
   - Añadir secrets en GitHub

---

## 🎉 ¡Listo!

Ahora tienes workflows para TODAS las necesidades:

- 🚨 **BUILD EXTREMO** - El más completo (RECOMENDADO)
- 🚀 **BUILD APK** - Rápido y fácil
- 🔄 **CI/CD** - Para desarrollo diario
- 📦 **Release** - Para producción firmada

**¡Solo haz push y descarga tus APKs!** 🐷💕🐨
