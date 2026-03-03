# 🎯 NOSTRCHAT - Visión Completa del Proyecto

> **Chat descentralizado con mascotas adorables, temas personalizables y stickers únicos** 🐷🐨🌸

---

## 📱 Flujo Completo de la App

### 1️⃣ Onboarding - Creación de Perfil

```
┌─────────────────────────────────────┐
│     👋 Bienvenido a NostrChat       │
│         🐷 🌸 🐨                    │
│                                     │
│  ┌─────────────────────────────┐   │
│  │  Tu nombre de usuario:      │   │
│  │  [_________________]        │   │
│  │                             │   │
│  │  Elige tu avatar:           │   │
│  │                             │   │
│  │  ┌─────┐  ┌─────┐  ┌─────┐ │   │
│  │  │ 📷  │  │ 🐷  │  │ 🐨  │ │   │
│  │  │Foto │  │Cerdita│Koalita│ │   │
│  │  └─────┘  └─────┘  └─────┘ │   │
│  │                             │   │
│  │  O sube tu propia foto      │   │
│  └─────────────────────────────┘   │
│                                     │
│     [  ✨ Crear Perfil  ]           │
│                                     │
│  ⚠️ Tus claves se generan          │
│     automáticamente y se guardan    │
│     seguras en tu dispositivo       │
└─────────────────────────────────────┘

↓ Al crear perfil:
✅ Claves pública/privada generadas
✅ Private key → Android Keystore
✅ Perfil publicado en Nostr (Kind 0)
✅ Clave pública lista para copiar
```

---

### 2️⃣ Pantalla Principal - Lista de Chats

```
┌─────────────────────────────────────┐
│  NostrChat              👤 ⚙️       │
├─────────────────────────────────────┤
│                                     │
│  💬 No hay chats todavía            │
│     Inicia una conversación         │
│                                     │
│                          ┌────┐    │
│              ┌────┐      │ 💬 │    │
│              │ ➕ │      │    │    │
│              │    │      │    │    │
│              └────┘      └────┘    │
│           Añadir    Nuevo Chat     │
│          Contacto   (tus contacts) │
│                                     │
│  🐷 Tema actual: Cerdita            │
└─────────────────────────────────────┘
```

**Características:**
- Lista vacía inicialmente
- **DOS botones flotantes** en esquina inferior derecha:
  - ➕ **Añadir contacto**: Buscar por npub o NIP-05
  - 💬 **Nuevo chat**: Seleccionar de contactos existentes

---

### 3️⃣ Añadir Contacto

```
┌─────────────────────────────────────┐
│  ‹ Añadir Contacto                  │
├─────────────────────────────────────┤
│                                     │
│  🔍 Buscar por npub o NIP-05        │
│  ┌─────────────────────────────┐   │
│  │ npub1... o user@domain.com  │   │
│  │ [___________________]       │   │
│  └─────────────────────────────┘   │
│                                     │
│  💡 Tips:                           │
│  • npub empieza con "npub1"        │
│  • NIP-05 es como un email         │
│  • También puedes escanear QR       │
│                                     │
│       [  🔍 Buscar y Añadir  ]      │
└─────────────────────────────────────┘

↓ Al añadir:
✅ Importa nombre automáticamente
✅ Importa foto de perfil automáticamente
✅ Puedes renombrar localmente (solo para ti)
✅ Se guarda en tu follow list (Kind 3)
```

---

### 4️⃣ Chat Individual

```
┌─────────────────────────────────────┐
│  ‹ Juan Pérez            📞 📹      │
├─────────────────────────────────────┤
│                                     │
│  ┌─────────────────┐               │
│  │ Hola! Cómo estás?│  10:30 AM   │
│  └─────────────────┘               │
│                                     │
│               ┌──────────────────┐ │
│               │  Todo bien! 🐷    │ │
│               │  [sticker cerdita]│ │
│               └──────────────────┘ │
│                                     │
│  🔒 Encriptado de extremo a extremo │
│                                     │
├─────────────────────────────────────┤
│  📎 🎤 [ Escribe un mensaje...  ] ➤│
└─────────────────────────────────────┘
```

