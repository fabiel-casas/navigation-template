import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.gradle)
}

// Configure JVM toolchain for all projects in this build
subprojects {
    val jvmVersion = 21

    // Apply to Kotlin projects
    plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin>().configureEach {
        kotlin {
            jvmToolchain(jvmVersion)
        }
    }

    // Apply to Java projects
    plugins.withType<JavaPlugin>().configureEach {
        java {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(jvmVersion))
            }
        }
    }
}