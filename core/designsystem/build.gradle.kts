plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.android.hilt)
}

android {
    namespace = "com.pedroid.core.designsystem"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.lottie)
}