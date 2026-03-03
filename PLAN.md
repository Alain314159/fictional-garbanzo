# Plan de Desarrollo - Cerdita 💕

> **La app de chat MÁS ROMÁNTICA y MÁGICA del mundo** - Solo para ti y tu novia

## 📅 Visión General

**Objetivo**: Crear una app de chat descentralizada, privada y romántica usando el protocolo Nostr, diseñada exclusivamente para parejas.

**Timeline**: 16 semanas (4 meses) hasta tenerla completa.

**Nombre**: **Cerdita** 💕

**Icono**: 🐷🤗🐨 (Cerdita y Koalita abrazándose)

---

## 🎨 Concepto Visual Único

### Mascotas de la App
- **Cerdita** 🐷 - Tema rosado/amarillo (tú)
- **Koalita** 🐨 - Tema azul/gris (tu novia)
- **Floral** 🌸 - Tema de flores y nubes
- **Mix** 🐷🐨 - Combinación de ambos (juntos)

### Filosofía de Diseño
- UI romántica y acogedora
- Animaciones sutiles de fondo
- Colores suaves y agradables
- Experiencia emocional positiva
- Elementos kawaii y naturales
- **Momentos mágicos:** Efectos especiales en mensajes románticos
- **Personalización total:** Fondos, fechas especiales, sorpresas
- **100% privada:** Solo para ustedes dos

---

## 🎯 Fase 1: MVP (Semanas 1-4)

### Semana 1: Setup y Fundamentos

#### Día 1-2: Configuración del Proyecto
- [x] Crear proyecto Android en Android Studio
- [x] Configurar Gradle (Kotlin DSL)
- [x] Setup de módulos (app, core, data, domain)
- [x] Configurar Git y .gitignore
- [x] Crear estructura de carpetas
- [x] Añadir assets de cerdita y koalita (imágenes base)
- [x] **Cambiar nombre de la app a "Cerdita"**
- [ ] **Crear icono: Cerdita y Koalita abrazándose** (Pendiente - usar placeholder por ahora)

#### Día 3-4: Arquitectura Base
- [x] Crear clases base para MVVM
- [x] Configurar Hilt para DI (configurado en build.gradle)
- [x] Crear Application class (CerditaApplication)
- [x] Setup de Navigation Compose
- [x] Crear tema base (Material 3)

#### Día 5-7: Sistema de Claves + Onboarding Romántico
- [ ] Generación de claves (public/private) - **PENDIENTE: Integrar Rhodium SDK**
- [ ] Almacenamiento seguro de private keys (Android Keystore) - **PENDIENTE**
- [x] **Pantalla de creación de perfil romántico:**
  - [x] Input de nombre de usuario (o apodo cariñoso)
  - [ ] Subir foto de perfil O
  - [x] Seleccionar avatar (Cerdita 🐷 o Koalita 🐨)
  - [ ] **Frase romántica inicial** (opcional)
  - [x] Botón "Crear Perfil" → genera claves automáticamente (UI lista, falta lógica)
  - [ ] Guardar private key en Keystore
  - [ ] Mostrar opción de copiar public key (para compartir con tu novia)
- [ ] **Intercambio de claves:**
  - [ ] Mostrar QR con tu clave pública
  - [ ] Escanear QR de tu novia
  - [ ] O compartir npub manualmente
- [ ] Backup de claves (exportar/importar nsec)

**Entregables Semana 1:**
- ✅ Proyecto compilando
- ✅ Estructura MVVM funcionando
- ⏳ Generación de claves Nostr funcional (PENDIENTE: Rhodium SDK)
- ✅ Onboarding romántico con avatares (cerdita/koalita) - UI COMPLETADA
- ✅ Tema visual base - 4 TEMAS CREADOS
- ⏳ Icono de la app (Cerdita + Koalita) (PENDIENTE)

---

### Semana 2: Conexión a Relays + **Funcionamiento Offline**

#### Día 1-3: Setup de Rhodium SDK
- [ ] Integrar Rhodium en el proyecto
- [ ] Configurar conexión WebSocket a relays
- [ ] Lista de relays por defecto (5-10 públicos)
- [ ] Manejo de reconexión automática

**Relays recomendados para empezar:**
```kotlin
val defaultRelays = listOf(
    "wss://relay.damus.io",
    "wss://relay.nostr.band",
    "wss://nos.lol",
    "wss://relay.snort.social",
    "wss://purplepag.es",
    "wss://relay.primal.net",
    "wss://nostr.wine",
    "wss://eden.nostr.land"
)
```

#### Día 4-5: Sistema de Suscripciones
- [ ] Suscribirse a eventos de mensajes
- [ ] Filtrar por kind (tipo de evento)
- [ ] Manejo de EOSE (End of Stored Events)
- [ ] Rate limiting para no saturar relays

#### Día 6-7: **Funcionamiento Offline + Cola de Mensajes**
- [ ] **Base de datos local (Room):**
  - [ ] Guardar todos los mensajes localmente
  - [ ] Estados de entrega (pendiente, enviado, recibido, leído)
  - [ ] Cola de mensajes pendientes de envío
