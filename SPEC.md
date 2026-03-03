# Especificación Técnica - NostrChat

## 1. Arquitectura

### 1.1 Overview

```
┌─────────────────────────────────────────────────────────────┐
│                     Presentation Layer                       │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  Jetpack Compose UI (Screens, Components, Navigation)   │ │
│  └─────────────────────────────────────────────────────────┘ │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  ViewModels (State, Events, Business Logic)             │ │
│  └─────────────────────────────────────────────────────────┘ │
├─────────────────────────────────────────────────────────────┤
│                      Domain Layer                            │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  Use Cases (Single responsibility operations)           │ │
│  └─────────────────────────────────────────────────────────┘ │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  Repository Interfaces (Contracts)                      │ │
│  └─────────────────────────────────────────────────────────┘ │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  Models (Pure Kotlin data classes)                      │ │
│  └─────────────────────────────────────────────────────────┘ │
├─────────────────────────────────────────────────────────────┤
│                       Data Layer                             │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  Repository Implementations                             │ │
│  └─────────────────────────────────────────────────────────┘ │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  Local Data Sources (Room, DataStore)                   │ │
│  └─────────────────────────────────────────────────────────┘ │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │  Remote Data Sources (Nostr SDK, Relays)                │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### 1.2 Patrones de Diseño

| Patrón | Uso |
|--------|-----|
| **MVVM** | Separación UI/Lógica |
| **Repository** | Abstracción de datos |
| **Use Case** | Casos de uso específicos |
| **Singleton** | Servicios únicos (NostrService) |
| **Observer** | Flow/LiveData para estados |
| **Dependency Injection** | Hilt para DI |
| **MVI** | State + Event en ViewModels |

---

## 2. Modelo de Datos

### 2.1 Entidades Principales

```kotlin
// Domain Models

data class UserProfile(
    val publicKey: String,           // npub format
    val displayName: String?,
    val name: String?,               // NIP-05
    val picture: String?,            // URL
    val banner: String?,             // URL
    val about: String?,
    val website: String?,
    val lud16: String?,              // Lightning address
    val nip05Verified: Boolean = false,
    val createdAt: Long,
    val updatedAt: Long
)

data class Contact(
    val publicKey: String,
    val profile: UserProfile?,
    val isFollowed: Boolean,
    val isBlocked: Boolean,
    val addedAt: Long,
    val metadata: Map<String, String>
)

data class Chat(
    val id: String,                  // Unique chat ID
    val type: ChatType,              // DIRECT, GROUP
    val participants: List<String>,  // Public keys
    val lastMessage: Message?,
    val lastMessageTime: Long,
    val unreadCount: Int,
    val isMuted: Boolean,
    val isPinned: Boolean
)

enum class ChatType {
    DIRECT,
    GROUP
}

data class Message(
    val id: String,                  // Event ID (hex)
    val eventId: String,             // Nostr event ID
    val chatId: String,
    val senderPublicKey: String,
    val content: String,
    val timestamp: Long,
    val kind: MessageKind,
    val isEncrypted: Boolean,
    val isRead: Boolean,
    val reactions: List<Reaction>,
    val replyToMessageId: String?,
    val attachments: List<Attachment>,
    val deliveryStatus: DeliveryStatus
)

enum class MessageKind {
    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    FILE,
    REACTION,
    SYSTEM
}

enum class DeliveryStatus {
    PENDING,
    SENT_TO_RELAY,
    RECEIVED_BY_RECIPIENT,
    READ_BY_RECIPIENT,
    FAILED
}

data class Reaction(
    val emoji: String,
    val publicKey: String,
    val timestamp: Long
)

data class Attachment(
    val id: String,
    val type: AttachmentType,
    val url: String,
    val localPath: String?,
    val size: Long,
    val mimeType: String
)

enum class AttachmentType {
    IMAGE,
    AUDIO,
    VIDEO,
    FILE
}

