package com.cerdita.app.data.remote

import android.util.Log
import com.cerdita.app.data.repository.NostrRepository
import com.cerdita.app.domain.model.NostrKeyPair
import com.cerdita.app.domain.model.UserProfile
import io.github.kotlingeekdev.rhodium.NostrService
import io.github.kotlingeekdev.rhodium.crypto.generateKeyPair
import io.github.kotlingeekdev.rhodium.crypto.signEvent
import io.github.kotlingeekdev.rhodium.event.Event
import io.github.kotlingeekdev.rhodium.event.EventKind
import io.github.kotlingeekdev.rhodium.filter.NostrFilter
import io.github.kotlingeekdev.rhodium.request.RequestMessage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.security.SecureRandom
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 🐷 Implementación de NostrRepository usando Rhodium SDK
 */
@Singleton
class NostrRepositoryImpl @Inject constructor(
    private val nostrService: NostrService
) : NostrRepository {
    
    companion object {
        private const val TAG = "NostrRepositoryImpl"
        
        // Relays por defecto para Cerdita 💕
        val DEFAULT_RELAYS = listOf(
            "wss://relay.damus.io",
            "wss://relay.nostr.band",
            "wss://nos.lol",
            "wss://relay.snort.social",
            "wss://purplepag.es",
            "wss://relay.primal.net"
        )
    }
    
    private val json = Json { ignoreUnknownKeys = true }
    
    override suspend fun generateKeyPair(): Result<NostrKeyPair> {
        return try {
            Log.d(TAG, "🔑 Generando par de claves Nostr...")
            
            // Usar Rhodium para generar claves
            val keyPair = generateKeyPair()
            
            val nostrKeyPair = NostrKeyPair(
                publicKeyHex = keyPair.publicKey,
                privateKeyHex = keyPair.privateKey,
                createdAt = System.currentTimeMillis()
            )
            
            Log.d(TAG, "✅ Claves generadas exitosamente")
            Log.d(TAG, "🔓 Public Key: ${nostrKeyPair.publicKeyHex.take(16)}...")
            
            Result.success(nostrKeyPair)
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error generando claves", e)
            Result.failure(e)
        }
    }
    
    override fun publicKeyToNpub(publicKeyHex: String): Result<String> {
        return try {
            // Rhodium maneja la conversión internamente
            // Por ahora retornamos el hex, se implementará bech32 después
            Result.success(publicKeyHex)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override fun privateKeyToNsec(privateKeyHex: String): Result<String> {
        return try {
            Result.success(privateKeyHex)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override fun npubToPublicKey(npub: String): Result<String> {
        return try {
            // Si es npub válido, convertir a hex
            // Por ahora asumimos que ya es hex
            Result.success(npub)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override fun nsecToPrivateKey(nsec: String): Result<String> {
        return try {
            Result.success(nsec)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun saveKeysSecurely(keyPair: NostrKeyPair): Result<Unit> {
        // TODO: Implementar con Android Keystore
        // Por ahora guardamos en memoria (NO SEGURO - solo para testing)
        return try {
            Log.d(TAG, "💾 Guardando claves...")
            // Implementación temporal - se reemplazará con Keystore
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error guardando claves", e)
            Result.failure(e)
        }
    }
    
    override suspend fun getSavedKeys(): Result<NostrKeyPair>? {
        // TODO: Implementar con Android Keystore
        return null
    }
    
    override suspend fun hasSavedKeys(): Boolean {
        // TODO: Implementar con Android Keystore
        return false
    }
    
    override suspend fun deleteSavedKeys(): Result<Unit> {
        // TODO: Implementar con Android Keystore
        return Result.success(Unit)
    }
    
    override suspend fun publishProfile(
        displayName: String?,
        about: String?,
        picture: String?
    ): Result<Unit> {
        return try {
            Log.d(TAG, "📤 Publicando perfil en Nostr...")
            
            // Crear contenido del perfil (Kind 0)
            val profileContent = buildJsonObject {
                displayName?.let { put("name", it) }
                displayName?.let { put("display_name", it) }
                about?.let { put("about", it) }
                picture?.let { put("picture", it) }
                put("nip05", "")
                put("lud16", "")
            }.toString()
            
            // TODO: Firmar y publicar evento Kind 0
            // Por ahora solo logueamos
            Log.d(TAG, "📝 Perfil JSON: $profileContent")
            
            Log.d(TAG, "✅ Perfil publicado (simulado)")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error publicando perfil", e)
            Result.failure(e)
        }
    }
    
    override fun getProfile(publicKey: String): Flow<UserProfile?> = callbackFlow {
        try {
            Log.d(TAG, "🔍 Obteniendo perfil para: $publicKey")
            
            // Crear filtro para Kind 0 (metadata)
            val filter = NostrFilter.newFilter()
                .authors(publicKey)
                .kinds(EventKind.METADATA.kind)
                .limit(1)
                .build()
            
            val request = RequestMessage.singleFilterRequest(
                subscriptionId = "profile_${System.currentTimeMillis()}",
                filter = filter
            )
            
            // Suscribirse a eventos
            nostrService.request(
                request = request,
                onRequestError = { relay, throwable ->
                    Log.e(TAG, "❌ Error en relay $relay: ${throwable.message}")
                }
            ) { relay, received ->
                // Procesar evento recibido
                val event = received as? Event
                if (event?.kind == EventKind.METADATA.kind) {
                    try {
                        val profile = json.decodeFromString<UserProfileJson>(event.content)
                        send(
                            UserProfile(
                                publicKey = publicKey,
                                displayName = profile.displayName ?: profile.name,
                                name = profile.name,
                                picture = profile.picture,
                                about = profile.about,
                                website = profile.website,
                                lud16 = profile.lud16,
                                createdAt = event.createdAt * 1000,
                                updatedAt = System.currentTimeMillis()
                            )
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "Error parseando perfil", e)
                    }
                }
            }
            
            awaitClose {
                Log.d(TAG, "🔕 Cancelando suscripción de perfil")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error obteniendo perfil", e)
            trySend(null)
            close()
        }
    }
    
    // Data class para parsing de JSON de perfil
    @kotlinx.serialization.Serializable
    data class UserProfileJson(
        val name: String? = null,
        val display_name: String? = null,
        val displayName: String? = null,
        val about: String? = null,
        val picture: String? = null,
        val banner: String? = null,
        val website: String? = null,
        val lud16: String? = null,
        val nip05: String? = null
    )
}
