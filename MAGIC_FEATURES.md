# ✨ Características Mágicas de NostrChat

> **Haciendo que cada mensaje sea especial** 💕

---

## 📋 Resumen de Características Mágicas

1. ✨ **Efectos en Palabras Románticas**
2. 🖼️ **Fondos de Chat Personalizados**
3. 🎤 **Notas de Voz Mágicas**
4. 📅 **Calendario de Fechas Especiales**
5. 💕 **Botón Mágico de Abrazo**

---

## ✨ Efectos en Palabras Románticas

### Descripción
Cuando un usuario escribe ciertas palabras o frases especiales, se activan efectos visuales mágicos en el mensaje.

### Palabras Detectadas y Efectos

| Palabra/Frase | Efecto Visual | Partículas |
|---------------|---------------|------------|
| **"te amo"** | Destellitos dorados + corazones flotando | 💛✨💕 |
| **"te quiero"** | Corazones rosados flotando | 💗💖💓 |
| **"eres hermoso/a"** | Brillitos alrededor del mensaje | ✨⭐🌟 |
| **"me encantas"** | Estrellitas doradas | 🌟💫 |
| **"buenos días"** | Amanecer con pajaritos | 🌅🐦 |
| **"buenas noches"** | Luna y estrellitas | 🌙⭐ |
| **"feliz cumpleaños"** | Confeti y globos | 🎉🎈 |
| **"te extraño"** | Nubecita triste con lluvia suave | 💭🌧️ |
| **"gracias"** | Corazones pequeños | 💕 |
| **"perdón"** | Carita triste con brillo | 😢✨ |

### Implementación Técnica

```kotlin
// Detector de palabras mágicas
class MagicWordDetector {
    private val magicWords = mapOf(
        "te amo" to MagicEffect.GOLDEN_SPARKLES_HEARTS,
        "te quiero" to MagicEffect.PINK_HEARTS,
        "eres hermoso" to MagicEffect.SPARKLES,
        "eres hermosa" to MagicEffect.SPARKLES,
        "me encantas" to MagicEffect.GOLDEN_STARS,
        "buenos dias" to MagicEffect.SUNRISE_BIRDS,
        "buenos días" to MagicEffect.SUNRISE_BIRDS,
        "buenas noches" to MagicEffect.MOON_STARS,
        "feliz cumpleaños" to MagicEffect.CONFETTI_BALLOONS,
        "te extrano" to MagicEffect.SAD_CLOUD_RAIN,
        "te extraño" to MagicEffect.SAD_CLOUD_RAIN,
        "gracias" to MagicEffect.SMALL_HEARTS,
        "perdon" to MagicEffect.SAD_SPARKLE,
        "perdón" to MagicEffect.SAD_SPARKLE
    )
    
    fun detectMagicWord(text: String): MagicEffect? {
        val lowerText = text.lowercase()
        return magicWords.entries
            .firstOrNull { it.key in lowerText }
            ?.value
    }
}

// Sistema de partículas
@Composable
fun MagicParticleEffect(
    effect: MagicEffect,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {}
) {
    val particles = remember { mutableStateListOf<Particle>() }
    
    // Crear partículas según el efecto
    LaunchedEffect(effect) {
        repeat(effect.particleCount) { i ->
            particles.add(
                Particle(
                    x = Random.nextFloat() * screenWidth,
                    y = screenHeight + 100,
                    type = effect.particleType,
                    delay = i * 50ms
                )
            )
        }
    }
    
    // Animación de partículas
    LaunchedEffect(Unit) {
        while (particles.isNotEmpty()) {
            delay(16) // 60 FPS
            particles.forEach { it.update() }
            particles.removeAll { it.isOffScreen }
        }
        onComplete()
    }
    
    Box(modifier = modifier) {
        particles.forEach { particle ->
            ParticleComposable(
                particle = particle,
                modifier = Modifier
                    .offset { IntOffset(particle.x.toInt(), particle.y.toInt()) }
                    .alpha(particle.alpha)
                    .rotate(particle.rotation)
            )
        }
    }
}
```

### Configuración de Usuario

```
┌─────────────────────────────────────┐
│  ‹ Efectos Mágicos                  │
├─────────────────────────────────────┤
│                                     │
│  [✓] Activar efectos mágicos        │
│                                     │
│  Intensidad de animación:           │
│  ○ Suave   ● Normal   ○ Intensa     │
│                                     │
│  Efectos individuales:              │
│  [✓] "te amo" → Destellitos dorados │
│  [✓] "te quiero" → Corazones        │
│  [✓] "buenos días" → Amanecer       │
│  [✓] "feliz cumpleaños" → Confeti   │
│  ... (más efectos)                  │
│                                     │
│  Palabras personalizadas:           │
│  ┌─────────────────────────────┐   │
│  │ + Añadir palabra especial   │   │
│  └─────────────────────────────┘   │
│                                     │
│  [  Restaurar predeterminados  ]    │
└─────────────────────────────────────┘
```

