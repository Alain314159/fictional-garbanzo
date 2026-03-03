# NostrChat - Chat Descentralizado

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Language](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)
[![Protocol](https://img.shields.io/badge/Protocol-Nostr-purple.svg)](https://nostr.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Una aplicación de chat **100% gratuita, descentralizada y resistente a la censura** construida sobre el protocolo Nostr, con adorables mascotas 🐷🐨.

## 🚀 Características

### Actuales (Planificadas)
- ✅ Mensajería directa encriptada (NIP-17 Gift Wrap)
- ✅ Chats grupales encriptados (NIP-44)
- ✅ Sin servidores centrales - usa relays públicos
- ✅ Identidad soberana (tus claves, tu identidad)
- ✅ Resistente a la censura
- ✅ Sin teléfono ni email requerido
- ✅ **Avatares adorables: Cerdita 🐷 y Koalita 🐨**
- ✅ **Múltiples temas personalizables**
- ✅ **Animaciones de fondo con mascotas**

### Multimedia
- 📸 Envío de imágenes y fotos
- 🎤 Mensajes de audio (voice notes)
- 🎥 Videos
- 📁 Archivos de cualquier tipo

### Comunicación Avanzada
- 📞 Llamadas de voz (WebRTC)
- 📹 Llamadas de video
- 💬 Mensajes temporales autodestructibles
- 🎭 Stickers de cerdita y koalita
- ✨ **Efectos mágicos en palabras románticas** ("te amo" → destellitos dorados)
- 💕 **Botón Mágico de Abrazo** (🐷🤗🐨 cerdita y koalita abrazándose)

### Personalización
- 🎨 **4 temas temáticos** (Cerdita 🐷, Koalita 🐨, Floral 🌸, Mix 🐷🐨)
- 🐷 Animaciones de cerditas flotando
- 🐨 Animaciones de koalitas y hojas de eucalipto
- 🌸 Tema floral con flores cayendo y bordes floreados
- ☁️ Nubecitas y mariposas animadas
- 🖼️ **Fondos de chat personalizados** (predeterminados y propios)
- 🎨 **Fondos animados** con mascotas que saludan al abrir chat
- 🔔 Notificaciones personalizables
- 🌐 Material You (Android 12+)

### Stickers y Diversión
- 🎨 **Creador de stickers personalizados**
- 🐷 Packs oficiales de Cerdita
- 🐨 Packs oficiales de Koalita
- 🌸 Packs de flores animadas
- ☁️ Packs de nubes con caritas
- 💕 Packs de corazones brillantes
- 🖼️ Bordes floreados y decorativos
- ✨ Stickers animados (saltan, giran, flotan)
- 🎤 **Notas de voz mágicas** con animaciones (nubecitas, estrellitas, mascotas)
- 📅 **Calendario de fechas especiales** (cumpleaños, aniversarios, recordatorios)

## 📋 Requisitos

- Android 8.0 (API 26) o superior
- Conexión a internet (WiFi o datos)
- Al menos 50MB de espacio libre

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────────────────────────┐
│                    UI Layer (Jetpack Compose)                │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │   Screens   │  │ Components  │  │   Navigation        │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
├─────────────────────────────────────────────────────────────┤
│                 ViewModel Layer (MVVM Pattern)               │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │   State     │  │   Events    │  │    Business Logic   │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
├─────────────────────────────────────────────────────────────┤
│                    Domain Layer                              │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │  Use Cases  │  │  Repository │  │    Models           │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
├─────────────────────────────────────────────────────────────┤
│                    Data Layer                                │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │  Nostr SDK  │  │   Room DB   │  │    DataStore        │  │
│  │  (Rhodium)  │  │  (Messages) │  │    (Settings)       │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
├─────────────────────────────────────────────────────────────┤
│                    Network Layer                             │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │              WebSocket Connections to Relays            │ │
│  │    wss://relay1.nostr.com  wss://relay2.damus.io ...    │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

## 🛠️ Stack Tecnológico

| Categoría | Tecnología |
|-----------|------------|
| **Lenguaje** | Kotlin 2.0+ |
| **UI** | Jetpack Compose + Material 3 |
| **Arquitectura** | MVVM + Clean Architecture |
| **Nostr SDK** | Rhodium (Kotlin Multiplatform) |
| **Base de Datos** | Room (SQLite) |
| **Async** | Kotlin Coroutines + Flow |
| **DI** | Hilt / Koin |
| **Navegación** | Navigation Compose |
| **Red** | OkHttp + WebSocket |
| **Criptografía** | LibSodium + Secp256k1 |

## 📦 Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/com/nostrchat/app/
│   │   ├── ui/                    # UI Layer
│   │   │   ├── screens/           # Pantallas completas
│   │   │   ├── components/        # Componentes reutilizables
│   │   │   ├── theme/             # Tema y colores
│   │   │   └── navigation/        # Navegación
│   │   ├── domain/                # Domain Layer
│   │   │   ├── model/             # Modelos de dominio
│   │   │   ├── repository/        # Interfaces de repositorios
│   │   │   └── usecase/           # Casos de uso
│   │   ├── data/                  # Data Layer
│   │   │   ├── local/             # Room, DataStore
│   │   │   ├── remote/            # Nostr SDK, Relays
│   │   │   └── repository/        # Implementaciones
│   │   └── di/                    # Inyección de dependencias
│   └── res/                       # Recursos Android
├── build.gradle.kts
└── ...
```

## 🔐 Seguridad y Privacidad

- **Encriptación de extremo a extremo**: NIP-44 para mensajes
- **Gift Wrap (NIP-17)**: Oculta metadatos de quién habla con quién
- **Claves locales**: Las private keys nunca salen del dispositivo
- **Sin metadata centralizada**: Los relays no saben quién eres
- **Tor opcional**: Para máxima privacidad

## 🌐 Protocolo Nostr

Nostr (Notes and Other Stuff Transmitted by Relays) es un protocolo abierto y descentralizado para redes sociales.

### Conceptos Clave

| Concepto | Descripción |
|----------|-------------|
| **Public Key (npub)** | Tu identidad pública (compartible) |
| **Private Key (nsec)** | Tu contraseña maestra (NUNCA compartir) |
| **Relay** | Servidor que retransmite mensajes |
| **Evento** | Cualquier cosa en Nostr (mensaje, perfil, etc.) |
| **Kind** | Tipo de evento (1=nota, 4=DM, 44=chat grupal) |

### NIPs Implementados

| NIP | Nombre | Estado |
|-----|--------|--------|
| NIP-01 | Protocolo básico | ✅ |
| NIP-04 | DMs encriptados (legacy) | ✅ |
| NIP-17 | DMs privados (Gift Wrap) | ✅ |
| NIP-44 | Encriptación mejorada | ✅ |
| NIP-59 | Gift wrapping | ✅ |

## 🚀 Roadmap

### Fase 1 - MVP (Semanas 1-4)
- [x] Setup del proyecto
- [ ] Generación de claves
- [ ] **Onboarding: Crear perfil con nombre + avatar (cerdita/koalita)**
- [ ] Conexión a relays
- [ ] Lista de chats con **dobles botones flotantes** (añadir contacto + nuevo chat)
- [ ] **Contactos: Importar nombre y foto automáticamente + renombrar localmente**
- [ ] Enviar/recibir mensajes básicos
- [ ] **Ajustes organizados por categorías** (Usuario, Privacidad, Notificaciones, Apariencia, Relays, Acerca de)

### Fase 2 - Core (Semanas 5-8)
- [ ] Encriptación NIP-17 (Gift Wrap)
- [ ] Chat 1-a-1 completo (reacciones, threads, forward)
- [ ] **Envío de imágenes**
- [ ] **Fondos de chat personalizados**
- [ ] **Mensajes de audio (voice notes)**
- [ ] **Notas de voz mágicas** (animaciones de nubes, estrellas, mascotas)
- [ ] **Envío de videos y archivos**
- [ ] **✨ Efectos mágicos en palabras románticas** ("te amo" → destellitos)
- [ ] Persistencia local

### Fase 3 - Temas y Features (Semanas 9-14)
- [ ] **Tema Claro**
- [ ] **Tema Noche Clara**
- [ ] **Tema Noche Oscura (OLED)**
- [ ] **Tema Claro Opaco (frosted glass)**
- [ ] **🐷 Tema Cerdita** (colores rosa/amarillo + animaciones de cerditas)
- [ ] **🐨 Tema Koalita** (colores azul/verde + animaciones de koalitas)
- [ ] **🌸 Tema Floral** (flores cayendo, nubecitas, bordes floreados)
- [ ] **🐷🐨 Tema Mix** (combinación de ambos)
- [ ] Selector de temas con vista previa
- [ ] Chats grupales
- [ ] **📅 Calendario de fechas especiales**
- [ ] Notificaciones push
- [ ] Múltiples cuentas

### Fase 4 - Llamadas y Stickers (Semanas 15-18)
- [ ] **📞 Llamadas de voz (WebRTC)**
- [ ] **📹 Llamadas de video**
- [ ] **🎨 Creador de stickers personalizados**
- [ ] **✨ Packs de stickers animados** (cerditos, koalitas, flores, nubes, corazones)
- [ ] **🖼️ Bordes y marcos decorativos** (floreados, corazones, estrellitas)
- [ ] **💕 Botón Mágico de Abrazo** (🐷🤗🐨)
- [ ] Optimización y accesibilidad
- [ ] Mensajes temporales

### Fase 5 - Launch (Semanas 19-20)
- [ ] Beta testing
- [ ] Publicación en F-Droid, GitHub, Play Store

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Este proyecto es open source.

1. Fork el repo
2. Crea una rama (`git checkout -b feature/nueva-feature`)
3. Commit (`git commit -m 'Añade nueva feature'`)
4. Push (`git push origin feature/nueva-feature`)
5. Abre un Pull Request

## 📄 Licencia

MIT License - ver [LICENSE](LICENSE) para detalles.

## 🔗 Recursos

- [Nostr Protocol Specs](https://github.com/nostr-protocol/nostr)
- [NIPs Oficiales](https://nips.nostr.com/)
- [Rhodium SDK](https://github.com/KotlinGeekDev/Rhodium)
- [Amethyst (referencia)](https://github.com/vitorpamplona/amethyst)

---

**Hecho con ❤️ para la libertad de comunicación**
