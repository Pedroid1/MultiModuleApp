import com.pedroid.modularizationpluginsetup.ProjectBuildType

plugins {
    alias(libs.plugins.project.android.application)
    alias(libs.plugins.project.android.hilt)
    alias(libs.plugins.project.android.navigation)
    alias(libs.plugins.project.kotlin.detekt)
}

android {
    namespace = "com.pedroid.modularizationpluginsetup"

    defaultConfig {
        applicationId = "com.pedroid.modularizationpluginsetup"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = ProjectBuildType.DEBUG.applicationIdSuffix
        }
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = ProjectBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:testing"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:home"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.truth)
    androidTestImplementation(libs.truth)
    testImplementation(libs.mockk)
    androidTestImplementation (libs.mockk.android)

    // Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    androidTestImplementation  (libs.hilt.android.testing)
    kaptAndroidTest (libs.hilt.compiler)
    testImplementation (libs.hilt.android.testing)
    kaptTest (libs.hilt.compiler)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    kapt(libs.androidx.lifecycle.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation (libs.kotlinx.coroutines.test)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    androidTestImplementation(libs.androidx.navigation.testing)

    // Detekt
    detektPlugins(libs.detekt.formatting)

    implementation(libs.lottie)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
}

kapt {
    correctErrorTypes = true
}