**Features:**
- Mensajes encriptados (NIP-17)
- **Enviar:**
  - 📝 Texto
  - 📸 Imágenes
  - 🎤 Audios (voice notes)
  - 🎥 Videos
  - 📁 Archivos
  - 🎭 Stickers animados
- Reacciones con emojis
- Responder mensajes (threads)
- Forward de mensajes
- Indicador "escribiendo..."

---

### 5️⃣ Ajustes (Organizados por Categoría)

```
┌─────────────────────────────────────┐
│  ‹ Ajustes                          │
├─────────────────────────────────────┤
│                                     │
│  👤 Usuario                         │
│     Nombre, foto, avatar, claves   │
│  ─────────────────────────────────  │
│  🔐 Privacidad y Seguridad          │
│     PIN, biometría, bloqueados     │
│  ─────────────────────────────────  │
│  🔔 Notificaciones                  │
│     Sonido, vibración, DND         │
│  ─────────────────────────────────  │
│  🎨 Apariencia y Temas              │
│     7 temas + animaciones          │
│  ─────────────────────────────────  │
│  📡 Relays                          │
│     Conectar/desconectar relays    │
│  ─────────────────────────────────  │
│  ℹ️ Acerca de                       │
│     Versión, licencias, GitHub     │
└─────────────────────────────────────┘
```

---

## 🎨 Los 8 Temas Disponibles

| # | Tema | Icono | Colores | Animaciones |
|---|------|-------|---------|-------------|
| 1 | **Claro** | ☀️ | Blanco, púrpura | Ninguna |
| 2 | **Noche Clara** | 🌙 | Gris oscuro, púrpura claro | Ninguna |
| 3 | **Noche Oscura (OLED)** | 🌑 | Negro puro | Ninguna |
| 4 | **Claro Opaco** | 🌫️ | Semitransparente | Blur/frosted glass |
| 5 | **Cerdita** | 🐷 | Rosa, amarillo, coral | 🐷 Cerditas flotando |
| 6 | **Koalita** | 🐨 | Azul, verde eucalipto | 🐨 Koalitas + 🍃 hojas |
| 7 | **Floral** | 🌸 | Rosa, menta, lavanda | 🌸 Flores + ☁️ nubes + 🦋 mariposas |
| 8 | **Mix** | 🐷🐨 | Gradiente rosa-azul | 🐷🐨🌸 Todos los elementos |

---

## 🎭 Stickers

### Creador de Stickers Personales

```
┌─────────────────────────────────────┐
│  ‹ Crear Sticker                    │
├─────────────────────────────────────┤
│                                     │
│  1️⃣ Seleccionar Imagen              │
│     [📷 Tomar] [🖼️ Galería]        │
│                                     │
│  2️⃣ Recortar                        │
│     [Auto] [Manual]                 │
│                                     │
│  3️⃣ Añadir Borde                    │
│     ○ Blanco                        │
│     ○ 🌸 Floral                     │
│     ○ 💕 Corazones                  │
│     ○ ⭐ Estrellitas                │
│     ○ 🫧 Burbujas                   │
│                                     │
│  4️⃣ Efectos (opcional)              │
│     [✓] Brillo                      │
│     [ ] Sombra                      │
│     [ ] Destellos                   │
│     [ ] Purpurina                   │
│                                     │
│     [  💾 Guardar Sticker  ]        │
└─────────────────────────────────────┘
```

### Packs Oficiales (100+ Stickers)

| Pack | Icono | Cantidad | Animados |
|------|-------|----------|----------|
| **🐷 Cerdita** | 🐷 | 24 | ✅ Todos |
| **🐨 Koalita** | 🐨 | 24 | ✅ Todos |
| **🌸 Flores** | 🌸 | 20 | ✅ Todos |
| **☁️ Nubes** | ☁️ | 16 | ✅ Todos |
| **💕 Corazones** | 💕 | 20 | ✅ Todos |

