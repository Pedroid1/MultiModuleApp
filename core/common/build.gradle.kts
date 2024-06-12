plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.android.hilt)
}

android {
    namespace = "com.pedroid.core.common"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.material)
}