- [ ] **Enviar sin conexión:**
  - [ ] Guardar mensaje en base de datos
  - [ ] Marcar como "pendiente de envío"
  - [ ] Intentar enviar cuando haya conexión
  - [ ] Actualizar estado cuando se envíe
- [ ] **Recibir sin conexión:**
  - [ ] Sincronizar al recuperar conexión
  - [ ] Descargar mensajes almacenados en relays
  - [ ] Mostrar mensajes recibidos
- [ ] **Indicadores de estado:**
  - [ ] ⏳ Pendiente (sin conexión)
  - [ ] 📤 Enviado a relay
  - [ ] ✅ Recibido por tu novia
  - [ ] 👁️ Leído por tu novia

**Entregables Semana 2:**
- ✅ Conexión a relays estable
- ✅ Suscribirse a eventos
- ✅ Publicar notas públicas
- ✅ Reconexión automática
- ✅ **Funcionamiento offline completo**
- ✅ **Cola de mensajes pendiente**
- ✅ **Sincronización automática al conectar**

---

### Semana 3: Mensajería Básica + Contactos

#### Día 1-3: NIP-04 (DMs Legacy)
- [ ] Implementar encriptación NIP-04
- [ ] Crear eventos Kind 4 (DMs)
- [ ] Desencriptar mensajes recibidos
- [ ] Testear envío/recepción

```kotlin
// Ejemplo NIP-04
fun encryptMessage(content: String, recipientPubKey: String): String {
    val sharedSecret = calculateSharedSecret(privateKey, recipientPubKey)
    val iv = generateRandomIV()
    return aesEncrypt(content, sharedSecret, iv)
}
```

#### Día 4-5: **Lista de Contactos con Importación Automática**
- [ ] Obtener lista de follows (Kind 3)
- [ ] **Al añadir contacto:**
  - [ ] Importar automáticamente su nombre de usuario (de Kind 0)
  - [ ] Importar su foto de perfil (de Kind 0)
  - [ ] **Permitir editar nombre localmente** (cada usuario puede renombrar sus contactos)
- [ ] Añadir/eliminar contactos
- [ ] Buscar usuarios por npub o NIP-05

#### Día 6-7: UI de Chat
- [ ] Pantalla de conversación
- [ ] Bubble de mensajes (enviado/recibido)
- [ ] Input de texto con botón enviar
- [ ] Mostrar nombre personalizado del contacto

**Entregables Semana 3:**
- ✅ DMs encriptados (NIP-04)
- ✅ Lista de contactos con importación automática de perfil
- ✅ Capacidad de renombrar contactos localmente
- ✅ UI de chat básica
- ✅ Historial de conversaciones

---

### Semana 4: Persistencia, UX y **Ajustes**

#### Día 1-3: Base de Datos Room
- [ ] Definir entidades (Message, Contact, Chat, Profile)
- [ ] Crear DAOs
- [ ] Implementar repositorios
- [ ] Sync entre DB y relays
- [ ] **Guardar nombre personalizado de contactos**

```kotlin
@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val publicKey: String,
    val customName: String?,        // Nombre personalizado
    val importedName: String?,      // Nombre importado de Nostr
    val profilePictureUrl: String?,
    val isFollowed: Boolean,
    val isBlocked: Boolean,
    val addedAt: Long
)
```

#### Día 4-5: Estado y Offline
- [ ] Caché local de mensajes
- [ ] Mostrar mensajes sin conexión
- [ ] Cola de mensajes pendientes
- [ ] Sync al reconectar

#### Día 6-7: **Pantalla de Ajustes Completa**
- [ ] Navegación a ajustes desde pantalla principal
- [ ] **Secciones de ajustes:**
  - [ ] **👤 Usuario/Perfil:**
    - [ ] Ver/editar nombre de usuario
    - [ ] Cambiar foto de perfil
    - [ ] Cambiar avatar (cerdita/koalita)
    - [ ] Copiar clave pública (npub)
    - [ ] Exportar clave privada (nsec)
  - [ ] **🔐 Privacidad y Seguridad:**
    - [ ] Bloqueo con PIN/biometría
    - [ ] Ocultar contenido de notificaciones
    - [ ] Bloquear usuarios
  - [ ] **🔔 Notificaciones:**
    - [ ] Activar/desactivar notificaciones
    - [ ] Sonido de notificación
    - [ ] Vibración
  - [ ] **🎨 Apariencia y Temas:**
    - [ ] Selector de temas (ver Semana 9)
    - [ ] Tamaño de fuente
  - [ ] **📡 Relays:**
    - [ ] Ver relays conectados
    - [ ] Añadir/eliminar relays personalizados
  - [ ] **ℹ️ Acerca de:**
    - [ ] Versión de la app
    - [ ] Licencias
    - [ ] Enlace a GitHub

**Entregables Semana 4 (FIN MVP):**
- ✅ Persistencia local
- ✅ Mensajes offline
- ✅ UX básica completa
- ✅ **Ajustes completos organizados por categorías**
- ✅ **Perfil y claves accesibles desde ajustes**
- ✅ **APK funcional para testing**

---

## 🎯 Fase 2: Core (Semanas 5-8)

### Semana 5: NIP-17 (Gift Wrap) + Mejoras de Chat