data class NostrAccount(
    val publicKey: String,         // npub
    val privateKeyEncrypted: String, // Encrypted in Keystore
    val relayList: List<String>,
    val contactList: List<String>,
    val createdAt: Long,
    val lastActiveAt: Long
)
```

### 2.2 Room Entities

```kotlin
// Data Layer - Room Entities

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey val publicKey: String,
    val privateKeyEncrypted: ByteArray,
    val relayListJson: String,
    val isCurrent: Boolean,
    val createdAt: Long,
    val lastActiveAt: Long
)

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey val publicKey: String,
    val displayName: String?,
    val name: String?,
    val picture: String?,
    val banner: String?,
    val about: String?,
    val website: String?,
    val lud16: String?,
    val nip05Verified: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val publicKey: String,
    val isFollowed: Boolean,
    val isBlocked: Boolean,
    val metadataJson: String,
    val addedAt: Long
)

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey val id: String,
    val type: String,
    val participantIdsJson: String,
    val lastMessageId: String?,
    val lastMessageTime: Long,
    val unreadCount: Int,
    val isMuted: Boolean,
    val isPinned: Boolean
)

@Entity(
    tableName = "messages",
    indices = [
        Index(value = ["chatId"]),
        Index(value = ["senderPublicKey"]),
        Index(value = ["timestamp"])
    ]
)
data class MessageEntity(
    @PrimaryKey val id: String,
    val eventId: String,
    val chatId: String,
    val senderPublicKey: String,
    val content: String,
    val timestamp: Long,
    val kind: String,
    val isEncrypted: Boolean,
    val isRead: Boolean,
    val reactionsJson: String,
    val replyToMessageId: String?,
    val attachmentsJson: String,
    val deliveryStatus: String
)

@Entity(
    tableName = "relay_info",
    indices = [Index(value = ["url"], unique = true)]
)
data class RelayEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val url: String,
    val name: String?,
    val description: String?,
    val isRead: Boolean,
    val isWrite: Boolean,
    val lastConnected: Long?,
    val connectionState: String,
    val supportedNipsJson: String
)
```

---

## 3. Nostr Implementation

### 3.1 Event Kinds Usados

| Kind | Tipo | Descripción | NIP |
|------|------|-------------|-----|
| 0 | Metadata | Perfil de usuario | NIP-01 |
| 1 | Short Text Note | Mensaje público | NIP-01 |
| 3 | Follow List | Lista de follows | NIP-01 |
| 4 | Encrypted DM | DM encriptado (legacy) | NIP-04 |
| 10002 | Relay List | Lista de relays | NIP-65 |
| 10063 | Relay Metadata | Info del relay | NIP-65 |
| 4444 | Gift Wrap | DM privado | NIP-17/59 |
| 9735 | Zap Request | Propina Lightning | NIP-57 |
| 9734 | Zap Receipt | Confirmación zap | NIP-57 |

### 3.2 Estructura de Eventos

```kotlin
// Nostr Event Structure
data class NostrEvent(
    val id: String,              // SHA256 hash of serialized event
    val pubkey: String,          // Public key of creator (hex)
    val created_at: Long,        // Unix timestamp
    val kind: Int,               // Event kind
    val tags: List<List<String>>, // Tags (e.g., ["p", "pubkey"])
    val content: String,         // Event content (may be encrypted)
    val sig: String              // Signature (hex)
)

// Ejemplo: Evento Kind 4 (DM NIP-04)
val dmEvent = NostrEvent(
    id = "calculated_sha256_hash",
    pubkey = "sender_public_key_hex",
    created_at = System.currentTimeMillis() / 1000,
    kind = 4,
    tags = listOf(
        listOf("p", "recipient_public_key_hex") // Tag p = destinatario
    ),
    content = "aes_encrypted_base64_content",
    sig = "signature_hex"
)

// Ejemplo: Evento Kind 1 (Nota pública)
val noteEvent = NostrEvent(
    id = "calculated_sha256_hash",
    pubkey = "author_public_key_hex",
    created_at = System.currentTimeMillis() / 1000,
    kind = 1,
    tags = emptyList(),
    content = "Hello, Nostr!",
    sig = "signature_hex"
)

// Ejemplo: Evento Kind 3 (Follow List)
val followListEvent = NostrEvent(
    id = "calculated_sha256_hash",
    pubkey = "user_public_key_hex",
    created_at = System.currentTimeMillis() / 1000,
    kind = 3,
    tags = listOf(
        listOf("p", "followed_pubkey_1", "relay_url_1"),
        listOf("p", "followed_pubkey_2", "relay_url_2")
    ),
    content = "", // Empty for kind 3
    sig = "signature_hex"
)
```

### 3.3 Filtros de Suscripción

```kotlin
// Suscribirse a DMs propios
val dmFilter = NostrFilter(
    kinds = listOf(4),
    pTags = listOf(currentUserId), // Mensajes donde soy destinatario
    limit = 100
)

