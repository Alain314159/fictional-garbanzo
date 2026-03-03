# 🐷 Cerdita - Workflows de GitHub configurados!

## ✅ Workflows Creados

He creado **4 workflows** para el proyecto Cerdita. No había ningún workflow existente, así que no hubo nada que eliminar.

---

## 📋 Lista de Workflows

### 1. 🔄 CI/CD (`android-ci.yml`)

**Propósito:** Compilar y probar la app automáticamente en cada push o Pull Request.

**Se ejecuta cuando:**
- Haces push a `main` o `develop`
- Creas o actualizas un Pull Request

**Qué hace:**
1. ✅ Checkout del código
2. ✅ Configura JDK 17
3. ✅ Compila la app (Debug APK)
4. ✅ Ejecuta tests unitarios
5. ✅ Ejecuta Android Lint (análisis de código)
6. ✅ Verifica dependencias
7. ✅ Sube el APK como artifact (puedes descargarlo)
8. ✅ Sube reportes de tests y lint

**Resultado:** Sabrás inmediatamente si tu código compila y pasa los tests.

---

### 2. 📦 Release APK (`release-apk.yml`)

**Propósito:** Generar APK firmado automáticamente cuando creas un release en GitHub.

**Se ejecuta cuando:**
- Publicas un release en GitHub

**Qué hace:**
1. ✅ Checkout del código
2. ✅ Configura JDK 17
3. ✅ Descarga el keystore desde GitHub Secrets
4. ✅ Compila la app (Release APK firmado)
5. ✅ Genera SHA-256 del APK
6. ✅ Sube el APK al release de GitHub
7. ✅ Sube el SHA-256 para verificación

**Configuración necesaria:**
Antes de usar este workflow, debes añadir los secrets en GitHub:

```
Settings → Secrets and variables → Actions → New repository secret

KEYSTORE = (tu archivo .keystore en base64)
KEYSTORE_PASSWORD = (tu contraseña)
KEY_PASSWORD = (tu contraseña de key)
KEY_ALIAS = (tu alias)
```

**Cómo generar el keystore:**
```bash
keytool -genkey -v -keystore cerdita.keystore \
  -alias cerdita \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000

# Convertir a base64 para GitHub
cat cerdita.keystore | base64 | pbcopy  # macOS
cat cerdita.keystore | base64 | clip    # Windows
cat cerdita.keystore | base64 | xclip -selection clipboard  # Linux
```

---

### 3. 🔄 Dependency Updates (`dependency-updates.yml`)

**Propósito:** Revisar automáticamente si hay dependencias desactualizadas.

**Se ejecuta cuando:**
- Todos los lunes a las 9:00 AM UTC
- O manualmente desde la pestaña Actions

**Qué hace:**
1. ✅ Checkout del código
2. ✅ Configura JDK 17
3. ✅ Ejecuta `dependencyUpdates`
4. ✅ Crea un issue con las actualizaciones disponibles
5. ✅ Ejecuta chequeo de seguridad de dependencias

**Resultado:** Cada lunes tendrás un issue con las dependencias que necesitan actualización.

---

### 4. 🤖 Auto Assign (`auto-assign.yml`)

**Propósito:** Asignar automáticamente issues y añadir labels.

**Se ejecuta cuando:**
- Alguien crea un issue

**Qué hace:**
1. ✅ Asigna el issue a quien lo creó
2. ✅ Añade label "triage" automáticamente

**Resultado:** Los issues se organizan solos.

---

## 📁 Estructura de Archivos

```
.github/
├── WORKFLOWS.md                    # Este archivo
└── workflows/
    ├── android-ci.yml              # CI/CD principal
    ├── release-apk.yml             # Generar APK firmado
    ├── dependency-updates.yml      # Actualizar dependencias
    └── auto-assign.yml             # Auto asignar issues
gradle/
└── wrapper/
    └── gradle-wrapper.properties   # Configuración de Gradle
gradlew                             # Gradle Wrapper script
```

---

## 🚀 Cómo Usar los Workflows

### 1. **Subir el código a GitHub**

```bash
git add .
git commit -m "✨ Configurar workflows de GitHub Actions"
git push origin main
```

### 2. **Verificar que CI/CD funcione**

1. Ve a la pestaña **Actions** en GitHub
2. Deberías ver el workflow "🐷 Cerdita CI" ejecutándose
3. Espera a que termine (3-5 minutos)
4. Verifica que todos los checks estén en ✅