- [ ] Implementar NIP-44 (encriptación mejorada)
- [ ] Implementar NIP-59 (gift wrapping)
- [ ] Migrar DMs de NIP-04 a NIP-17
- [ ] Ocultar metadatos (quién habla con quién)
- [ ] Indicadores de escritura (typing indicators)
- [ ] Estado de entrega (enviado/recibido/leído)

**Beneficios NIP-17:**
- Los relays no saben quién envía a quién
- Solo el destinatario puede abrir el "regalo"
- Metadata completamente oculta

### Semana 6: Chat 1-a-1 Completo + **Efectos Románticos Mejorados**

- [ ] Reacciones a mensajes (emoji reactions)
- [ ] Responder mensajes específicos (threads)
- [ ] Forward de mensajes
- [ ] Eliminar mensajes (para ti)
- [ ] Copiar mensaje
- [ ] Selección múltiple de mensajes

- [ ] **✨ Efectos Románticos en Palabras Especiales (MEJORADO):**
  - [ ] **Detectar palabras románticas (MÚLTIPLES VARIACIONES):**
    - [ ] **Amor:**
      - [ ] "te amo", "te amo mucho", "te amo demasiado", "te amo infinitamente"
      - [ ] "te quiero", "te quiero mucho", "te quiero un montón"
      - [ ] "te adoro", "te adoro mi vida", "te adoro corazón"
      - [ ] "eres el amor de mi vida", "eres mi amor", "mi amor", "amor mío"
      - [ ] "te amo princesa", "te amo bebé", "te amo mi reina"
      - [ ] "💕", "❤️", "🥰", "😍" (emojis de amor)
      - [ ] → 💛 Destellitos dorados + 💖 corazones flotando + ✨ brillo especial
    - [ ] **Belleza:**
      - [ ] "eres hermosa", "eres muy hermosa", "eres la más hermosa"
      - [ ] "eres bella", "eres bellísima", "qué bella eres"
      - [ ] "eres preciosa", "eres preciosa mi vida"
      - [ ] "eres bonita", "eres muy bonita", "qué bonita eres"
      - [ ] "eres hermosa princesa", "hermosa bebé", "bella reina"
      - [ ] "me encantas", "me encantas mucho", "me tienes loco"
      - [ ] → ✨ Brillitos alrededor + 🌟 estrellitas + 💫 destellos
    - [ ] **Buenos días (varias formas):**
      - [ ] "buenos días", "buen día", "feliz día", "que tengas lindo día"
      - [ ] "buenos días princesa", "buenos días hermosa", "buenos días amor"
      - [ ] "buenos días mi vida", "buenos días corazón", "buenos días bebé"
      - [ ] "que amanezcas bien", "espero tengas lindo día"
      - [ ] "🌅", "☀️", "🌞", "buen dia" (sin tilde)
      - [ ] → 🌅 Amanecer con pajaritos + ☀️ rayos de sol + 🐦 pajaritos cantando
    - [ ] **Buenas noches (varias formas):**
      - [ ] "buenas noches", "buenas noches princesa", "buenas noches amor"
      - [ ] "buenas noches mi vida", "buenas noches corazón", "buenas noches bebé"
      - [ ] "que descanses", "dulces sueños", "sueña conmigo"
      - [ ] "que sueñes con los angelitos", "hasta mañana"
      - [ ] "🌙", "⭐", "💫", "buenas noches"
      - [ ] → 🌙 Luna + ⭐ estrellitas brillando + 💫 polvo de estrellas
    - [ ] **Cumpleaños:**
      - [ ] "feliz cumpleaños", "feliz cumple", "feliz día", "feliz día princesa"
      - [ ] "que cumplas muchos más", "te amo en tu día"
      - [ ] "🎂", "🎉", "🎈", "🎁"
      - [ ] → 🎉 Confeti + 🎈 globos + 🎁 regalos + ✨ fuegos artificiales
    - [ ] **Extrañar:**
      - [ ] "te extraño", "te extraño mucho", "te extraño demasiado"
      - [ ] "te echo de menos", "me haces falta", "me haces mucha falta"
      - [ ] "quiero verte", "quiero estar contigo", "quiero abrazarte"
      - [ ] "💭", "😢", "😔"
      - [ ] → 💭 Nubecita triste + 🌧️ lluvia suave + 😢 lágrimas brillantes
    - [ ] **Gracias:**
      - [ ] "gracias", "gracias mi vida", "gracias amor", "gracias princesa"
      - [ ] "te lo agradezco", "mil gracias", "muchas gracias"
      - [ ] "🙏", "💕"
      - [ ] → 💕 Corazones pequeños + ✨ destellitos de gratitud
    - [ ] **Perdón:**
      - [ ] "perdón", "perdóname", "lo siento", "lo siento mucho"
      - [ ] "disculpa", "no fue mi intención", "perdón princesa"
      - [ ] "😢", "😔", "🥺"
      - [ ] → 😢 Carita triste + ✨ brillo de arrepentimiento + 💔 corazón roto que se repara
    - [ ] **Felicidades/Logros:**
      - [ ] "felicidades", "felicidades amor", "felicidades princesa"
      - [ ] "lo lograste", "estoy orgulloso", "muy orgulloso de ti"
      - [ ] "👏", "🏆", "⭐"
      - [ ] → 🎊 Fuegos artificiales + ⭐ estrellas doradas + 👏 aplausos
    - [ ] **Ánimos:**
      - [ ] "tú puedes", "ánimos", "ánimos mi vida", "tú puedes princesa"
      - [ ] "eres fuerte", "eres valiente", "confío en ti"
      - [ ] "💪", "✊", "🌟"
      - [ ] → 💪 Músculos animados + 🌟 estrella de poder + ✊ puño de fuerza
    - [ ] **Te voy a ver:**
      - [ ] "ya quiero verte", "nos vemos pronto", "pronto nos vemos"
      - [ ] "quiero abrazarte", "quiero besarte", "quiero estar contigo"
      - [ ] "🤗", "😘", "💕"
      - [ ] → 🤗 Abrazo animado + 😘 besos volando + 💕 corazones acelerados

  - [ ] **Configuración de efectos:**
    - [ ] Activar/desactivar efectos
    - [ ] Intensidad de animación (suave/normal/intensa)
    - [ ] **Añadir palabras personalizadas** (tus propios apodos, frases internas)
    - [ ] **Guardar frases especiales de ustedes**
  - [ ] **Implementación técnica:**
    - [ ] Detector de palabras en tiempo real (case-insensitive, sin tildes)
    - [ ] Sistema de partículas para cada efecto
    - [ ] No interferir con rendimiento del chat
    - [ ] Efectos configurables por categoría

