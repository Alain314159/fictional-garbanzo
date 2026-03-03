# 🌸 Tema Floral y Stickers - Guía Completa

## 🌸 Tema Floral

### Descripción
El tema **Floral** es un tema romántico y natural inspirado en jardines de flores con elementos kawaii.

---

### Paleta de Colores

```kotlin
// Colores principales
primary = #FFD1DC        // Rosa pastel (rosa japonés)
onPrimary = #5C3A4A
primaryContainer = #FFECF1 // Rosa muy claro
onPrimaryContainer = #FF69B4 // Rosa fuerte

// Secundarios
secondary = #98FF98      // Verde menta
onSecondary = #2D4A2D
secondaryContainer = #C1FFC1 // Verde claro
onSecondaryContainer = #556B2F // Verde oliva

// Terciarios (acentos)
tertiary = #E6E6FA       // Lavanda
onTertiary = #4B3A6B
tertiaryContainer = #F0E6FF // Lavanda claro

// Fondo
background = #FFF5F7       // Blanco rosado (rosa bebé)
onBackground = #5C3A3A
surface = #FFFFFF
onSurface = #3A2A2A

// Acentos especiales
accentPeach = #FFE5CC      // Durazno
accentLilac = #C8A2C8      // Lila
accentSky = #87CEEB        // Azul cielo
accentSunshine = #FFD700   // Dorado suave
```

---

### Animaciones de Fondo

#### 1. Flores Cayendo

**Tipos de flores:**
- 🌸 Sakura (cerezos en flor) - 40%
- 🌼 Margaritas - 25%
- 🌻 Girasoles pequeños - 15%
- 🌹 Rosas - 10%
- 🌷 Tulipanes - 10%

**Movimiento:**
```kotlin
data class FallingFlower(
    val type: FlowerType,      // Sakura, Daisy, Sunflower, Rose, Tulip
    val x: Float,              // Posición X
    val y: Float,              // Posición Y (empieza arriba)
    val speedY: Float,         // Velocidad de caída
    val swayAmplitude: Float,  // Amplitud del balanceo
    val swayFrequency: Float,  // Frecuencia del balanceo
    val rotation: Float,       // Rotación actual
    val rotationSpeed: Float,  // Velocidad de rotación
    val scale: Float,          // Tamaño (0.6 a 1.0)
    val alpha: Float           // Transparencia (0.15 a 0.25)
)

fun update(deltaTime: Float) {
    y += speedY * deltaTime
    
    // Balanceo suave de lado a lado
    x += sin(time * swayFrequency) * swayAmplitude * deltaTime
    
    // Rotación lenta
    rotation += rotationSpeed * deltaTime
    
    // Resetear si sale de pantalla
    if (y > screenHeight) {
        y = -50f
        x = Random.nextFloat() * screenWidth
    }
}
```

#### 2. Nubecitas Flotando

**Características:**
- Nubes blancas esponjosas con caritas sonrientes
- Flotan horizontalmente lentamente
- 3-5 nubes visibles a la vez
- Diferentes tamaños

```kotlin
data class FluffyCloud(
    val x: Float,
    val y: Float,
    val speedX: Float,       // Movimiento horizontal
    val scale: Float,        // 0.8 a 1.5
    val hasSmile: Boolean,   // Algunas tienen carita
    val alpha: Float         // 0.2 a 0.3
)
```

#### 3. Mariposas Ocasionales

**Aparición:**
- 10% de probabilidad cada 30 segundos
- Vuelan en patrón de figura de 8
- Colores: rosa, azul, morado, amarillo

```kotlin
data class Butterfly(
    val x: Float,
    val y: Float,
    val color: Color,
    val wingAngle: Float,    // Para animar aleteo
    val figure8Progress: Float // Progreso en la figura de 8
)

fun updateButterfly(deltaTime: Float) {
    // Patrón de figura de 8
    figure8Progress += deltaTime * 0.5f
    x = screenWidth * 0.5f + cos(figure8Progress) * 100
    y = screenHeight * 0.5f + sin(2 * figure8Progress) * 50
    
    // Aleteo rápido
    wingAngle = sin(time * 20) * 30
}
```

---

### Elementos Decorativos

#### Bordes Floreados Animados

**Ubicación:**
- Bordes superior e inferior de la pantalla
- Bordes de las burbujas de mensaje
- Esquinas con viñetas florales

**Implementación:**
```kotlin
@Composable
fun FloralBorder(
    modifier: Modifier = Modifier,
    flowerType: FlowerType = FlowerType.Sakura
) {
    Box(modifier = modifier) {
        // Borde superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            repeat(10) { i ->
                FlowerIcon(
                    type = flowerType,
                    modifier = Modifier
                        .size(24.dp)
                        .rotate((i * 36).degrees)
                )
            }
        }
        
        // Borde inferior (similar)
        
        // Esquinas decoradas
        FlowerCorner(
            modifier = Modifier
                .align(Alignment.TopStart)
        )
        FlowerCorner(
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
        // ... otras esquinas
    }
}
```