**Total:** 104 stickers animados oficiales

### Ejemplos de Stickers Animados

**Pack Cerdita:**
- 🐷😊 Salta de alegría
- 🐷😍 Corazones alrededor
- 🐷😴 Ronquidos "Zzz"
- 🐷💕 Corazón latiendo

**Pack Flores:**
- 🌸 Pétalos flotando
- 🌻 Girando suavemente
- 🌷 Balanceándose
- 🦋 Mariposa volando

---

## 📦 Features Completas

### Mensajería Básica
- [x] Textos encriptados (NIP-17)
- [ ] Imágenes
- [ ] Audios (voice notes)
- [ ] Videos
- [ ] Archivos
- [ ] Reacciones
- [ ] Threads
- [ ] Forward

### Stickers y Diversión
- [ ] 🎨 Creador de stickers
- [ ] 🐷 Pack Cerdita (24 stickers)
- [ ] 🐨 Pack Koalita (24 stickers)
- [ ] 🌸 Pack Flores (20 stickers)
- [ ] ☁️ Pack Nubes (16 stickers)
- [ ] 💕 Pack Corazones (20 stickers)
- [ ] ✨ Stickers animados
- [ ] 🖼️ Bordes decorativos (floral, corazones, estrellas, burbujas)
- [ ] 🔍 Búsqueda de stickers
- [ ] ❤️ Stickers favoritos

### Comunicación Sincrónica
- [ ] 📞 Llamadas de voz (WebRTC)
- [ ] 📹 Llamadas de video

### Personalización
- [ ] ☀️ Tema Claro
- [ ] 🌙 Tema Noche Clara
- [ ] 🌑 Tema Noche Oscura (OLED)
- [ ] 🌫️ Tema Claro Opaco
- [ ] 🐷 Tema Cerdita
- [ ] 🐨 Tema Koalita
- [ ] 🌸 Tema Floral
- [ ] 🐷🐨 Tema Mix
- [ ] Animaciones personalizables
- [ ] Material You (Android 12+)

### Social
- [ ] Contactos (import auto)
- [ ] Renombrar contactos
- [ ] Chats grupales
- [ ] Estados (tipo WhatsApp)
- [ ] Múltiples cuentas

### Privacidad
- [x] Claves en Android Keystore
- [ ] PIN/biometría
- [ ] Exportar claves
- [ ] NIP-17 (sin metadata)

---

## 📅 Roadmap Detallado

### Fase 1 - MVP (Semanas 1-4)
- [x] Setup del proyecto
- [ ] Generación de claves
- [ ] Onboarding con avatar (🐷/🐨/foto)
- [ ] Lista de chats con doble FAB
- [ ] Añadir contactos (import auto)
- [ ] Renombrar contactos
- [ ] Chats individuales
- [ ] Ajustes por categorías

### Fase 2 - Multimedia (Semanas 5-8)
- [ ] Imágenes
- [ ] Audios (voice notes)
- [ ] Videos
- [ ] Archivos
- [ ] NIP-17 (Gift Wrap)

### Fase 3 - Temas (Semanas 9-14)
- [ ] ☀️ Claro
- [ ] 🌙 Noche Clara
- [ ] 🌑 OLED
- [ ] 🌫️ Opaco
- [ ] 🐷 Cerdita
- [ ] 🐨 Koalita
- [ ] 🌸 **Floral (NUEVO)**
- [ ] 🐷🐨 Mix
- [ ] Selector de temas

### Fase 4 - Llamadas y Stickers (Semanas 15-18)
- [ ] 📞 Llamadas de voz
- [ ] 📹 Llamadas de video
- [ ] 🎨 **Creador de stickers**
- [ ] 🐷 Pack Cerdita
- [ ] 🐨 Pack Koalita
- [ ] 🌸 Pack Flores
- [ ] ☁️ Pack Nubes
- [ ] 💕 Pack Corazones
- [ ] 🖼️ Bordes decorativos
- [ ] ✨ Stickers animados

