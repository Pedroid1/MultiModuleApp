plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.android.hilt)
}

android {
    namespace = "com.pedroid.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    testImplementation(libs.truth)
    testImplementation(libs.mockk)
}