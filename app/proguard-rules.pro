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