#### Burbujas de Mensaje con Bordes Florales

```kotlin
@Composable
fun FloralMessageBubble(
    message: Message,
    isFromMe: Boolean
) {
    Surface(
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = if (isFromMe) 16.dp else 4.dp,
            bottomEnd = if (isFromMe) 4.dp else 16.dp
        ),
        border = BorderStroke(
            2.dp,
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFFFFD1DC), // Rosa
                    Color(0xFFE6E6FA)  // Lavanda
                )
            )
        )
    ) {
        Box {
            // Contenido del mensaje
            
            // Decoración de esquina con flor
            Icon(
                painter = painterResource(R.drawable.flower_corner),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .align(if (isFromMe) Alignment.TopEnd else Alignment.TopStart)
            )
        }
    }
}
```

---

### Separadores entre Mensajes

```kotlin
@Composable
fun FloralDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color(0xFFFFD1DC).copy(alpha = 0.5f)
        )
        
        Icon(
            painter = painterResource(R.drawable.tiny_flower),
            contentDescription = null,
            tint = Color(0xFFFF69B4),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(16.dp)
        )
        
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color(0xFFFFD1DC).copy(alpha = 0.5f)
        )
    }
}
```

---

## 🎨 Creador de Stickers

### Flujo de Creación

```
┌─────────────────────────────────────┐
│     ‹ Crear Sticker                 │
├─────────────────────────────────────┤
│                                     │
│  1. Seleccionar Imagen              │
│  ┌─────────────────────────────┐   │
│  │                             │   │
│  │      [📷] [🖼️]             │   │
│  │   Tomar    Galería          │   │
│  │                             │   │
│  └─────────────────────────────┘   │
│                                     │
│  2. Recortar y Editar               │
│  ┌─────────────────────────────┐   │
│  │    ┌─────────────┐          │   │
│  │    │  🐷          │          │   │
│  │    │   (imagen)   │          │   │
│  │    └─────────────┘          │   │
│  │      [Ajustar] [Auto]       │   │
│  └─────────────────────────────┘   │
│                                     │
│  3. Añadir Borde                    │
│  ○ Blanco clásico                   │
│  ○ Borde floral 🌸                 │
│  ○ Borde corazones 💕              │
│  ○ Borde estrellitas ⭐            │
│  ○ Sin borde                        │
│                                     │
│  4. Añadir Efectos (opcional)       │
│  [✓] Brillo                         │
│  [ ] Sombra                         │
│  [ ] Destellos                      │
│  [ ] Purpurina                      │
│                                     │
│       [  Guardar Sticker  ]         │
└─────────────────────────────────────┘
```

---

### Packs de Stickers Oficiales

#### Pack 1: 🐷 Cerdita

**Stickers incluidos (24 stickers):**

| Sticker | Descripción | Animación |
|---------|-------------|-----------|
| 🐷😊 | Cerdita feliz | Salta de alegría |
| 🐷😢 | Cerdita triste | Lágrimas cayendo |
| 🐷😍 | Cerdita enamorada | Corazones alrededor |
| 🐷😴 | Cerdita durmiendo | Ronquidos "Zzz" |
| 🐷👋 | Cerdita saludando | Mano moviéndose |
| 🐷💕 | Cerdita con corazón | Corazón latiendo |
| 🐷🎉 | Cerdita de fiesta | Confeti cayendo |
| 🐷🌸 | Cerdita con flor | Flor girando |
| 🐷😂 | Cerdita riendo | Sacudida de risa |
| 🐷😳 | Cerdita avergonzada | Rubor parpadeando |
| 🐷🤔 | Cerdita pensando | Bombilla aparece |
| 🐷💤 | Cerdita soñando | Burbujas de sueño |

#### Pack 2: 🐨 Koalita

**Stickers incluidos (24 stickers):**

| Sticker | Descripción | Animación |
|---------|-------------|-----------|
| 🐨😊 | Koalita feliz | Balanceándose |
| 🐨🌿 | Koalita comiendo | Masticando hoja |
| 🐨💤 | Koalita durmiendo | Colgado de rama |
| 🐨👋 | Koalita saludando | Ola con la mano |
| 🐨💕 | Koalita con amor | Corazones flotando |
| 🐨🎋 | Koalita en árbol | Subiendo al árbol |
| 🐨😯 | Koalita sorprendido | Ojos grandes |
| 🐨🧘 | Koalita meditando | Flotando en loto |
| 🐨🎵 | Koalita cantando | Notas musicales |
| 🐨🌈 | Koalita feliz | Arcoíris detrás |
| 🐨🤗 | Koalita abrazando | Brazos abriéndose |
| 🐨✨ | Koalita brillante | Brillo alrededor |