// Suscribirse a notas de follows
val notesFilter = NostrFilter(
    kinds = listOf(1),
    authors = followedUserIds,
    limit = 50
)

// Suscribirse a metadata de contactos
val profileFilter = NostrFilter(
    kinds = listOf(0),
    authors = contactIds,
    limit = 100
)
```

---

## 4. Criptografía

### 4.1 Generación de Claves

```kotlin
// Usando secp256k1 curve (Bitcoin's curve)
class KeyGenerator {
    fun generateKeyPair(): KeyPair {
        val privateKey = SecureRandomBytes(32)
        val publicKey = Secp256k1.publicKey(privateKey)
        return KeyPair(privateKey, publicKey)
    }
    
    // Convertir a formato bech32 (npub/nsec)
    fun toNpub(publicKey: ByteArray): String {
        return Bech32.encode("npub", publicKey)
    }
    
    fun toNsec(privateKey: ByteArray): String {
        return Bech32.encode("nsec", privateKey)
    }
    
    fun fromNpub(npub: String): ByteArray {
        val (hrp, data) = Bech32.decode(npub)
        require(hrp == "npub") { "Invalid npub format" }
        return data
    }
}
```

### 4.2 Encriptación NIP-04

```kotlin
object Nip04Encryption {
    fun encrypt(plaintext: String, recipientPubKey: String, senderPrivKey: String): String {
        // 1. Calcular shared secret usando ECDH
        val sharedSecret = Secp256k1.ecdh(recipientPubKey, senderPrivKey)
        
        // 2. Generar IV aleatorio
        val iv = SecureRandomBytes(16)
        
        // 3. Encriptar con AES-256-CBC
        val ciphertext = AES.encryptCBC(plaintext, sharedSecret, iv)
        
        // 4. Retornar base64(ciphertext) + base64(iv) separados por ?
        return "${Base64.encode(ciphertext)}?${Base64.encode(iv)}"
    }
    
    fun decrypt(encryptedMessage: String, senderPubKey: String, recipientPrivKey: String): String {
        val parts = encryptedMessage.split("?")
        val ciphertext = Base64.decode(parts[0])
        val iv = Base64.decode(parts[1])
        
        // 1. Calcular shared secret
        val sharedSecret = Secp256k1.ecdh(senderPubKey, recipientPrivKey)
        
        // 2. Desencriptar
        return AES.decryptCBC(ciphertext, sharedSecret, iv)
    }
}
```

### 4.3 Encriptación NIP-44 (Recomendado)

```kotlin
object Nip44Encryption {
    fun encrypt(plaintext: String, recipientPubKey: String, senderPrivKey: String): String {
        // 1. Calcular shared secret
        val sharedSecret = Secp256k1.ecdh(recipientPubKey, senderPrivKey)
        
        // 2. Derivar keys usando HKDF
        val (encryptionKey, authKey) = HKDF.deriveKeys(sharedSecret)
        
        // 3. Generar salt y nonce
        val salt = SecureRandomBytes(32)
        val nonce = SecureRandomBytes(32)
        
        // 4. Encriptar con ChaCha20-Poly1305
        val ciphertext = ChaCha20Poly1305.encrypt(
            plaintext, encryptionKey, nonce, authKey
        )
        
        // 5. Retornar v2 + salt + nonce + ciphertext (todo en base64)
        return "02" + Base64.encode(salt + nonce + ciphertext)
    }
}
```

### 4.4 Gift Wrap (NIP-59)

```kotlin
object GiftWrap {
    fun wrap(
        innerEvent: NostrEvent,
        recipientPubKey: String,
        senderPrivKey: String
    ): NostrEvent {
        // 1. Encriptar el evento interno con NIP-44
        val serializedEvent = Json.encodeToString(innerEvent)
        val encryptedContent = Nip44Encryption.encrypt(
            serializedEvent, recipientPubKey, senderPrivKey
        )
        
        // 2. Crear evento "seal" (Kind 13)
        val sealEvent = NostrEvent(
            kind = 13,
            tags = listOf(listOf("p", recipientPubKey)),
            content = encryptedContent,
            // ... resto de campos
        )
        
        // 3. Encriptar el seal para el destinatario final
        val serializedSeal = Json.encodeToString(sealEvent)
        val wrappedContent = Nip44Encryption.encrypt(
            serializedSeal, recipientPubKey, senderPrivKey
        )
        
        // 4. Crear evento "gift wrap" (Kind 1059)
        return NostrEvent(
            kind = 1059,
            tags = emptyList(),
            content = wrappedContent
        )
    }
}
```

---

## 5. Componentes de UI

### 5.1 Pantallas Principales

```
Navigation Graph:
├── SplashScreen
├── OnboardingScreen (primer launch)
│   ├── CreateAccountScreen
│   └── ImportAccountScreen
├── MainScreen (Bottom Navigation)
│   ├── ChatsListScreen
│   │   └── ChatDetailScreen
│   ├── ContactsScreen
│   │   └── AddContactScreen
│   │   └── ProfileScreen
│   ├── SettingsScreen
│   │   ├── AccountSettingsScreen
│   │   ├── PrivacySettingsScreen
│   │   ├── NotificationSettingsScreen
│   │   └── AppearanceSettingsScreen
│   └── ProfileScreen (own profile)
└── QRCodeScannerScreen
```

### 5.2 Componentes Reutilizables

```kotlin
// UI Components
@Composable
fun MessageBubble(
    message: Message,
    isFromMe: Boolean,
    onReply: (String) -> Unit,
    onReact: (String) -> Unit,
    onCopy: () -> Unit
)

