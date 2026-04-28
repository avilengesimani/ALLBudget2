plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.all.allbudgeting"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.all.allbudgeting"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

        val room_version = "2.6.1"
        implementation("androidx.room:room-runtime:$room_version")
        // If you are using KSP instead of KAPT, use ksp("androidx.room:room-compiler:$room_version")
        // But for most default setups, use kapt or annotationProcessor
        annotationProcessor("androidx.room:room-compiler:$room_version")
        implementation("androidx.room:room-ktx:$room_version")

}