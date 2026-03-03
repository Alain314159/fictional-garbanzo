# 🚨 BUILD EXTREMO - DEBUG + RELEASE + LOGS DETALLADOS

## 🎯 ¿Qué es este workflow?

Es un workflow **SUPER COMPLETO** que:
- ✅ Genera **DEBUG APK** y **RELEASE APK**
- ✅ Muestra **LOGS EXTREMADAMENTE DETALLADOS** si falla
- ✅ Sube **TODO** como artifacts para debugging
- ✅ Analiza automáticamente los fallos

---

## 📦 ¿Qué genera?

### APKs
| Tipo | Archivo | ¿Siempre disponible? |
|------|---------|---------------------|
| **DEBUG** | `app-debug.apk` | ✅ SÍ (para testing) |
| **RELEASE** | `app-release.apk` | ⚠️ Si hay keystore (para producción) |

### Artifacts (se suben siempre)
| Artifact | Contenido | Retención |
|----------|-----------|-----------|
| 🐷 **CERDITA-DEBUG-APK** | APK de debug | 30 días |
| 🐷 **CERDITA-RELEASE-APK** | APK de release (si existe) | 30 días |
| 📊 **BUILD-INFORMATION** | Info detallada del build | 30 días |
| 📝 **FULL-BUILD-LOGS** | Logs completos, reports, dependencias | 30 días |
| 🔍 **FAILURE-ANALYSIS** | Análisis de fallo (si falla) | 30 días |

---

## 🔍 ¿Qué información muestra?

### Antes del Build
```
=========================================
🖥️ INFORMACIÓN DEL SISTEMA
=========================================
OS: Linux ...
CPU: 4 cores
RAM: 16GB
Disk: 50GB libre
Java: 17.0.x
=========================================
```

### Durante el Build
```
=========================================
📁 ARCHIVOS DEL PROYECTO
=========================================
Root:
- README.md
- build.gradle.kts
- app/
...

=========================================
📄 BUILD.GRADLE.KTS
=========================================
(contenido completo del archivo)

=========================================
🧹 LIMPIEZA PRE-BUILD
=========================================
./gradlew clean

=========================================
📦 DESCARGANDO DEPENDENCIAS
=========================================
./gradlew dependencies

=========================================
🔨 BUILD DEBUG APK
=========================================
./gradlew assembleDebug --stacktrace --info

=========================================
🔍 VERIFICANDO DEBUG APK
=========================================
✅ DEBUG APK ENCONTRADO
Tamaño: 45MB
SHA256: abc123...
```

### Si FALLA el Build
```
=========================================
❌❌❌ ¡BUILD FALLÓ! ❌❌❌
=========================================
🔍 POSIBLES CAUSAS:
   1. Errores de compilación en el código
   2. Dependencias faltantes o incorrectas
   3. Configuración de Gradle incorrecta
   4. AndroidManifest.xml con errores
   5. Recursos faltantes (R.java)
=========================================
📊 REVISAR:
   - Logs completos del build
   - app/build.gradle.kts
   - AndroidManifest.xml
   - Archivos de recursos (res/)
=========================================
📝 COMANDO PARA DEBUG LOCAL:
   ./gradlew clean assembleDebug --stacktrace --info
=========================================
```

### Análisis Automático de Fallos
```
=========================================
🔍 ANÁLISIS DETALLADO DEL FALLO
=========================================

1️⃣ ÚLTIMOS COMMITS:
abc1234 Fix bug
def5678 Add feature
...

2️⃣ CAMBIOS EN EL ÚLTIMO COMMIT:
app/src/main/java/.../MainActivity.kt | 10 +++++++---
app/build.gradle.kts | 5 +++--

3️⃣ ARCHIVOS MODIFICADOS:
app/src/main/java/.../MainActivity.kt
app/build.gradle.kts

4️⃣ ESTADO DEL REPOSITORIO:
On branch main
Changes not staged for commit:
...
```

---

## 🚀 ¿Cómo usarlo?

### Opción 1: Automático (con push)

```bash
git add .
git commit -m "tu cambio"
git push

# El workflow se ejecuta SOLO
# Espera 5-10 minutos
# Ve a GitHub → Actions → 🚨 BUILD EXTREMO
# Descarga los artifacts
```

### Opción 2: Manual (sin push)

1. Ve a **Actions** → "🚨 BUILD EXTREMO"
2. Click en **"Run workflow"**
3. Selecciona el branch
4. Click **"Run workflow"**
5. Espera 5-10 minutos
6. Descarga los artifacts

---

## 📊 ¿Qué hacer si FALLA?

### Paso 1: Revisar el mensaje de error

En la página del workflow, busca:
```
❌❌❌ ¡BUILD FALLÓ! ❌❌❌
```

Ahí verás las posibles causas.

### Paso 2: Descargar artifacts

Descarga:
- 🔍 **FAILURE-ANALYSIS** (análisis del fallo)
- 📝 **FULL-BUILD-LOGS** (logs completos)

### Paso 3: Revisar logs locales

Ejecuta en tu computadora:
```bash
./gradlew clean assembleDebug --stacktrace --info
```

### Paso 4: Posibles soluciones

| Error | Solución |
|-------|----------|
| `SDK not found` | Verificar que Android SDK esté instalado |
| `Dependency X not found` | Revisar `build.gradle.kts` |
| `R.java not generated` | Revisar recursos en `res/` |
| `Manifest merger failed` | Revisar `AndroidManifest.xml` |
| `Kotlin compilation error` | Revisar el código Kotlin |

### Paso 5: Re-ejecutar

Después de corregir:
```bash
git add .
git commit -m "fix: corregir error"
git push

# El workflow se ejecuta solo
```

---

## 🎯 Comparación con otros workflows

| Workflow | DEBUG | RELEASE | Logs | Análisis |
|----------|-------|---------|------|----------|
| 🚀 BUILD APK | ✅ | ❌ | Básicos | ❌ |
| 🚨 BUILD EXTREMO | ✅ | ✅ | **COMPLETOS** | ✅ |

---

## 💡 Tips

1. **Los artifacts duran 30 días** - Descarga pronto
2. **RELEASE APK requiere keystore** - Si no hay, solo tendrás DEBUG
3. **El análisis de fallos es automático** - Revisa el artifact 🔍
4. **Puedes cancelar builds viejos** - Si haces otro push
5. **El timeout es 30 minutos** - Si tarda más, algo está mal

---

## 🔗 Enlaces rápidos

| Enlace | ¿Qué es? |
|--------|----------|
| [Actions](../../actions) | Ver builds |
| [Workflow](../../blob/main/.github/workflows/build-extremo-logs.yml) | Ver configuración |
| [GET_APK.md](GET_APK.md) | Guía para obtener APK |

---

## 🎉 ¡Eso es todo!

**Este workflow es el MÁS COMPLETO:**
- ✅ Genera DEBUG y RELEASE APK
- ✅ Logs extremadamente detallados
- ✅ Análisis automático de fallos
- ✅ Todos los artifacts necesarios

**¡Si falla, tendrás TODA la información para debuggear!** 🔍

---

**¡A construir Cerdita!** 🐷💕🐨
