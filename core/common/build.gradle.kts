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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}