---

## 🖼️ Fondos de Chat Personalizados

### Fondos Predeterminados

| Fondo | Descripción | Animación |
|-------|-------------|-----------|
| 🐷 **Cerditas Durmiendo** | Cerditas acostadas sobre nubes | Respiración suave |
| 🐨 **Koalitas en Árboles** | Koalitas colgando de ramas | Hojas moviéndose |
| 🌸 **Jardín de Flores** | Flores de varios colores | Pétalos cayendo |
| ☁️ **Cielo con Nubes** | Nubes esponjosas blancas | Flotando lentamente |
| 🌙 **Noche Estrellada** | Cielo oscuro con estrellas | Estrellas brillando |
| 🌈 **Arcoíris** | Arcoíris colorido | Nubes moviéndose |
| 💕 **Corazones Flotando** | Corazones rosados | Flotando hacia arriba |
| 🐷🐨 **Mascotas Juntas** | Cerdita y Koalita jugando | Movimiento suave |

### Fondos Personalizados

**Subir imagen propia:**
```
┌─────────────────────────────────────┐
│  ‹ Fondo Personalizado              │
├─────────────────────────────────────┤
│                                     │
│  ┌─────────────────────────────┐   │
│  │                             │   │
│  │      [📷] [🖼️]             │   │
│  │   Tomar    Galería          │   │
│  │                             │   │
│  └─────────────────────────────┘   │
│                                     │
│  Ajustes de imagen:                 │
│  Brillo:    ────●───────            │
│  Blur:      ──●──────────           │
│  Escala:    ──────●──────           │
│                                     │
│  [✓] Aplicar blur para legibilidad │
│  [✓] Oscurecer para modo oscuro    │
│                                     │
│  [  Aplicar Fondo  ]                │
└─────────────────────────────────────┘
```

### Fondos por Contacto

- Cada chat puede tener su propio fondo
- Fondo predeterminado para nuevos chats
- Sugerencia de fondo según contacto (ej: foto de la mascota favorita del contacto)

### Fondos Animados al Abrir Chat

**Animación de bienvenida:**
```kotlin
@Composable
fun ChatWelcomeAnimation(
    contactName: String,
    theme: Theme
) {
    val showAnimation by remember { mutableStateOf(true) }
    
    if (showAnimation) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(animationProgress)
        ) {
            when (theme) {
                Theme.PIGGY -> {
                    // Cerditas saltando y saludando
                    PiggyWelcome()
                }
                Theme.KOALA -> {
                    // Koalitas colgando y saludando
                    KoalaWelcome()
                }
                Theme.FLORAL -> {
                    // Flores girando
                    FloralWelcome()
                }
                else -> {}
            }
        }
        
        // Desvanecer después de 2 segundos
        LaunchedEffect(Unit) {
            delay(2000)
            showAnimation = false
        }
    }
}
```

---

## 🎤 Notas de Voz Mágicas

### Animaciones Durante Reproducción

**Tipos de animaciones:**

1. **☁️ Nubecitas Flotando**
   - Nubes blancas esponjosas
   - Flotan de izquierda a derecha
   - Opacidad variable

2. **🌟 Estrellitas Brillando**
   - Estrellas doradas
   - Brillan al ritmo del audio
   - Aparecen y desaparecen

3. **🐷 Cerditas Saltando**
   - Pequeñas cerditas
   - Saltan suavemente
   - Siguen la onda de audio

4. **🐨 Koalitas Balanceándose**
   - Koalitas colgando
   - Se balancean con el audio
   - Hojas de eucalipto cayendo

5. **🎵 Notas Musicales**
   - Notas de colores
   - Flotan hacia arriba
   - Tamaño según volumen

### Visualizador de Audio Mejorado

```kotlin
@Composable
fun MagicVoiceNoteVisualizer(
    audioAmplitude: Float,
    animationType: VoiceNoteAnimation
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // Onda de audio base
        AudioWaveform(
            amplitude = audioAmplitude,
            color = MaterialTheme.colorScheme.primary
        )
        
        // Partículas mágicas
        when (animationType) {
            VoiceNoteAnimation.CLOUDS -> {
                CloudParticles(amplitude = audioAmplitude)
            }
            VoiceNoteAnimation.STARS -> {
                StarParticles(amplitude = audioAmplitude)
            }
            VoiceNoteAnimation.PIGGY -> {
                PiggyParticles(amplitude = audioAmplitude)
            }
            VoiceNoteAnimation.KOALA -> {
                KoalaParticles(amplitude = audioAmplitude)
            }
            VoiceNoteAnimation.MUSIC -> {
                MusicNoteParticles(amplitude = audioAmplitude)
            }
        }
    }
}
```

