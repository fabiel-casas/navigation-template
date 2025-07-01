plugins {
    `android-library`
    `kotlin-android`
}

module(
    name = "feature.product.domain"
)

android {
    // ... existing configurations like namespace, compileSdk, defaultConfig, etc.

    // >>> Explicitly set Java compatibility for Android Gradle Plugin <<<
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    // >>> Explicitly set Kotlin JVM toolchain for this Android module <<<
    // This will ensure KSP also targets 17
    kotlinOptions {
        // This is the traditional way, jvmToolchain in kotlin {} block is newer/preferred but this works
        jvmTarget = "21"
    }

    // Or, for a more modern approach, use the jvmToolchain directly in the module's build.gradle.kts:
    // Make sure this is at the root level of the module's build.gradle.kts, outside the android {} block.
    // This will override what the subprojects block tries to do for Kotlin.
    // kotlin {
    //    jvmToolchain(17)
    // }
}