#### Pack 3: 🌸 Flores

**Stickers incluidos (20 stickers):**

| Sticker | Descripción | Animación |
|---------|-------------|-----------|
| 🌸 | Sakura cayendo | Pétalos flotando |
| 🌼 | Margarita feliz | Girando suavemente |
| 🌻 | Girasol sonriente | Siguiendo el sol |
| 🌹 | Rosa roja | Pétalos brillando |
| 🌷 | Tulipán bailando | Balanceándose |
| 🌺 | Hibisco tropical | Brillo suave |
| 🏵️ | Roseta decorativa | Rotando |
| 💐 | Ramo de flores | Partículas brillantes |
| 🌻🐝 | Girasol con abeja | Abeja volando |
| 🌸✨ | Flores mágicas | Estrellas alrededor |
| 🌼☀️ | Margarita y sol | Sol radiante |
| 🌷🦋 | Tulipán y mariposa | Mariposa volando |

#### Pack 4: ☁️ Nubes

**Stickers incluidos (16 stickers):**

| Sticker | Descripción | Animación |
|---------|-------------|-----------|
| ☁️😊 | Nube feliz | Flotando |
| ☁️😴 | Nube durmiendo | "Zzz" animados |
| ☁️🌈 | Nube con arcoíris | Arcoíris animado |
| ☁️⚡ | Nube de tormenta | Rayos cayendo |
| ☁️💧 | Nube lloviendo | Gotas cayendo |
| ☁️❄️ | Nube nevando | Copos de nieve |
| ☁️💕 | Nube enamorada | Corazones flotando |
| ☁️🌙 | Nube nocturna | Luna y estrellas |
| ☁️🎈 | Nube con globo | Globo flotando |
| ☁️🕊️ | Nube con paloma | Paloma volando |
| ☁️✨ | Nube mágica | Purpurina cayendo |
| ☁️🌟 | Nube con estrella | Estrella brillando |

#### Pack 5: 💕 Corazones

**Stickers incluidos (20 stickers):**

| Sticker | Descripción | Animación |
|---------|-------------|-----------|
| ❤️ | Corazón rojo | Latiendo |
| 💖 | Corazón brillante | Destellos |
| 💕 | Dos corazones | Girando entre sí |
| 💗 | Corazón creciente | Latido fuerte |
| 💓 | Corazón latiendo | Pulso animado |
| 💞 | Corazones rotando | Orbitando |
| 💟 | Corazón decorado | Flores alrededor |
| ❣️ | Corazón exclamación | Rebote suave |
| 💘 | Corazón con flecha | Flecha atravesando |
| 💝 | Corazón con cinta | Cinta moviéndose |
| 💌 | Carta de amor | Sobre abriéndose |
| 💙💚💛💜 | Corazones colores | Saltando en fila |

---

### Bordes Decorativos para Stickers

#### 1. Borde Floral 🌸

```kotlin
@Composable
fun FloralStickerBorder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFD1DC), // Rosa
                        Color(0xFF98FF98), // Verde menta
                        Color(0xFFE6E6FA)  // Lavanda
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // Esquinas con flores
        FlowerCornerDecoration()
    }
}
```

#### 2. Borde Corazones 💕

```kotlin
@Composable
fun HeartStickerBorder(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Borde con patrón de corazones
        BorderPattern(
            pattern = { heartIcon },
            spacing = 20.dp,
            color = Color(0xFFFF69B4)
        )
    }
}
```

#### 3. Borde Estrellitas ⭐

```kotlin
@Composable
fun StarryStickerBorder(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Borde con estrellas brillantes
        BorderPattern(
            pattern = { starIcon },
            spacing = 16.dp,
            color = Color(0xFFFFD700),
            animation = { twinkle() }
        )
    }
}
```

#### 4. Borde Burbujas 🫧

```kotlin
@Composable
fun BubbleStickerBorder(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Borde con burbujas animadas
        BorderPattern(
            pattern = { bubbleIcon },
            spacing = 18.dp,
            color = Color(0xFF87CEEB).copy(alpha = 0.6f),
            animation = { float() }
        )
    }
}
```

---

### Efectos Especiales para Stickers

#### 1. Brillo (Glow)

```kotlin
fun applyGlowEffect(
    bitmap: Bitmap,
    color: Color = Color.White,
    radius: Float = 20f
): Bitmap {
    // Implementar efecto de brillo exterior
}
```

#### 2. Sombra (Drop Shadow)