@Composable
fun ChatListItem(
    chat: Chat,
    onClick: () -> Unit,
    onLongPress: () -> Unit
)

@Composable
fun ContactItem(
    contact: Contact,
    onClick: () -> Unit,
    showCheckbox: Boolean = false,
    isSelected: Boolean = false,
    onToggle: (Boolean) -> Unit
)

@Composable
fun MessageInput(
    onSendMessage: (String) -> Unit,
    onAttachFile: () -> Unit,
    onRecordAudio: () -> Unit,
    isRecording: Boolean
)

@Composable
fun ProfileCard(
    profile: UserProfile,
    isContact: Boolean,
    onAddContact: () -> Unit,
    onRemoveContact: () -> Unit,
    onBlock: () -> Unit
)

@Composable
fun RelayStatusIndicator(
    relayUrl: String,
    connectionState: ConnectionState,
    latency: Long?
)
```

---

## 6. ViewModels

### 6.1 ChatViewModel

```kotlin
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val subscribeToMessagesUseCase: SubscribeToMessagesUseCase,
    private val markAsReadUseCase: MarkAsReadUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(ChatState())
    val state: StateFlow<ChatState> = _state.asStateFlow()
    
    private val _events = Channel<ChatEvent>(Channel.BUFFERED)
    val events: Flow<ChatEvent> = _events.receiveAsFlow()
    
    fun loadChat(chatId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            getMessagesUseCase(chatId).collect { messages ->
                _state.update { 
                    it.copy(
                        messages = messages,
                        isLoading = false
                    ) 
                }
            }
        }
    }
    
    fun sendMessage(content: String, recipientPubKey: String) {
        viewModelScope.launch {
            _state.update { it.copy(isSending = true) }
            
            sendMessageUseCase(content, recipientPubKey)
                .onSuccess { 
                    _events.send(ChatEvent.MessageSent)
                }
                .onFailure { 
                    _events.send(ChatEvent.SendMessageError(it))
                }
            
            _state.update { it.copy(isSending = false) }
        }
    }
    
    fun markAsRead(messageIds: List<String>) {
        viewModelScope.launch {
            markAsReadUseCase(messageIds)
        }
    }
}

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val isSending: Boolean = false,
    val error: String? = null
)

