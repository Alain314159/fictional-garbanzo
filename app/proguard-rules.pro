# Add project specific ProGuard rules here.
# Nostr SDK
-keep class com.vitorpamplona.quartz.** { *; }
-keep class io.github.kotlingeekdev.rhodium.** { *; }

# Libsodium
-keep class org.libsodium.** { *; }

# Tink
-keep class com.google.crypto.tink.** { *; }

# Kotlinx Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote  kotlinx.serialization.AnnotationsKt

-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel { *; }
