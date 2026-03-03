package com.cerdita.app.domain.model

/**
 * 🐷 Par de claves Nostr
 */
data class NostrKeyPair(
    val publicKeyHex: String,      // Clave pública en hexadecimal
    val privateKeyHex: String,     // Clave privada en hexadecimal
    val publicKeyNpub: String? = null,  // Clave pública en formato npub (bech32)
    val privateKeyNsec: String? = null, // Clave privada en formato nsec (bech32)
    val createdAt: Long = System.currentTimeMillis()
) {
    /**
     * Obtiene una versión corta de la clave pública para mostrar
     */
    fun getShortNpub(): String {
        return publicKeyNpub?.take(12) ?: publicKeyHex.take(16) + "..."
    }
}