### Configuración

```
┌─────────────────────────────────────┐
│  ‹ Notas de Voz Mágicas             │
├─────────────────────────────────────┤
│                                     │
│  [✓] Activar animaciones mágicas    │
│                                     │
│  Tipo de animación:                 │
│  ● Nubecitas ☁️                     │
│  ○ Estrellitas ⭐                   │
│  ○ Cerditas 🐷                      │
│  ○ Koalitas 🐨                      │
│  ○ Notas musicales 🎵               │
│                                     │
│  Intensidad: ────●───────           │
│                                     │
│  [✓] Sincronizar con audio          │
│  [✓] Mostrar durante grabación      │
│                                     │
│  [  Vista Previa  ]                 │
└─────────────────────────────────────┘
```

---

## 📅 Calendario de Fechas Especiales

### Vista del Calendario

```
┌─────────────────────────────────────┐
│  ‹ Fechas Especiales      [+ Añadir]│
├─────────────────────────────────────┤
│         Marzo 2026                  │
│  Do  Lu  Ma  Mi  Ju  Vi  Sá         │
│                                     │
│   1   2   3   4   5   6   7         │
│       🎂              💕            │
│                                     │
│   8   9  10  11  12  13  14         │
│                      🎉            │
│                                     │
│  15  16  17  18  19  20  21         │
│              🐷🤗🐨                  │
│                                     │
│  22  23  24  25  26  27  28         │
│       💕                            │
│                                     │
│  29  30  31                         │
│                                     │
├─────────────────────────────────────┤
│  Leyenda:                           │
│  🎂 Cumpleaños   💕 Aniversario     │
│  🎉 Evento       🐷🤗🐨 Especial     │
└─────────────────────────────────────┘
```

### Tipos de Fechas

| Tipo | Icono | Color | Recordatorio |
|------|-------|-------|--------------|
| **Cumpleaños** | 🎂 | Rosa | 1 día antes, 1 semana antes |
| **Aniversario** | 💕 | Rojo | 1 día antes, 1 mes antes |
| **Evento Personal** | 🎉 | Morado | Personalizable |
| **Primera Vez** | 🐷🤗🐨 | Dorado | 1 día antes |
| **Recordatorio** | 📅 | Azul | Personalizable |

### Añadir Fecha Especial

```
┌─────────────────────────────────────┐
│  ‹ Nueva Fecha Especial             │
├─────────────────────────────────────┤
│                                     │
│  Tipo:                              │
│  ○ Cumpleaños    ○ Aniversario      │
│  ○ Evento        ○ Recordatorio     │
│                                     │
│  Título:                            │
│  [_________________________]        │
│                                     │
│  Contacto (opcional):               │
│  [Seleccionar contacto       ▼]     │
│                                     │
│  Fecha:                             │
│  [15 / 03 / 2026]    [📅]           │
│                                     │
│  Repetir:                           │
│  ○ Una vez   ● Anual   ○ Mensual    │
│                                     │
│  Recordatorio:                      │
│  [✓] 1 día antes                    │
│  [✓] 1 semana antes                 │
│  [ ] 1 mes antes                    │
│                                     │
│  Notas (opcional):                  │
│  [_________________________]        │
│  [_________________________]        │
│                                     │
│  [  💾 Guardar Fecha  ]             │
└─────────────────────────────────────┘
```

### Notificación de Fecha Especial

```
┌─────────────────────────────────────┐
│  🎉 ¡Hoy es un día especial!        │
│                                     │
│  💕 Aniversario con Juan Pérez      │
│  📅 15 de Marzo, 2024               │
│                                     │
│  ┌─────────────────────────────┐   │
│  │  🐷🤗🐨                      │   │
│  │  (animación de abrazo)       │   │
│  └─────────────────────────────┘   │
│                                     │
│  [ Enviar Mensaje ] [ Enviar Abrazo ]│
│                                     │
│  Sugerencia de mensaje:             │
│  "¡Feliz aniversario! 💕🐷🤗🐨"     │
└─────────────────────────────────────┘
```

### Contador Regresivo

