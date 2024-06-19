plugins {
    alias(libs.plugins.project.android.feature)

}

android {
    namespace = "com.pedroid.feature.main.home"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":feature:main:communicator"))
}
