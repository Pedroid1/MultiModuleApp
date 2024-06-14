plugins {
    alias(libs.plugins.project.android.feature)
}

android {
    namespace = "com.pedroid.feature.onboarding"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":feature:home"))

    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
}