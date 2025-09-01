plugins {
    `android-library`
    `kotlin-android`
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
}

module(
    name = "feature.base.presentation",
    modules = projects.feature.run {
        Modules(
            bonus.ui,
            product.ui,
        )
    },
    dependencies = Dependencies(
        libs.androidx.navigation3.ui,
        libs.androidx.navigation3.runtime,
        libs.androidx.lifecycle.viewmodel.navigation3,
    )
)

android {
    // ... existing configurations like namespace, compileSdk, defaultConfig, etc.

    // >>> Explicitly set Java compatibility for Android Gradle Plugin <<<
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}