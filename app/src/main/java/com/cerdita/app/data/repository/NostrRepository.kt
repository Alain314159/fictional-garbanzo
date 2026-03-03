package com.cerdita.app.data.repository

import com.cerdita.app.domain.model.NostrKeyPair
import com.cerdita.app.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

/**
 * 🐷 Repositorio de Nostr - Cerdita
 * Maneja la generación y gestión de claves Nostr
 */
interface NostrRepository {
    
    /**
     * Genera un nuevo par de claves Nostr
     * @return NostrKeyPair con las claves pública y privada
     */
    suspend fun generateKeyPair(): Result<NostrKeyPair>
    
    /**
     * Convierte una clave pública a formato npub (bech32)
     */
    fun publicKeyToNpub(publicKeyHex: String): Result<String>
    
    /**
     * Convierte una clave privada a formato nsec (bech32)
     */
    fun privateKeyToNsec(privateKeyHex: String): Result<String>
    
    /**
     * Convierte npub a clave pública en hexadecimal
     */
    fun npubToPublicKey(npub: String): Result<String>
    
    /**
     * Convierte nsec a clave privada en hexadecimal
     */
    fun nsecToPrivateKey(nsec: String): Result<String>
    
    /**
     * Guarda las claves de forma segura
     */
    suspend fun saveKeysSecurely(keyPair: NostrKeyPair): Result<Unit>
    
    /**
     * Obtiene las claves guardadas
     */
    suspend fun getSavedKeys(): Result<NostrKeyPair>?
    
    /**
     * Verifica si hay claves guardadas
     */
    suspend fun hasSavedKeys(): Boolean
    
    /**
     * Elimina las claves guardadas
     */
    suspend fun deleteSavedKeys(): Result<Unit>
    
    /**
     * Publica el perfil en los relays (Kind 0)
     */
    suspend fun publishProfile(
        displayName: String?,
        about: String?,
        picture: String?
    ): Result<Unit>
    
    /**
     * Obtiene el perfil de un usuario desde los relays
     */
    fun getProfile(publicKey: String): Flow<UserProfile?>
}
