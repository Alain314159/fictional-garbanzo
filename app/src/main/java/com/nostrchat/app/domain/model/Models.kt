package com.nostrchat.app.domain.model

/**
 * User profile data from Nostr metadata event (Kind 0)
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
 * Contact in user's follow list
 */
data class Contact(
    val publicKey: String,
    val profile: UserProfile? = null,
    val isFollowed: Boolean = false,
    val isBlocked: Boolean = false,
    val addedAt: Long = System.currentTimeMillis(),
    val metadata: Map<String, String> = emptyMap()
)

/**
 * Chat conversation (direct or group)
 */
data class Chat(
    val id: String,                  // Unique chat ID
    val type: ChatType = ChatType.DIRECT,
    val participants: List<String> = emptyList(),  // Public keys
    val lastMessage: Message? = null,
    val lastMessageTime: Long = 0L,
    val unreadCount: Int = 0,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false
)

enum class ChatType {
    DIRECT,
    GROUP
}

/**
 * Message in a chat
 */
data class Message(
    val id: String,                  // Event ID (hex)
    val eventId: String,             // Nostr event ID
    val chatId: String,
    val senderPublicKey: String,
    val content: String,
    val timestamp: Long,
    val kind: MessageKind = MessageKind.TEXT,
    val isEncrypted: Boolean = true,
    val isRead: Boolean = false,
    val reactions: List<Reaction> = emptyList(),
    val replyToMessageId: String? = null,
    val attachments: List<Attachment> = emptyList(),
    val deliveryStatus: DeliveryStatus = DeliveryStatus.PENDING
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

/**
 * Reaction to a message (emoji)
 */
data class Reaction(
    val emoji: String,
    val publicKey: String,
    val timestamp: Long
)

/**
 * Attachment (image, audio, video, file)
 */
data class Attachment(
    val id: String,
    val type: AttachmentType,
    val url: String,
    val localPath: String? = null,
    val size: Long = 0L,
    val mimeType: String
)

enum class AttachmentType {
    IMAGE,
    AUDIO,
    VIDEO,
    FILE
}

/**
 * Nostr account (user's own account)
 */
data class NostrAccount(
    val publicKey: String,         // npub
    val privateKeyEncrypted: ByteArray,
    val relayList: List<String> = emptyList(),
    val contactList: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val lastActiveAt: Long = System.currentTimeMillis()
)

/**
 * Relay information
 */
data class Relay(
    val url: String,
    val name: String? = null,
    val description: String? = null,
    val isRead: Boolean = true,
    val isWrite: Boolean = true,
    val lastConnected: Long? = null,
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED,
    val supportedNips: List<Int> = emptyList()
)

enum class ConnectionState {
    CONNECTED,
    CONNECTING,
    DISCONNECTED,
    ERROR
}
