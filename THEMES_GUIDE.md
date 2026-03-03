# 🎨 Guía de Temas - NostrChat

## Overview

NostrChat incluye **6 temas principales** más variaciones, cada uno con su propia personalidad y animaciones de fondo.

---

## 🌞 Temas Base

### 1. Tema Claro (Default)

**Descripción:** Tema limpio y moderno para uso diurno.

**Colores:**
```kotlin
background = #FFFBFE (blanco rosáceo)
surface = #FFFBFE
primary = #6650a4 (púrpura)
onPrimary = #FFFFFF
secondary = #625b71
```

**Características:**
- Fondo claro
- Texto oscuro
- Acentos púrpuras
- Sin animaciones de fondo

---

### 2. Tema Noche Clara

**Descripción:** Tema oscuro suave para uso nocturno sin contraste extremo.

**Colores:**
```kotlin
background = #1A1A1A (gris oscuro suave)
surface = #242424
primary = #D0BCFF (púrpura claro)
onPrimary = #381E72
secondary = #CCC2DC
```

**Características:**
- Fondo gris oscuro (no negro)
- Texto claro
- Menos fatiga visual
- Ideal para lectura prolongada

---

### 3. Tema Noche Oscura (OLED)

**Descripción:** Tema completamente negro para pantallas AMOLED.

**Colores:**
```kotlin
background = #000000 (negro puro)
surface = #0A0A0A
primary = #D0BCFF
onPrimary = #381E72
secondary = #CCC2DC
```

