import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.internal.catalog.DelegatingProjectDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

fun Project.android(): LibraryExtension {
    return extensions.getByType<LibraryExtension>()
}

fun Project.projects(): LibraryExtension {
    return extensions.getByType<LibraryExtension>()
}

fun Project.library(libraryAlias: String): String {
    val dependency = libs().findLibrary(libraryAlias).get().get()

    println("Library added: $dependency")

    return dependency.toString()
}

fun Project.libs(): VersionCatalog {
    return rootProject
        .extensions
        .getByType<VersionCatalogsExtension>()
        .named("libs")
}

fun Project.module(
    name: String,
    modules: Modules = Modules(),
    dependencies: Dependencies = Dependencies(),
) {
    apply<LibraryGradlePlugin>()
    android().namespace = "${ProjectConfig.namespace}.$name"
    dependencies {
        modules.projects.forEach { dependency ->
            add("implementation", dependency)
        }
        dependencies.libs.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    if (name.contains(".data")) {
        setupApi()
        dependencies {
            if (!name.contains("base.data")) {
                add("implementation", project(":feature:base:data"))
                add("implementation", project(":feature:base:presentation"))
            }

            add("implementation", project(":feature:base:domain"))
            add("implementation", library("arrow"))
        }
    }

    if (name.contains(".domain")) {
        dependencies {
            if (!name.contains("base.domain")) {
                add("implementation", project(":feature:base:domain"))
            }

            add("implementation", library("arrow"))
        }
    }

    if (name.contains(".presentation")) {
        setupCompose()
        dependencies {
            if (name.contains("base.presentation")) {
                add("implementation", library("material3"))
            } else {
                add("implementation", project(":feature:base:presentation"))
            }

            add("implementation", project(":feature:base:domain"))
            add("implementation", project(":feature:base:ui"))
            add("implementation", library("arrow"))
            add("implementation", library("serialization-json"))
        }
    }

    if (name.contains(".ui")) {
        setupCompose()

        if (!name.contains("base.ui")) {
            dependencies {
                add("implementation", project(":feature:base:ui"))
            }
        }
    }
}

class Modules(vararg projects: DelegatingProjectDependency) {
    val projects: List<DelegatingProjectDependency> = projects.asList()
}

class Dependencies(vararg libs: Provider<MinimalExternalModuleDependency>) {
    val libs: List<Provider<MinimalExternalModuleDependency>> = libs.asList()
}

fun Project.setupHilt() {
    pluginManager.apply("com.google.dagger.hilt.android")

    dependencies {
        ksp(library("hilt-android-compiler"))
        implementation(library("hilt-android"))
        implementation(library("hilt-navigation-compose"))
    }
}

fun Project.setupRoom() {
    dependencies {
        ksp(library("room-compiler"))
        implementation(library("room"))
        implementation(library("room-ktx"))
    }
}

fun Project.setupCompose() {
    dependencies {
        implementation(platform(library("compose-bom")))
        implementation(library("activity-compose"))
        implementation(library("compose-material3"))
        implementation(library("compose-ui"))
        implementation(library("compose-ui-graphics"))
        implementation(library("compose-ui-tooling"))
        implementation(library("compose-ui-tooling-preview"))
        implementation(library("constraint-layout"))
        implementation(library("flow-row"))
        implementation(library("navigation-compose"))
        implementation(library("lifecycle-runtime-compose"))
        implementation(library("lifecycle-viewmodel-compose"))
    }
}

fun Project.setupNavigation3() {
    dependencies {
        implementation(library("androidx-navigation3-runtime"))
        implementation(library("androidx-navigation3-ui"))
        implementation(library("androidx-lifecycle-viewmodel-navigation3"))
//        implementation(library("androidx-material3-adaptive-navigation3"))
    }
}

fun Project.setupApi() {
    dependencies {
        implementation(library("retrofit"))
        implementation(library("retrofit-converter-kotlinx.serialization"))
        implementation(library("retrofit-converter-scalars"))
        implementation(library("serialization-json"))
    }
}

fun Project.setupTests() {
    setupRoom()
    dependencies {
        implementation(library("compose-ui-test-junit4"))
        implementation(library("compose-ui-test-manifest"))
        implementation(library("test-runner"))
        androidTestImplementation(library("hilt-android-testing"))
    }
}