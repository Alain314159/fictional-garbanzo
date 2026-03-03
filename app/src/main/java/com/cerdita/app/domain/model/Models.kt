package com.cerdita.app.domain.model

/**
 * 🐷 Modelos de Dominio - Cerdita
 */

/**
 * Perfil de usuario en Nostr (Kind 0)
 */
data class UserProfile(
    val publicKey: String,           // npub format
    val displayName: String? = null,
    val name: String? = null,        // NIP-05 identifier
    val picture: String? = null,     // URL
    val banner: String? = null,      // URL
    val about: String? = null,
    val website: String? = null,
    val lud16: String? = null,       // Lightning address
    val nip05Verified: Boolean = false,
    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)

/**
 * Contacto (follow en Nostr)
 */
data class Contact(
    val publicKey: String,
    val profile: UserProfile? = null,
    val customName: String? = null,  // Nombre personalizado localmente
    val isFollowed: Boolean = false,
    val isBlocked: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)

/**
 * Chat (conversación)
 */
data class Chat(
    val id: String,
    val participantPublicKey: String,
    val lastMessage: Message? = null,
    val lastMessageTime: Long = 0L,
    val unreadCount: Int = 0,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false
)

/**
 * Mensaje
 */
data class Message(
    val id: String,                  // Event ID
    val eventId: String,
    val chatId: String,
    val senderPublicKey: String,
    val content: String,
    val timestamp: Long,
    val isEncrypted: Boolean = true,
    val isRead: Boolean = false,
    val deliveryStatus: DeliveryStatus = DeliveryStatus.PENDING,
    val replyToMessageId: String? = null,
    val hasAttachments: Boolean = false
)

enum class DeliveryStatus {
    PENDING,        // ⏳ Sin conexión
    SENT_TO_RELAY,  // 📤 Enviado
    RECEIVED,       // ✅ Recibido
    READ,           // 👁️ Leído
    FAILED          // ❌ Fallido
}

/**
 * Cuenta de usuario local
 */
data class UserAccount(
    val publicKey: String,         // npub
    val privateKeyEncrypted: ByteArray,
    val displayName: String? = null,
    val avatarType: AvatarType = AvatarType.PIGGY,
    val profilePictureUrl: String? = null,
    val relayList: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val lastActiveAt: Long = System.currentTimeMillis()
)

/**
 * Tipo de avatar
 */
enum class AvatarType {
    PIGGY,      // 🐷 Cerdita
    KOALA,      // 🐨 Koalita
    PHOTO       // 📷 Foto personalizada
}

/**
 * Relay de Nostr
 */
data class Relay(
    val url: String,
    val name: String? = null,
    val isRead: Boolean = true,
    val isWrite: Boolean = true,
    val lastConnected: Long? = null,
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED
)

enum class ConnectionState {
    CONNECTED,
    CONNECTING,
    DISCONNECTED,
    ERROR
}