**Entregables Semana 6:**
- ✅ Chat 1-a-1 completo
- ✅ **50+ variaciones de palabras románticas**
- ✅ **Efectos visuales únicos para cada categoría**
- ✅ **Frases personalizables de ustedes dos**
- ✅ Configuración completa de efectos

### Semana 7: **Multimedia - Imágenes y Fondos Personalizados**

- [ ] **Envío de imágenes:**
  - [ ] Seleccionar de galería
  - [ ] Tomar foto con cámara
  - [ ] Vista previa antes de enviar
  - [ ] Compresión de imágenes
- [ ] **NIP-96 (HTTP File Storage - Blossom):**
  - [ ] Subir imágenes a relay compatible
  - [ ] Obtener URL de la imagen
  - [ ] Enviar URL en mensaje encriptado
- [ ] **Visualización:**
  - [ ] Mostrar imágenes en chat
  - [ ] Zoom con pinch-to-zoom
  - [ ] Guardar imagen
  - [ ] Compartir imagen

- [ ] **🖼️ Fondos de Chat Personalizados:**
  - [ ] **Fondos predeterminados:**
    - [ ] 🐷 Cerditas durmiendo
    - [ ] 🐨 Koalitas en árboles
    - [ ] 🌸 Jardín de flores
    - [ ] ☁️ Cielo con nubes
    - [ ] 🌙 Noche estrellada
    - [ ] 🌈 Arcoíris
    - [ ] 💕 Corazones flotando
  - [ ] **Fondos personalizados:**
    - [ ] Subir imagen de galería
    - [ ] Ajustar brillo/blur
    - [ ] Centrar/escalar imagen
  - [ ] **Fondos por contacto:**
    - [ ] Asignar fondo diferente a cada chat
    - [ ] Fondo predeterminado para nuevos chats
  - [ ] **Fondos animados:**
    - [ ] Cerditas y koalitas apareciendo/saludando al abrir chat
    - [ ] Nubes flotando lentamente
    - [ ] Estrellas brillando

### Semana 8: **Multimedia - Audio, Video, Archivos y Notas de Voz Mágicas**

- [ ] **Mensajes de audio (voice notes):**
  - [ ] Grabar audio manteniendo botón
  - [ ] Bloquear grabación (lock)
  - [ ] Visualizar onda de audio
  - [ ] Reproducir con velocidad ajustable
  - [ ] Pausar/reanudar reproducción
- [ ] **Envío de videos:**
  - [ ] Seleccionar de galería
  - [ ] Grabar video
  - [ ] Compresión de video
  - [ ] Reproducir en chat
- [ ] **Envío de archivos:**
  - [ ] Selector de archivos
  - [ ] Mostrar icono según tipo (PDF, DOC, ZIP, etc.)
  - [ ] Descargar archivo
  - [ ] Abrir con app externa
- [ ] **Progreso de subida/bajada:**
  - [ ] Barra de progreso
  - [ ] Cancelar subida
  - [ ] Reintentar si falla

- [ ] **🎤 Notas de Voz Mágicas:**
  - [ ] **Animaciones durante reproducción:**
    - [ ] ☁️ Nubecitas flotando mientras se reproduce
    - [ ] 🌟 Estrellitas brillando al ritmo del audio
    - [ ] 🐷 Cerditas saltando suavemente
    - [ ] 🐨 Koalitas balanceándose
    - [ ] 🎵 Notas musicales flotando
  - [ ] **Visualizador de audio mejorado:**
    - [ ] Onda de audio animada con colores del tema
    - [ ] Picos de audio con partículas
  - [ ] **Configuración:**
    - [ ] Activar/desactivar animaciones
    - [ ] Elegir tipo de animación (nubes, estrellas, mascotas)
    - [ ] Intensidad de efectos

