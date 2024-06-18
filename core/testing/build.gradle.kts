plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.android.hilt)
}

android {
    namespace = "com.pedroid.core.testing"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    api(libs.truth)
    api(libs.mockk)
    api(libs.androidx.core.testing)
    api(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)

}