```kotlin
@Composable
fun EventCountdown(
    eventDate: Long,
    eventName: String
) {
    val currentTime = remember { mutableStateOf(System.currentTimeMillis()) }
    val timeLeft = eventDate - currentTime.value
    
    val days = TimeUnit.MILLISECONDS.toDays(timeLeft)
    val hours = TimeUnit.MILLISECONDS.toHours(timeLeft) % 24
    
    Column {
        Text("Falta para: $eventName")
        Text("$days días, $hours horas")
        
        // Animación de mascotas
        if (days <= 7) {
            ExcitedPiggyAndKoala()
        }
    }
}
```

---

## 💕 Botón Mágico de Abrazo

### Ubicación en la UI

```
┌─────────────────────────────────────┐
│  ‹ Juan Pérez            📞 📹      │
├─────────────────────────────────────┤
│  (chat messages...)                 │
│                                     │
├─────────────────────────────────────┤
│  📎 🎤 💕 [ Escribe... ] ➤          │
│         ↑                           │
│    Botón de Abrazo                  │
└─────────────────────────────────────┘
```

### Animación al Presionar

**Secuencia de animación:**

1. **Fade out** del chat (0.3s)
2. **Fade in** de pantalla completa (0.3s)
3. **Cerdita y Koalita** aparecen en el centro (0.5s)
4. **Se acercan** lentamente (0.5s)
5. **Se abrazan** (0.3s)
6. **Corazones y destellitos** aparecen (1s)
7. **Mensaje opcional**: "Te envío un abrazo 🐷🤗🐨"
8. **Fade out** gradual (1s)
9. **Regreso al chat**

```kotlin
@Composable
fun MagicHugButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.hug_button),
            contentDescription = "Enviar abrazo",
            tint = Color(0xFFFF69B4)
        )
    }
}

@Composable
fun HugAnimation(
    onFinished: () -> Unit
) {
    val progress = animateFloatAsState(
        targetValue = if (isAnimating) 1f else 0f,
        animationSpec = tween(3000)
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f * progress.value))
    ) {
        // Cerdita y Koalita en el centro
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .scale(progress.value)
        ) {
            Image(
                painter = painterResource(R.drawable.piggy_hugging),
                contentDescription = null
            )
            Image(
                painter = painterResource(R.drawable.koala_hugging),
                contentDescription = null
            )
        }
        
        // Corazones flotando
        repeat(20) { i ->
            FloatingHeart(
                index = i,
                progress = progress.value
            )
        }
        
        // Destellitos dorados
        GoldenSparkles(progress = progress.value)
    }
    
    LaunchedEffect(progress.value) {
        if (progress.value == 0f) {
            onFinished()
        }
    }
}
```

### Tipos de Abrazos

```
┌─────────────────────────────────────┐
│  ‹ Tipo de Abrazo                   │
├─────────────────────────────────────┤
│                                     │
│  ● Normal (🐷🤗🐨)                   │
│    Cerdita y Koalita abrazándose    │
│    Corazones pequeños               │
│                                     │
│  ○ Romántico (💕🐷💕🐨💕)           │
│    Muchos corazones rosados         │
│    Destellitos dorados              │
│    Brillo especial                  │
│                                     │
│  ○ Amistad (⭐🐷⭐🐨⭐)              │
│    Estrellitas brillantes           │
│    Colores azules y amarillos       │
│                                     │
│  ○ Grupal (🐷🐷🤗🐨🐨)              │
│    Múltiples cerditas y koalitas    │
│    Arcoíris de fondo                │
│                                     │
│  [  Guardar Predeterminado  ]       │
└─────────────────────────────────────┘
```

### Configuración

```
┌─────────────────────────────────────┐
│  ‹ Botón de Abrazo                  │
├─────────────────────────────────────┤
│                                     │
│  [✓] Mostrar botón en chats         │
│                                     │
│  Tipo de abrazo predeterminado:     │
│  ● Normal   ○ Romántico             │
│  ○ Amistad  ○ Grupal                │
│                                     │
│  [✓] Mostrar mensaje automático     │
│  Mensaje: "Te envío un abrazo 🐷🤗🐨"│
│                                     │
│  [✓] Sonido de abrazo               │
│  [✓] Vibración suave                │
│                                     │
│  [  Vista Previa  ]                 │
└─────────────────────────────────────┘
```

---

## 🎯 Resumen de Implementación

| Característica | Complejidad | Tiempo Est. | Prioridad |
|----------------|-------------|-------------|-----------|
| Efectos palabras | Media | 1 semana | Alta |
| Fondos personalizados | Media | 1 semana | Alta |
| Notas de voz mágicas | Alta | 2 semanas | Media |
| Calendario | Media | 1 semana | Media |
| Botón de abrazo | Baja | 3 días | Alta |

---

**Última actualización:** Marzo 2026