**Entregables Fase 2:**
- ✅ Encriptación moderna (NIP-17)
- ✅ Chat 1-a-1 completo
- ✅ **Envío de imágenes**
- ✅ **Mensajes de audio**
- ✅ **Envío de videos y archivos**
- ✅ Búsqueda y backup

---

## 🎯 Fase 3: Features (Semanas 9-14)

### Semana 9: **Temas Personalizados - Parte 1**

- [ ] **Sistema de temas dinámicos:**
  - [ ] Arquitectura para cambiar temas en runtime
  - [ ] Guardar tema seleccionado en DataStore
  - [ ] Aplicar tema sin reiniciar app

- [ ] **🌞 Tema Claro (Default):**
  - [ ] Colores base Material 3
  - [ ] Fondo blanco/gris claro
  - [ ] Acentos en color primario

- [ ] **🌙 Tema Noche Clara:**
  - [ ] Fondo gris oscuro suave (#1A1A1A)
  - [ ] Texto blanco/gris claro
  - [ ] Acentos tenues

- [ ] **🌑 Tema Noche Oscura (OLED):**
  - [ ] Fondo negro puro (#000000)
  - [ ] Ahorro de batería en AMOLED
  - [ ] Contraste alto

- [ ] **🌫️ Tema Claro Opaco:**
  - [ ] Fondo semitransparente
  - [ ] Efecto blur (backdrop filter)
  - [ ] Estilo "frosted glass"

### Semana 10: **Temas Personalizados - Parte 2 (Cerdita y Koalita)**

- [ ] **🐷 Tema Cerdita:**
  - [ ] **Colores:**
    - Primario: Rosa (#FFB6C1)
    - Secundario: Amarillo suave (#FFFACD)
    - Fondo: Rosa muy claro (#FFF0F5)
    - Acentos: Coral (#FF7F50)
  - [ ] **Animaciones de fondo:**
    - Cerditas flotando suavemente en el fondo
    - Animación sutil (opacity 0.1-0.2)
    - Movimiento aleatorio lento
  - [ ] **Iconos personalizados:**
    - Cerdita en mensajes enviados
    - Huellitas de cerdo como bullets

- [ ] **🐨 Tema Koalita:**
  - [ ] **Colores:**
    - Primario: Azul grisáceo (#778899)
    - Secundario: Verde eucalipto (#8FBC8F)
    - Fondo: Gris azulado claro (#F0F8FF)
    - Acentos: Verde hoja (#90EE90)
  - [ ] **Animaciones de fondo:**
    - Koalitas flotando suavemente
    - Hojas de eucalipto cayendo
    - Animación sutil (opacity 0.1-0.2)
  - [ ] **Iconos personalizados:**
    - Koala en mensajes enviados
    - Hojas como bullets

- [ ] **🐷🐨 Tema Mix (Cerdita + Koalita):**
  - [ ] **Colores:**
    - Gradiente rosa-azul
    - Balance entre ambos temas
  - [ ] **Animaciones:**
    - Ambas mascotas apareciendo
    - Interacción entre cerdita y koalita
  - [ ] **Iconos:**
    - Alternancia entre ambos

- [ ] **🌸 Tema Floral:**
  - [ ] **Colores:**
    - Primario: Rosa pastel (#FFD1DC)
    - Secundario: Verde menta (#98FF98)
    - Fondo: Blanco rosado (#FFF5F7)
    - Acentos: Lavanda (#E6E6FA), Durazno (#FFE5CC)
  - [ ] **Animaciones de fondo:**
    - Flores cayendo suavemente (sakura, margaritas, girasoles)
    - Nubecitas blancas flotando
    - Mariposas ocasionales
    - Bordess floreados animados en los bordes de la pantalla
  - [ ] **Elementos decorativos:**
    - Bordes floreados en burbujas de mensaje
    - Esquinas con viñetas florales
    - Separadores con flores entre mensajes

### Semana 11: **Selector de Temas + Personalización**

- [ ] **Pantalla de selector de temas:**
  - [ ] Vista previa de cada tema
  - [ ] Click para aplicar
  - [ ] Animación de transición suave
- [ ] **Personalización adicional:**
  - [ ] Cambiar color de acento dentro de cada tema
  - [ ] Ajustar intensidad de animaciones
  - [ ] Activar/desactivar animaciones de fondo
- [ ] **Temas dinámicos (Android 12+):**
  - [ ] Soporte para Material You
  - [ ] Extraer colores del wallpaper
  - [ ] Adaptar tema de cerdita/koalita/floral a colores del sistema

**NOTA:** Se eliminaron los temas Claro, Noche Clara, Noche Oscura (OLED) y Opaco para simplificar la app. Solo quedan los 4 temas temáticos.

### Semana 12: **Características Románticas Extra**

- [ ] ~~Crear grupos (Kind 44)~~ **NO NECESARIO (solo son ustedes dos)**
- [ ] ~~Invitar miembros~~ **NO NECESARIO**
- [ ] ~~Admin de grupos~~ **NO NECESARIO**

- [ ] **💕 Características Exclusivas para Parejas:**
  - [ ] **Widget de amor:**
    - [ ] Widget en pantalla de inicio
    - [ ] Muestra tiempo juntos (días, horas, minutos)
    - [ ] Corazón que late al ritmo de mensajes
    - [ ] Click para enviar abrazo rápido
  - [ ] **Estado de relación:**
    - [ ] "En línea" / "Ocupada" / "Durmiendo"
    - [ ] Estado personalizado ("Pensando en ti", "Extrañándote")
    - [ ] Estados automáticos (se actualiza solo)
  - [ ] **Contador de días juntos:**
    - [ ] Fecha de inicio de relación
    - [ ] Contador en pantalla principal
    - [ ] Celebración automática de meses/años
  - [ ] **Álbum de recuerdos:**
    - [ ] Fotos especiales guardadas
    - [ ] Fecha especial de cada foto
    - [ ] Notas románticas en cada foto
  - [ ] **Cartas de amor:**
    - [ ] Escribir cartas largas
    - [ ] Guardar en la app
    - [ ] Enviar en fechas especiales
  - [ ] **Promesas/Retos:**
    - [ ] Lista de promesas de relación
    - [ ] Retos románticos para cumplir
    - [ ] Marcar como completados

**Entregables Semana 12:**
- ✅ **Características exclusivas para parejas**
- ✅ **Widget de amor**
- ✅ **Contador de días juntos**
- ✅ **Álbum de recuerdos**
- ✅ **Cartas de amor**

- [ ] **📅 Calendario de Fechas Especiales:**
  - [ ] **Pantalla del calendario:**
    - [ ] Vista mensual tipo calendario
    - [ ] Marcadores de colores por tipo de evento
    - [ ] Navegación entre meses
  - [ ] **Tipos de fechas especiales:**
    - [ ] 💕 Cumpleaños de contactos
    - [ ] 💑 Aniversarios (primera cita, boda, etc.)
    - [ ] 🎉 Eventos personalizados
    - [ ] 📅 Recordatorios especiales
  - [ ] **Características:**
    - [ ] Añadir fecha especial manualmente
    - [ ] Importar cumpleaños de contactos
    - [ ] Configurar recordatorios (1 día antes, 1 semana antes)
    - [ ] Notificación especial el día del evento
    - [ ] Sugerencia de sticker/regalo para la fecha
  - [ ] **Detalles visuales:**
    - [ ] Animación de cerdita/koalita en fechas especiales
    - [ ] Contador regresivo para eventos próximos
    - [ ] Historial de fechas celebradas
  - [ ] **Integración con chat:**
    - [ ] Recordatorio en chat el día especial
    - [ ] Sugerencia automática de mensaje/sticker
    - [ ] Efectos especiales en mensajes de felicitación

### Semana 13: **Notificaciones + Modo Ahorro de Datos**

- [ ] **Notificaciones sin servidores centrales:**
  - [ ] **Usar Firebase Cloud Messaging (gratuito):**
    - [ ] Configurar FCM en la app
    - [ ] No requiere servidores propios
    - [ ] Google maneja las notificaciones
  - [ ] **Alternativa: Notificaciones locales periódicas:**
    - [ ] WorkManager para chequear mensajes cada 15 min
    - [ ] Mostrar notificación local
    - [ ] Sin dependencia de Firebase
  - [ ] **Configuración de notificaciones:**
    - [ ] Activar/desactivar notificaciones
    - [ ] Sonido personalizado
    - [ ] Vibración
    - [ ] LED de notificación
    - [ ] Ocultar contenido en pantalla de bloqueo
    - [ ] Notificación solo cuando no estás en el chat

- [ ] **📊 Modo Optimizado (Ahorro de Datos + Redes Lentas):**
  - [ ] **Detección automática de red lenta:**
    - [ ] Medir velocidad de conexión
    - [ ] Activar modo optimizado automáticamente en 3G/2G
    - [ ] Indicador de red lenta
  - [ ] **Características del modo optimizado:**
    - [ ] **Imágenes:**
      - [ ] Compresión automática (80% menos tamaño)
      - [ ] Cargar solo vista previa (baja resolución)
      - [ ] Opción de cargar original bajo demanda
    - [ ] **Videos:**
      - [ ] No reproducir automáticamente
      - [ ] Compresión extrema
      - [ ] Solo audio inicialmente
    - [ ] **Mensajes:**
      - [ ] Texto plano sin efectos (opcional)
      - [ ] Desactivar animaciones pesadas
      - [ ] Reducir frecuencia de sync
    - [ ] **Animaciones:**
      - [ ] Reducir a 50% o desactivar
      - [ ] Partículas mínimas
      - [ ] Sin efectos de partículas en red 2G
  - [ ] **Ahorro de datos configurado:**
    - [ ] Límite de datos por día/semana/mes
    - [ ] Alerta al alcanzar límite
    - [ ] Pausar descarga de multimedia
    - [ ] Solo WiFi para multimedia
  - [ ] **Indicadores visuales:**
    - [ ] 📶 Icono de red lenta
    - [ ] 💾 Icono de ahorro de datos activo
    - [ ] 📊 Consumo de datos del día/mes

**Entregables Semana 13:**
- ✅ **Notificaciones funcionando (FCM o local)**
- ✅ **Modo optimizado para redes lentas**
- ✅ **Ahorro de datos configurable**
- ✅ **Compresión automática de multimedia**
- ✅ **Indicadores de estado de red**

### Semana 14: **Configuración de Cuenta Única**

- [ ] ~~Añadir múltiples cuentas~~ **NO NECESARIO (solo una cuenta para ti)**
- [ ] ~~Cambiar entre cuentas~~ **NO NECESARIO**

- [ ] **Configuración de cuenta única:**
  - [ ] Ver mi clave pública (npub)
  - [ ] Copiar clave pública
  - [ ] Exportar clave privada (con advertencia)
  - [ ] Cambiar avatar
  - [ ] Cambiar nombre/apodo
  - [ ] Ver estadísticas de uso
  - [ ] Cerrar sesión (con backup automático)

**Entregables Semana 14:**
- ✅ **Configuración de cuenta simple**
- ✅ **Backup automático de claves**
- ✅ **Estadísticas de uso (mensajes enviados, días juntos)**

**Entregables Fase 3:**
- ✅ **6 temas completos (claro, noche clara, noche oscura, opaco, cerdita, koalita, mix)**
- ✅ **Animaciones de fondo con mascotas**
- ✅ Grupos encriptados
- ✅ Notificaciones push
- ✅ Múltiples cuentas

---

## 🎯 Fase 4: Features Avanzadas (Semanas 15-18)

### Semana 15-16: **Llamadas de Voz y Video** 📞

> **Nota:** Las llamadas en Nostr son complejas porque el protocolo no tiene soporte nativo. Usaremos WebRTC con señalización a través de eventos Nostr encriptados.

- [ ] **Investigación y setup:**
  - [ ] Integrar biblioteca WebRTC (ej: `webrtc-kmp` o nativa)
  - [ ] Definir eventos de señalización (Kind personalizado)
  - [ ] Encriptar señalización con NIP-17

- [ ] **Llamadas de voz:**
  - [ ] Iniciar llamada desde chat
  - [ ] Sonido de llamada entrante
  - [ ] Pantalla de llamada (mute, speaker, end)
  - [ ] Manejo de red (WiFi/4G switching)
  - [ ] Reconexión automática si se pierde

- [ ] **Llamadas de video:**
  - [ ] Cámara frontal/trasera
  - [ ] Switch de cámara durante llamada
  - [ ] Mute de audio/video
  - [ ] Picture-in-picture
  - [ ] Calidad adaptable según ancho de banda

- [ ] **Estados de llamada:**
  - [ ] Llamando...
  - [ ] Timbrando...
  - [ ] En llamada
  - [ ] Finalizada
  - [ ] Perdida/rechazada

- [ ] **Historial de llamadas:**
  - [ ] Lista de llamadas realizadas/recibidas/perdidas
  - [ ] Duración de llamada
  - [ ] Volver a llamar

### Semana 17: **Optimización y Pulido**

- [ ] **Performance:**
  - [ ] Profiling de memoria
  - [ ] Optimizar batería
  - [ ] Reducir tamaño APK
  - [ ] Mejorar tiempos de carga
  - [ ] Lazy loading de imágenes/mensajes

- [ ] **Accesibilidad:**
  - [ ] TalkBack support
  - [ ] Texto escalable
  - [ ] Contraste adecuado
  - [ ] Labels en iconos

- [ ] **Internacionalización:**
  - [ ] Español (default)
  - [ ] Inglés
  - [ ] Estructura para más idiomas

### Semana 18: **Stickers Personalizados + Características Extra + Botón Mágico**

- [ ] **🎨 Creador de Stickers Personalizados:**
  - [ ] **Pantalla del editor de stickers:**
    - [ ] Seleccionar imagen de galería o tomar foto
    - [ ] Recortar automáticamente (detectar bordes)
    - [ ] Añadir bordes blancos automáticos
    - [ ] Añadir efectos: brillos, sombras, destellos
  - [ ] **Stickers pre-diseñados de la app:**
    - [ ] Pack Cerdita: cerditos en varias poses (feliz, triste, enamorado, durmiendo)
    - [ ] Pack Koalita: koalitas colgando, comiendo, durmiendo, saludando
    - [ ] Pack Flores: sakura, margaritas, girasoles, rosas, tulipanes
    - [ ] Pack Nubes: nubecitas con caritas, arcoíris, estrellitas
    - [ ] Pack Corazones: corazones de colores, corazones con alas, corazones brillantes
  - [ ] **Stickers animados:**
    - [ ] Cerditos saltando
    - [ ] Koalitas balanceándose
    - [ ] Flores girando/brillando
    - [ ] Nubecitas flotando
    - [ ] Corazones latiendo

- [ ] **Elementos decorativos para stickers:**
  - [ ] Bordes floreados (rosas, sakura, margaritas)
  - [ ] Bordes con corazones
  - [ ] Bordes con estrellitas
  - [ ] Bordes con burbujas
  - [ ] Marcos de colores
  - [ ] Nubecitas decorativas

- [ ] **Enviar stickers:**
  - [ ] Selector de stickers en chat (por packs)
  - [ ] Stickers recientes
  - [ ] Stickers favoritos
  - [ ] Buscar stickers por nombre/emoji

- [ ] **💕 Botón Mágico de Abrazo:**
  - [ ] **Ubicación del botón:**
    - [ ] En pantalla de chat, cerca del input de texto
    - [ ] Icono: 🐷🤗🐨 (cerdita y koalita abrazándose)
  - [ ] **Al presionar el botón:**
    - [ ] Animación especial a pantalla completa
    - [ ] 🐷 Cerdita y 🐨 Koalita aparecen en el centro
    - [ ] Se abrazan tiernamente
    - [ ] Corazones flotando alrededor
    - [ ] Destellitos dorados
    - [ ] Mensaje opcional: "Te envío un abrazo 🐷🤗🐨"
  - [ ] **Tipos de abrazos:**
    - [ ] Abrazo normal (default)
    - [ ] Abrazo con corazones (romántico)
    - [ ] Abrazo con estrellitas (amistad)
    - [ ] Abrazo grupal (múltiples cerditas y koalitas)
  - [ ] **Configuración:**
    - [ ] Activar/desactivar botón
    - [ ] Elegir tipo de abrazo predeterminado
    - [ ] Sonido opcional de abrazo

- [ ] **Estados tipo WhatsApp:**
  - [ ] Publicar estado (texto/imagen/sticker)
  - [ ] Ver estados de contactos
  - [ ] Desaparece a las 24h
  - [ ] Reaccionar a estados

- [ ] **Mensajes temporales:**
  - [ ] Configurar timer de autodestrucción
  - [ ] 5s, 1min, 1h, 1d
  - [ ] Notificación de mensaje eliminado

**Entregables Fase 4:**
- ✅ **Llamadas de voz**
- ✅ **Llamadas de video**
- ✅ **Stickers personalizados y packs oficiales**
- ✅ **💕 Botón Mágico de Abrazo (🐷🤗🐨)**
- ✅ App optimizada y accesible
- ✅ Características extra (estados, mensajes temporales)

---

## 🎯 Fase 5: Launch (Semanas 19-20)

### Semana 19: Beta Testing

- [ ] Cerrar beta (50-100 usuarios)
- [ ] Recoger feedback
- [ ] Analytics de crashes (Firebase Crashlytics)
- [ ] Métricas de uso
- [ ] Iterar rápido en bugs

### Semana 20: Preparación y Publicación

- [ ] **Preparación:**
  - [ ] Página web landing
  - [ ] Assets de marketing (screenshots, promo)
  - [ ] Documentación usuario
  - [ ] Política de privacidad
  - [ ] Términos de servicio

- [ ] **Publicación:**
  - [ ] Google Play Store (si cumple políticas)
  - [ ] F-Droid (recomendado para apps FOSS)
  - [ ] GitHub Releases (APK directo)
  - [ ] Anuncio en comunidades Nostr
  - [ ] Marketing orgánico (Twitter, Nostr)

**Entregables Fase 5:**
- ✅ App estable y testeada
- ✅ Publicada en stores
- ✅ Documentación completa
- ✅ Comunidad inicial

---

## 📊 Métricas de Éxito

| Métrica | Objetivo MVP | Objetivo Launch |
|---------|--------------|-----------------|
| Tamaño APK | < 30 MB | < 25 MB |
| Tiempo inicio | < 3s | < 2s |
| Entrega mensaje | < 5s | < 2s |
| Crash-free sessions | > 95% | > 99% |
| Battery drain | < 5%/hora | < 3%/hora |

---

## 🔐 Consideraciones de Seguridad

### Private Keys
- **NUNCA** enviar a servidores
- Almacenar en Android Keystore
- Opción de passphrase adicional
- Backup en papel (seed phrase)

### Encriptación
- Usar NIP-44 (no NIP-04 para nuevos mensajes)
- Rotación de claves periódica
- Perfect Forward Secrecy

### Red
- Tor opcional para máxima privacidad
- Validar certificados SSL de relays
- No confiar en un solo relay

---

## 🧪 Testing Strategy

| Tipo | Herramienta | Cobertura |
|------|-------------|-----------|
| Unit Tests | JUnit, MockK | Domain layer 80% |
| UI Tests | Compose Testing | Screens críticas |
| Integration | Espresso | Flujos principales |
| E2E | Manual + Script | Mensajería completa |

---

## 📚 Recursos de Aprendizaje

### Para el equipo
1. [Nostr Protocol Guide](https://github.com/nostr-protocol/nostr)
2. [NIPs Oficiales](https://nips.nostr.com/)
3. [Rhodium SDK Docs](https://github.com/KotlinGeekDev/Rhodium)
4. [Android Developers](https://developer.android.com/)
5. [Jetpack Compose](https://developer.android.com/jetpack/compose)

### Apps de referencia
1. **Amethyst** (Android) - https://github.com/vitorpamplona/amethyst
2. **Damus** (iOS) - https://github.com/damus-io/damus
3. **Snort** (Web) - https://github.com/v0l/snort
4. **More-speech** (Desktop) - https://github.com/unclebob/more-speech

---

## 🚦 Próximos Pasos Inmediatos

1. **Hoy**: Crear proyecto Android vacío
2. **Mañana**: Configurar dependencias y estructura
3. **Día 3**: Implementar generación de claves
4. **Día 4**: Conectar a primer relay
5. **Día 5**: Primer mensaje público enviado

---

**¡Vamos a construir el futuro del chat descentralizado! 🚀**