**Características:**
- Fondo negro puro (#000000)
- Ahorro de batería en AMOLED
- Contraste alto
- Ideal para uso en oscuridad total

---

### 4. Tema Claro Opaco

**Descripción:** Tema moderno con efecto "frosted glass" (vidrio esmerilado).

**Colores:**
```kotlin
background = #FFFFFF con 80% opacity
surface = #F5F5F5 con 80% opacity
primary = #6650a4
```

**Características:**
- Fondos semitransparentes
- Efecto blur detrás del contenido
- Estilo iOS/macOS
- Requiere wallpaper con colores agradables

**Implementación técnica:**
```kotlin
@Composable
fun FrostedGlassBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(20.dp)
            .background(Color.White.copy(alpha = 0.3f))
    )
}
```

---

## 🐷 Tema Cerdita

**Descripción:** Tema adorable inspirado en una cerdita kawaii.

### Paleta de Colores

```kotlin
// Colores principales
primary = #FFB6C1        // Rosa pastel (light pink)
onPrimary = #5C2B32
primaryContainer = #FFD1DC  // Rosa más claro
onPrimaryContainer = #FF69B4 // Hot pink

// Secundarios
secondary = #FFFACD        // Lemon chiffon (amarillo suave)
onSecondary = #5C4A3D
secondaryContainer = #FFE4B5 // Moccasin
onSecondaryContainer = #D2691E

// Fondo
background = #FFF0F5       // Lavender blush (rosa muy claro)
onBackground = #5C3A3A
surface = #FFFFFF
onSurface = #3A2A2A

// Acentos
accentCoral = #FF7F50      // Coral
accentPink = #FF69B4       // Hot pink
accentGold = #FFD700       // Oro
```

### Animaciones de Fondo

**Elementos:**
- 🐷 Cerditas flotando (5-10 instancias)
- ✨ Estrellitas/brillos
- 💕 Corazoncitos ocasionales

**Movimiento:**
```kotlin
// Movimiento aleatorio suave
data class FloatingPiggy(
    val x: Float,           // Posición X actual
    val y: Float,           // Posición Y actual
    val speedX: Float,      // Velocidad X (-0.5 a 0.5)
    val speedY: Float,      // Velocidad Y (-0.5 a 0.5)
    val scale: Float,       // Tamaño (0.8 a 1.2)
    val rotation: Float,    // Rotación (-15 a 15 grados)
    val alpha: Float        // Transparencia (0.1 a 0.2)
)

// Actualización por frame
fun update(deltaTime: Float) {
    x += speedX * deltaTime
    y += speedY * deltaTime
    
    // Rebotar en bordes
    if (x < 0 || x > screenWidth) speedX *= -1
    if (y < 0 || y > screenHeight) speedY *= -1
}
```

**Implementación:**
```kotlin
@Composable
fun PiggyBackgroundTheme() {
    val piggies = remember { mutableStateListOf<FloatingPiggy>() }
    
    // Inicializar piggies
    LaunchedEffect(Unit) {
        repeat(8) {
            piggies.add(
                FloatingPiggy(
                    x = Random.nextFloat() * screenWidth,
                    y = Random.nextFloat() * screenHeight,
                    speedX = (Random.nextFloat() - 0.5f) * 0.3f,
                    speedY = (Random.nextFloat() - 0.5f) * 0.3f,
                    scale = 0.8f + Random.nextFloat() * 0.4f,
                    rotation = (Random.nextFloat() - 0.5f) * 30f,
                    alpha = 0.1f + Random.nextFloat() * 0.1f
                )
            )
        }
    }
    
    // Animación
    LaunchedEffect(Unit) {
        while (true) {
            delay(16) // ~60 FPS
            // Actualizar posiciones
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        piggies.forEach { piggy ->
            Image(
                painter = painterResource(R.drawable.piggy_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp * piggy.scale)
                    .offset { IntOffset(piggy.x.toInt(), piggy.y.toInt()) }
                    .rotate(piggy.rotation)
                    .alpha(piggy.alpha),
                alpha = piggy.alpha
            )
        }
    }
}
```

### Iconos Personalizados

- **Bubble de mensajes enviados:** Forma de cerdita
- **Avatar default:** Cerdita
- **Icono de enviar:** Huellita de cerdo
- **Indicador de escritura:** 3 cerditas saltando

---

## 🐨 Tema Koalita

**Descripción:** Tema tierno inspirado en un koala durmiendo en un eucalipto.

### Paleta de Colores

```kotlin
// Colores principales
primary = #778899        // Light slate gray (azul grisáceo)
onPrimary = #E8F0F8
primaryContainer = #B0C4DE // Light steel blue
onPrimaryContainer = #4A5568

// Secundarios
secondary = #8FBC8F      // Dark sea green (verde eucalipto)
onSecondary = #2D3A2D
secondaryContainer = #C1E1C1 // Dark green claro
onSecondaryContainer = #556B2F

// Fondo
background = #F0F8FF       // Alice blue (azul muy claro)
onBackground = #2C3E50
surface = #FAFBFF
onSurface = #1A202C

// Acentos
accentEucalyptus = #90EE90 // Light green
accentBlue = #87CEEB       // Sky blue
accentSilver = #C0C0C0     // Plata
```

### Animaciones de Fondo

**Elementos:**
- 🐨 Koalitas flotando (5-8 instancias)
- 🍃 Hojas de eucalipto cayendo
- ☁️ Nubecitas blancas

**Movimiento:**
```kotlin
data class FloatingKoala(
    val x: Float,
    val y: Float,
    val speedY: Float,      // Principalmente caen hacia abajo
    val rotation: Float,
    val scale: Float,
    val alpha: Float,
    val isLeaf: Boolean     // true = hoja, false = koala
)

// Las hojas caen más rápido y rotan
fun updateLeaf(deltaTime: Float) {
    y += speedY * deltaTime * 1.5  // Más rápido
    rotation += 2f * deltaTime      // Rotación constante
}

// Los koalas flotan más lento
fun updateKoala(deltaTime: Float) {
    y += speedY * deltaTime * 0.5  // Más lento
    x += sin(time * 2) * 0.2f       // Movimiento sinusoidal
}
```

**Implementación:**
```kotlin
@Composable
fun KoalaBackgroundTheme() {
    val elements = remember { mutableStateListOf<FloatingElement>() }
    
    LaunchedEffect(Unit) {
        // Añadir koalas
        repeat(5) {
            elements.add(FloatingKoala(isLeaf = false, ...))
        }
        // Añadir hojas
        repeat(15) {
            elements.add(FloatingKoala(isLeaf = true, ...))
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        elements.forEach { element ->
            if (element.isLeaf) {
                Image(
                    painter = painterResource(R.drawable.eucalyptus_leaf),
                    // ... similar al de cerdita
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.koala_icon),
                    // ...
                )
            }
        }
    }
}
```

### Iconos Personalizados

- **Bubble de mensajes enviados:** Forma de hoja
- **Avatar default:** Koalita
- **Icono de enviar:** Hoja de eucalipto
- **Indicador de escritura:** 3 hojas balanceándose

---

## 🐷🐨 Tema Mix

**Descripción:** La combinación perfecta de ambos mundos.

### Paleta de Colores

```kotlin
// Gradiente de colores
primary = Gradient(
    start = #FFB6C1,  // Rosa cerdita
    end = #778899     // Azul koalita
)

secondary = Gradient(
    start = #FFFACD,  // Amarillo cerdita
    end = #8FBC8F     // Verde koalita
)

background = #F5F5FF  // Lavanda muy claro (mezcla)
surface = #FFFFFF
```

### Animaciones de Fondo

**Elementos:**
- 🐷 Cerditas (4-5 instancias)
- 🐨 Koalitas (4-5 instancias)
- 💕 Corazoncitos y 🍃 Hojas
- ✨ Partículas brillantes

**Interacciones:**
- Cerditas y koalitas se saludan al cruzarse
- Las hojas caen sobre las cerditas
- Los koalitas "abrazan" a las cerditas ocasionalmente

**Implementación:**
```kotlin
@Composable
fun MixBackgroundTheme() {
    val entities = remember { mutableStateListOf<MixEntity>() }
    
    // Crear entidades mixtas
    LaunchedEffect(Unit) {
        repeat(10) {
            entities.add(
                when (Random.nextInt(3)) {
                    0 -> Piggy(...)
                    1 -> Koala(...)
                    2 -> HeartOrLeaf(...)
                }
            )
        }
    }
    
    // Detectar colisiones para interacciones
    LaunchedEffect(entities) {
        entities.forEach { entity1 ->
            entities.forEach { entity2 ->
                if (entity1 != entity2 && isClose(entity1, entity2)) {
                    triggerInteraction(entity1, entity2)
                }
            }
        }
    }
}
```

---

## 🎛️ Selector de Temas

### UI del Selector

```
┌─────────────────────────────────────┐
│         Seleccionar Tema            │
├─────────────────────────────────────┤
│  ┌─────┐  ┌─────┐  ┌─────┐         │
│  │ ☀️  │  │ 🌙  │  │ 🌑  │         │
│  │Claro│  │Noche│  │OLED │         │
│  └─────┘  └─────┘  └─────┘         │
│                                     │
│  ┌─────┐  ┌─────┐  ┌─────┐         │
│  │ 🌫️  │  │ 🐷  │  │ 🐨  │         │
│  │Opaco│  │Cerdita│Koalita│       │
│  └─────┘  └─────┘  └─────┘         │
│                                     │
│  ┌─────────────────────────┐       │
│  │      🐷🐨 Mix           │       │
│  └─────────────────────────┘       │
├─────────────────────────────────────┤
│  [Vista previa del tema seleccionado]│
│                                     │
│  ○ Aplicar ahora  ○ Programar      │
└─────────────────────────────────────┘
```

### Código del Selector

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectorScreen(
    currentTheme: Theme,
    onThemeSelected: (Theme) -> Unit,
    onDismiss: () -> Unit
) {
    val themes = listOf(
        Theme.Light("☀️", "Claro"),
        Theme.DarkLight("🌙", "Noche Clara"),
        Theme.DarkOLED("🌑", "OLED"),
        Theme.Opaque("🌫️", "Opaco"),
        Theme.Piggy("🐷", "Cerdita"),
        Theme.Koala("🐨", "Koalita"),
        Theme.Mix("🐷🐨", "Mix")
    )
    
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(themes) { theme ->
            ThemeCard(
                theme = theme,
                isSelected = currentTheme == theme,
                onClick = { onThemeSelected(theme) }
            )
        }
    }
}