sealed class ChatEvent {
    object MessageSent : ChatEvent()
    data class SendMessageError(val error: String) : ChatEvent()
    data class NewMessageReceived(val message: Message) : ChatEvent()
}
```

---

## 7. Configuración de Relays

### 7.1 Estrategia de Conexión

```kotlin
class RelayManager @Inject constructor(
    private val nostrService: NostrService
) {
    // Relays por defecto para nuevos usuarios
    private val defaultRelays = listOf(
        RelayConfig("wss://relay.damus.io", read = true, write = true),
        RelayConfig("wss://relay.nostr.band", read = true, write = true),
        RelayConfig("wss://nos.lol", read = true, write = true),
        RelayConfig("wss://relay.snort.social", read = true, write = false),
        RelayConfig("wss://purplepag.es", read = true, write = false),
        RelayConfig("wss://relay.primal.net", read = true, write = false)
    )
    
    // Conectar a relays
    suspend fun connect(relays: List<RelayConfig>) {
        relays.forEach { relay ->
            nostrService.connect(relay.url)
        }
    }
    
    // Suscribirse a eventos
    suspend fun subscribe(filters: List<NostrFilter>) {
        nostrService.subscribe(filters)
    }
    
    // Publicar evento
    suspend fun publish(event: NostrEvent) {
        // Publicar en todos los relays de escritura
        defaultRelays
            .filter { it.write }
            .forEach { relay ->
                nostrService.publish(event, relay.url)
            }
    }
}
```

---

## 8. Testing

### 8.1 Unit Tests

```kotlin
class MessageEncryptionTest {
    @Test
    fun `NIP-04 encryption and decryption should work`() {
        // Given
        val senderKeys = KeyGenerator.generateKeyPair()
        val recipientKeys = KeyGenerator.generateKeyPair()
        val plaintext = "Hello, Nostr!"
        
        // When
        val encrypted = Nip04Encryption.encrypt(
            plaintext, 
            recipientKeys.publicKey, 
            senderKeys.privateKey
        )
        val decrypted = Nip04Encryption.decrypt(
            encrypted,
            senderKeys.publicKey,
            recipientKeys.privateKey
        )
        
        // Then
        assertEquals(plaintext, decrypted)
    }
    
    @Test
    fun `NIP-44 should be more secure than NIP-04`() {
        // Test que NIP-44 produce ciphertext diferente cada vez
        // (debido al salt aleatorio)
    }
}

class ChatViewModelTest {
    @Test
    fun `sending message should update state correctly`() = runTest {
        // Given
        val mockUseCase = mockk<SendMessageUseCase>()
        coEvery { mockUseCase(any(), any()) } returns Result.success(Unit)
        
        val viewModel = ChatViewModel(mockUseCase, ...)
        
        // When
        viewModel.sendMessage("Test message", "recipient_pubkey")
        
        // Then
        val state = viewModel.state.value
        assertTrue(state.messages.isNotEmpty())
        assertEquals("Test message", state.messages.last().content)
    }
}
```

---

## 9. Seguridad

### 9.1 Almacenamiento Seguro

```kotlin
class SecureKeyStorage @Inject constructor(
    private val keystore: AndroidKeystore
) {
    fun storePrivateKey(alias: String, privateKey: ByteArray) {
        // Usar Android Keystore con StrongBox si está disponible
        val keySpec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .setUserAuthenticationRequired(false) // No requerir biometría
            .build()
        
        keystore.generateKey(keySpec)
        keystore.encryptAndStore(privateKey, alias)
    }
    
    fun getPrivateKey(alias: String): ByteArray {
        return keystore.decryptAndGet(alias)
    }
    
    fun deletePrivateKey(alias: String) {
        keystore.deleteEntry(alias)
    }
}
```

### 9.2 Mejores Prácticas

1. **Private Keys**: Nunca salen del dispositivo
2. **Keystore**: Usar Android Keystore (hardware-backed si disponible)
3. **No logs**: No loggear información sensible
4. **Secure Random**: Usar SecureRandom para todo lo criptográfico
5. **Certificate Pinning**: Para conexiones a relays críticos
6. **Obfuscation**: R8/ProGuard en release builds

---

## 10. Build Configuration

### 10.1 build.gradle.kts (app)

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.nostrchat.app"
    compileSdk = 35
    
    defaultConfig {
        applicationId = "com.nostrchat.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
    
    buildFeatures {
        compose = true
        buildConfig = true
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Android
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material.icons.extended)
    
    // Navigation
    implementation(libs.navigation.compose)
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    
    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    
    // DataStore
    implementation(libs.datastore.preferences)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    
    // Serialization
    implementation(libs.kotlinx.serialization.json)
    
    // Nostr SDK - Rhodium
    implementation(libs.rhodium)
    
    // Cryptography
    implementation(libs.tink.android)
    implementation(libs.libsodium)
    
    // Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    
    // Networking
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    
    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
}
```

---

**Documento vivo - Se actualizará durante el desarrollo**
