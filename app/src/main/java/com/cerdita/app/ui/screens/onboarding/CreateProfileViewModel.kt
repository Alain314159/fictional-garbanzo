package com.cerdita.app.ui.screens.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerdita.app.data.repository.NostrRepository
import com.cerdita.app.domain.model.AvatarType
import com.cerdita.app.domain.model.NostrKeyPair
import com.cerdita.app.domain.model.UserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🐷 ViewModel para Creación de Perfil - Cerdita
 */
@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val nostrRepository: NostrRepository
) : ViewModel() {
    
    companion object {
        private const val TAG = "CreateProfileVM"
    }
    
    private val _state = MutableStateFlow(CreateProfileState())
    val state: StateFlow<CreateProfileState> = _state.asStateFlow()
    
    private val _events = MutableStateFlow<CreateProfileEvent?>(null)
    val events: StateFlow<CreateProfileEvent?> = _events.asStateFlow()
    
    /**
     * Crea el perfil del usuario con avatar
     */
    fun createProfile(
        displayName: String,
        avatarType: AvatarType
    ) {
        viewModelScope.launch {
            Log.d(TAG, "🚀 Iniciando creación de perfil...")
            _state.value = _state.value.copy(isLoading = true, error = null)
            
            // 1. Generar claves Nostr
            Log.d(TAG, "🔑 Generando claves Nostr...")
            val keyPairResult = nostrRepository.generateKeyPair()
            
            if (keyPairResult.isFailure) {
                val error = keyPairResult.exceptionOrNull() ?: Exception("Error desconocido")
                Log.e(TAG, "❌ Error generando claves: ${error.message}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error generando claves: ${error.message}"
                )
                _events.value = CreateProfileEvent.ProfileCreationFailed(error.message ?: "Error desconocido")
                return@launch
            }
            
            val keyPair = keyPairResult.getOrNull()!!
            Log.d(TAG, "✅ Claves generadas: ${keyPair.getShortNpub()}")
            
            // 2. Guardar claves de forma segura
            Log.d(TAG, "💾 Guardando claves...")
            val saveResult = nostrRepository.saveKeysSecurely(keyPair)
            
            if (saveResult.isFailure) {
                val error = saveResult.exceptionOrNull() ?: Exception("Error desconocido")
                Log.e(TAG, "❌ Error guardando claves: ${error.message}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error guardando claves: ${error.message}"
                )
                _events.value = CreateProfileEvent.ProfileCreationFailed(error.message ?: "Error desconocido")
                return@launch
            }
            
            // 3. Crear cuenta de usuario
            val userAccount = UserAccount(
                publicKey = keyPair.publicKeyHex,
                privateKeyEncrypted = keyPair.privateKeyHex.toByteArray(), // TODO: Encriptar
                displayName = displayName,
                avatarType = avatarType,
                relayList = NostrRepositoryImpl.DEFAULT_RELAYS
            )
            
            Log.d(TAG, "💕 Cuenta creada: $displayName con avatar $avatarType")
            
            // 4. Publicar perfil en Nostr (Kind 0)
            Log.d(TAG, "📤 Publicando perfil en Nostr...")
            val publishResult = nostrRepository.publishProfile(
                displayName = displayName,
                about = "💕 Usando Cerdita - La app de chat más romántica 🐷🤗🐨",
                picture = null // TODO: URL del avatar seleccionado
            )
            
            if (publishResult.isFailure) {
                Log.w(TAG, "⚠️ Error publicando perfil (no crítico): ${publishResult.exceptionOrNull()?.message}")
                // Continuamos aunque falle la publicación
            } else {
                Log.d(TAG, "✅ Perfil publicado en Nostr")
            }
            
            // 5. Perfil creado exitosamente
            _state.value = _state.value.copy(
                isLoading = false,
                profileCreated = true,
                generatedKeyPair = keyPair,
                userAccount = userAccount
            )
            
            _events.value = CreateProfileEvent.ProfileCreatedSuccessfully(keyPair)
            Log.d(TAG, "🎉 ¡Perfil creado exitosamente!")
        }
    }
    
    /**
     * Limpia el evento después de ser consumido
     */
    fun consumeEvent() {
        _events.value = null
    }
    
    /**
     * Obtiene la clave pública para copiar
     */
    fun getPublicKeyToCopy(): String {
        return _state.value.generatedKeyPair?.publicKeyHex ?: ""
    }
}

/**
 * Estado de la pantalla de creación de perfil
 */
data class CreateProfileState(
    val isLoading: Boolean = false,
    val profileCreated: Boolean = false,
    val generatedKeyPair: NostrKeyPair? = null,
    val userAccount: UserAccount? = null,
    val error: String? = null,
    val displayName: String = "",
    val selectedAvatar: AvatarType = AvatarType.PIGGY
)

/**
 * Eventos de la pantalla de creación de perfil
 */
sealed class CreateProfileEvent {
    data class ProfileCreatedSuccessfully(val keyPair: NostrKeyPair) : CreateProfileEvent()
    data class ProfileCreationFailed(val error: String) : CreateProfileEvent()
}