@Composable
fun ThemeCard(
    theme: Theme,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(theme.icon, fontSize = 48.sp)
            Text(theme.name, style = MaterialTheme.typography.labelLarge)
        }
    }
}
```

---

## ⚙️ Configuración de Animaciones

### Opciones de Personalización

En **Ajustes → Apariencia → Animaciones**:

```
┌─────────────────────────────────────┐
│      Configuración de Animaciones   │
├─────────────────────────────────────┤
│  Intensidad de animación            │
│  ○ Ninguna     ○ Suave             │
│  ○ Normal      ○ Intensa           │
│                                     │
│  Elementos de fondo                 │
│  [✓] Mostrar mascotas               │
│  [✓] Mostrar partículas            │
│  [✓] Movimiento aleatorio          │
│                                     │
│  Rendimiento                        │
│  [✓] Reducir en modo ahorro        │
│  [ ] Pausar cuando no visible      │
└─────────────────────────────────────┘
```

### Guardar Preferencias

```kotlin
data class ThemeSettings(
    val selectedTheme: String,
    val animationIntensity: Int,      // 0-3
    val showMascots: Boolean,
    val showParticles: Boolean,
    val reduceMotion: Boolean,
    val pauseInBackground: Boolean
)

// Guardar en DataStore
val SettingsDataStore.themeSettings: Flow<ThemeSettings> = 
    dataStore.data.map { prefs ->
        ThemeSettings(
            selectedTheme = prefs[THEME_KEY] ?: "light",
            animationIntensity = prefs[ANIMATION_INTENSITY_KEY] ?: 1,
            showMascots = prefs[SHOW_MASCOTS_KEY] ?: true,
            // ...
        )
    }
```

---

## 📊 Comparativa de Temas

| Característica | Claro | Noche | OLED | Opaco | Cerdita | Koalita | Mix |
|----------------|-------|-------|------|-------|---------|---------|-----|
| Fondo claro | ✅ | ❌ | ❌ | ✅ | ✅ | ✅ | ✅ |
| Fondo oscuro | ❌ | ✅ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Animaciones | ❌ | ❌ | ❌ | ⚠️ | ✅ | ✅ | ✅ |
| Mascotas | ❌ | ❌ | ❌ | ❌ | 🐷 | 🐨 | 🐷🐨 |
| Ahorro batería | ❌ | ⚠️ | ✅ | ❌ | ❌ | ❌ | ❌ |
| Material You | ✅ | ✅ | ✅ | ✅ | ⚠️ | ⚠️ | ⚠️ |

---

## 🎯 Recomendaciones de Uso

| Situación | Tema Recomendado |
|-----------|------------------|
| Día, interior | Claro o Cerdita |
| Día, exterior | Claro (más brillo) |
| Noche, luz tenue | Noche Clara |
| Noche, oscuridad total | OLED |
| Quiere algo único | Opaco |
| Quiere sonreír | Cerdita 🐷 |
| Quiere calma | Koalita 🐨 |
| No se decide | Mix |

---

**Última actualización:** Marzo 2026