### Fase 5 - Launch (Semanas 19-20)
- [ ] Beta testing
- [ ] Publicación (F-Droid, GitHub, Play Store)

---

## 🛠️ Stack Técnico

```
Kotlin 2.0+
    ↓
Jetpack Compose (UI moderna)
    ↓
MVVM + Clean Architecture
    ↓
┌─────────────┬──────────────┬─────────────┐
│   Domain    │     Data     │     UI      │
│  (Models)   │  (Room,      │ (Screens,   │
│  (UseCases) │   Rhodium)   │ ViewModels) │
└─────────────┴──────────────┴─────────────┘
    ↓              ↓               ↓
 Nostr SDK    Room DB       Material 3
 (Rhodium)   (Messages)    + Animaciones
                ↓
         Stickers DB
         (Imágenes locales)
```

---

## 🎯 Lo Esencial (Tu Visión Original)

✅ **Onboarding:**
- Nombre + avatar (cerdita/koalita/foto)
- Generación automática de claves

✅ **Pantalla de chats:**
- Lista vacía inicialmente
- **DOS botones flotantes** (añadir contacto + nuevo chat)

✅ **Contactos:**
- Importa nombre y foto automáticamente
- Renombrar localmente

✅ **Ajustes:**
- Categorías: Usuario, Privacidad, Notificaciones, Apariencia, Relays, Acerca de
- Copiar clave pública desde ajustes

✅ **Temas:**
- Noche clara, Noche oscura, Claro, Claro opaco
- **🐷 Cerdita** (con animaciones)
- **🐨 Koalita** (con animaciones)
- **🌸 Floral (NUEVO)** (flores, nubes, bordes floreados)
- **🐷🐨 Mix**

✅ **Multimedia:**
- Audios, fotos, videos, archivos

✅ **Llamadas:**
- Voz y video

✅ **NUEVO - Stickers:**
- Creador de stickers personalizados
- 5 packs oficiales (100+ stickers animados)
- Bordes decorativos (floral, corazones, estrellas, burbujas)
- Stickers animados (saltan, giran, flotan, laten)

---

## 📚 Documentación Completa

| Documento | Propósito |
|-----------|-----------|
| `README.md` | Visión general |
| `PLAN.md` | Roadmap de 20 semanas |
| `THEMES_GUIDE.md` | Guía de 7 temas |
| `FLORAL_THEME_AND_STICKERS.md` | 🌸 Tema floral + stickers (NUEVO) |
| `SPEC.md` | Especificación técnica |
| `COMPLETE_SUMMARY.md` | Visión completa con flujos |
| `GETTING_STARTED.md` | Cómo empezar |

---

## 🚀 ¿Qué Sigue?

1. **Abrir Android Studio** → Cargar proyecto
2. **Semana 1:**
   - Generación de claves Nostr
   - Pantalla de onboarding con avatares
   - Publicar perfil en Nostr

3. **Semana 2:**
   - Lista de chats con doble FAB
   - Añadir contactos
   - Importar perfiles

4. **Semana 10-11:**
   - 🌸 **Tema Floral**
   - 🎨 **Creador de stickers**

5. **Semana 18:**
   - ✨ **Todos los packs de stickers**

---

**¡Tu visión está 100% documentada!** 🎉

Todo lo que pediste está aquí:
- ✅ Onboarding con avatar
- ✅ Doble botón flotante
- ✅ Importar perfiles
- ✅ Renombrar contactos
- ✅ Ajustes completos
- ✅ 8 temas (incluyendo Floral 🌸)
- ✅ **Stickers personalizados**
- ✅ **5 packs oficiales (100+ stickers)**
- ✅ **Bordes decorativos**
- ✅ **Stickers animados**
- ✅ Multimedia completa
- ✅ Llamadas

**¡Vamos a crear la app de chat más linda y privada del mundo!** 🐷🐨🌸💕
