plugins {
    `android-library`
    `kotlin-android`
    alias(libs.plugins.compose.compiler)
}

module(
    name = "feature.bonus.presentation",
    modules = projects.feature.run {
        Modules(
            bonus.domain,
            bonus.ui,
            product.domain,
            product.ui,
        )
    }
)

android {
    // ... existing configurations like namespace, compileSdk, defaultConfig, etc.

    // >>> Explicitly set Java compatibility for Android Gradle Plugin <<<
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}