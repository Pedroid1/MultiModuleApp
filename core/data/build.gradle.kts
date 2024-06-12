plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.android.hilt)
    alias(libs.plugins.project.android.room)
}

android {
    namespace = "com.pedroid.data"
}

dependencies {
    implementation(project(":core:common"))
    androidTestImplementation(libs.androidx.junit)
}