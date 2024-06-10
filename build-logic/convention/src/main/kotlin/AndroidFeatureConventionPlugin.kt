import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("project.android.library")
                apply("project.android.hilt")
                apply("androidx.navigation.safeargs.kotlin")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.livedata.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("kapt", libs.findLibrary("androidx.lifecycle.compiler").get())

                add("implementation", libs.findLibrary("androidx.navigation.fragment.ktx").get())
                add("implementation", libs.findLibrary("androidx.navigation.ui.ktx").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", libs.findLibrary("androidx.navigation.testing").get()
                )
            }
        }
    }
}
