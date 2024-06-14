plugins {
    alias(libs.plugins.project.android.feature)

}

android {
    namespace = "com.pedroid.feature.home"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    dependencies {
        testImplementation(libs.androidx.core.testing)
        testImplementation(libs.kotlinx.coroutines.test)
        testImplementation(libs.turbine)
    }
}