```kotlin
fun applyShadowEffect(
    bitmap: Bitmap,
    offset: IntOffset,
    blur: Float,
    alpha: Float
): Bitmap
```

#### 3. Destellos (Sparkles)

```kotlin
@Composable
fun SparkleOverlay(
    modifier: Modifier = Modifier,
    intensity: Float = 0.5f
) {
    // Partículas brillantes aleatorias
}
```

#### 4. Purpurina (Glitter)

```kotlin
@Composable
fun GlitterOverlay(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        Color(0xFFFFD700), // Oro
        Color(0xFFFF69B4), // Rosa
        Color(0xFF87CEEB)  // Azul
    )
) {
    // Partículas de purpurina cayendo
}
```

---

### Selector de Stickers en Chat

```
┌─────────────────────────────────────┐
│  Stickers                [🔍 Buscar]│
├─────────────────────────────────────┤
│  [❤️] [🐷] [🐨] [🌸] [☁️] [⭐] [🎵]│
│  Recientes                          │
├─────────────────────────────────────┤
│  ┌────┐ ┌────┐ ┌────┐ ┌────┐      │
│  │ 🐷 │ │ 🐷 │ │ 🐷 │ │ 🐷 │      │
│  │ 😊 │ │ 😢 │ │ 😍 │ │ 😴 │      │
│  └────┘ └────┘ └────┘ └────┘      │
│  ┌────┐ ┌────┐ ┌────┐ ┌────┐      │
│  │ 🐨 │ │ 🐨 │ │ 🌸 │ │ ☁️ │      │
│  │ 🌿 │ │ 💕 │ │ ✨ │ │ 🌈 │      │
│  └────┘ └────┘ └────┘ └────┘      │
│  ┌────┐ ┌────┐ ┌────┐ ┌────┐      │
│  │ 💕 │ │ ❤️ │ │ 💖 │ │ 💗 │      │
│  │ 🌸 │ │ ✨ │ │ 🌟 │ │ ⭐ │      │
│  └────┘ └────┘ └────┘ └────┘      │
│                                     │
│  [➕ Crear Sticker] [❤️ Favoritos] │
└─────────────────────────────────────┘
```

---

### Implementación Técnica

#### Data Models

```kotlin
data class Sticker(
    val id: String,
    val packId: String,
    val imageUrl: String,      // URL local o remota
    val isAnimated: Boolean,
    val animationData: AnimationData?,
    val tags: List<String>,    // Para búsqueda
    val createdAt: Long
)

data class StickerPack(
    val id: String,
    val name: String,
    val icon: String,          // Emoji o URL
    val stickers: List<Sticker>,
    val isOfficial: Boolean,   // Packs oficiales vs personalizados
    val createdAt: Long
)

data class StickerBorder(
    val id: String,
    val name: String,
    val previewUrl: String,
    val borderType: BorderType
)

enum class BorderType {
    FLORAL,
    HEARTS,
    STARS,
    BUBBLES,
    NONE
}
```

#### Room Database

```kotlin
@Entity(tableName = "sticker_packs")
data class StickerPackEntity(
    @PrimaryKey val id: String,
    val name: String,
    val icon: String,
    val isOfficial: Boolean,
    val createdAt: Long
)

@Entity(tableName = "stickers")
data class StickerEntity(
    @PrimaryKey val id: String,
    val packId: String,
    val imageUrl: String,
    val isAnimated: Boolean,
    val animationJson: String?,
    val tagsJson: String,
    val createdAt: Long,
    val isFavorite: Boolean
)

@Dao
interface StickerDao {
    @Query("SELECT * FROM stickers WHERE packId = :packId")
    fun getStickersByPack(packId: String): Flow<List<StickerEntity>>
    
    @Query("SELECT * FROM stickers WHERE isFavorite = 1")
    fun getFavoriteStickers(): Flow<List<StickerEntity>>
    
    @Query("SELECT * FROM stickers WHERE tags LIKE '%' || :query || '%'")
    suspend fun searchStickers(query: String): List<StickerEntity>
}
```

---

## 📊 Resumen de Características

| Característica | Tema Floral | Stickers |
|----------------|-------------|----------|
| **Colores únicos** | Rosa, menta, lavanda | N/A |
| **Animaciones fondo** | Flores, nubes, mariposas | N/A |
| **Bordes decorativos** | ✅ Floreados | ✅ Múltiples tipos |
| **Packs oficiales** | N/A | 5 packs (100+ stickers) |
| **Stickers animados** | N/A | ✅ Todos los packs |
| **Creador personalizado** | N/A | ✅ Con bordes y efectos |
| **Búsqueda** | N/A | ✅ Por tags/emoji |
| **Favoritos** | N/A | ✅ Sistema de favoritos |

---

**Última actualización:** Marzo 2026
