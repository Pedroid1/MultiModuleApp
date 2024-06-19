import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidNavigationComponentConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("androidx.navigation.fragment.ktx").get())
                add("implementation", libs.findLibrary("androidx.navigation.ui.ktx").get())
                add("androidTestImplementation", libs.findLibrary("androidx.navigation.testing").get())
            }
        }
    }
}