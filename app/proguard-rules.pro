<<<<<<< HEAD
# Add project specific ProGuard rules here.

# Basic Android rules
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# Kotlin specific rules
-dontwarn kotlin.**
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }

# Android SDK rules
-keep class android.** { *; }
-dontwarn android.**

# WiFi Scanner specific rules (customize as needed)
-keep class com.example.wifiscanner.** { *; }
=======
# ProGuard configuration for WiFi Scanner app

# Basic Android configuration
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Keep Kotlin reflection
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# WiFi scanning and network-related classes
-keep class android.net.wifi.** { *; }
-keep class android.net.NetworkInfo { *; }

# Keep data model classes
-keep class com.wifiscanner.model.** { *; }

# Remove logging in release build
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

# Performance optimizations
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable

# Reduce code size
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
>>>>>>> pr-2-relayrelayrelay-scanmywifi
