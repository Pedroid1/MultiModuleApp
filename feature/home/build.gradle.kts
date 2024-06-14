plugins {
    alias(libs.plugins.project.android.feature)

}

android {
    namespace = "com.pedroid.feature.home"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}