### 3. **Descargar el APK de Debug**

1. En el workflow completado, haz click en el nombre
2. Baja hasta "Artifacts"
3. Haz click en `cerdita-debug-apk`
4. Se descargará el APK listo para instalar

### 4. **Configurar Release APK**

```bash
# 1. Generar keystore
keytool -genkey -v -keystore cerdita.keystore \
  -alias cerdita \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000

# 2. Convertir a base64
cat cerdita.keystore | base64

# 3. Ir a GitHub → Settings → Secrets and variables → Actions
# 4. Añadir secrets:
#    - KEYSTORE (el base64)
#    - KEYSTORE_PASSWORD
#    - KEY_PASSWORD
#    - KEY_ALIAS
```

### 5. **Crear tu primer Release**

1. Ve a **Releases** en GitHub
2. Click en "Create a new release"
3. Tag version: `v0.1.0-alpha`
4. Release title: `Cerdita 💕 v0.1.0-alpha`
5. Description: "Primera versión de Cerdita!"
6. Click "Publish release"
7. El workflow se ejecutará automáticamente
8. En unos minutos, el APK estará en los assets del release

---

## 🎯 Flujo de Trabajo Recomendado

```
┌─────────────────────────────────────────────────────────┐
│  1. Crear feature branch                                │
│     git checkout -b feature/nueva-feature               │
└─────────────────────────────────────────────────────────┘
                         ↓
┌─────────────────────────────────────────────────────────┐
│  2. Desarrollar la feature                              │
│     (hacer commits)                                     │
└─────────────────────────────────────────────────────────┘
                         ↓
┌─────────────────────────────────────────────────────────┐
│  3. Crear Pull Request                                  │
│     CI/CD se ejecuta automáticamente                    │
│     ✅ Verificar que todos los checks pasen            │
└─────────────────────────────────────────────────────────┘
                         ↓
┌─────────────────────────────────────────────────────────┐
│  4. Merge a main                                        │
│     CI/CD se ejecuta en main                           │
└─────────────────────────────────────────────────────────┘
                         ↓
┌─────────────────────────────────────────────────────────┐
│  5. Cuando esté lista para usuarios:                    │
│     Crear Release → Se genera APK firmado               │
└─────────────────────────────────────────────────────────┘
```

---

## 📊 Estado de los Workflows

| Workflow | Estado | Configuración Necesaria |
|----------|--------|------------------------|
| 🔄 CI/CD | ✅ Listo | Ninguna |
| 📦 Release APK | ⚠️ Pendiente | Añadir secrets del keystore |
| 🔄 Dependency Updates | ✅ Listo | Ninguna |
| 🤖 Auto Assign | ✅ Listo | Ninguna |

---

## 🔧 Personalización

### Cambiar cuando se ejecuta CI/CD

Editar `.github/workflows/android-ci.yml`:

```yaml
on:
  push:
    branches: [ "main" ]  # Cambiar branches
  pull_request:
    branches: [ "main" ]
```

### Cambiar frecuencia de Dependency Updates

Editar `.github/workflows/dependency-updates.yml`:

```yaml
on:
  schedule:
    - cron: '0 9 * * MON'  # Cambiar a tu horario preferido
```

### Añadir más tests

Editar `app/build.gradle.kts`:

```kotlin
dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.espresso.core)
}
```

---

## 💡 Tips

1. **Ver el progreso de los workflows:**
   - Ve a la pestaña **Actions** en GitHub
   - Verás todos los workflows ejecutándose

2. **Descargar APKs de prueba:**
   - Cada push genera un APK en los artifacts
   - Puedes descargarlo e instalarlo en tu teléfono

3. **Cancelar workflows:**
   - Si haces un push incorrecto, puedes cancelar el workflow en Actions

4. **Re-ejecutar workflows:**
   - Puedes volver a ejecutar un workflow fallido click en "Re-run"

5. **Notifications:**
   - Configura notificaciones para saber cuando un workflow falla

---

## 🎉 ¡Listo!

Los workflows están configurados y listos para usar.

**Próximos pasos:**
1. ✅ Subir código a GitHub
2. ✅ Verificar que CI/CD funcione
3. ⏳ Configurar secrets para Release APK
4. 🚀 Empezar a desarrollar!

---

**¡A codificar Cerdita!** 🐷💕🐨
