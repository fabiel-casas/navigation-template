import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AppModuleGradlePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply("com.google.devtools.ksp")
            setupHilt()
            setupCompose()
            setupRoom()
            setupNavigation3()
            dependencies {
                implementation(library("serialization-json"))
                modules(
                    implementation = arrayOf(
                        ":feature:base:presentation",
                        ":feature:base:ui",
                        ":feature:home:presentation",
                        ":feature:bonus:presentation",
                        ":feature:product:presentation",
                        ":feature:cooking:presentation",
                        ":feature:mylist:presentation",
                    )
                )
            }
        }
    }
}