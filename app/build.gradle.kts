plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.deportes2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.deportes2"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation ("androidx.media3:media3-exoplayer:1.6.1")
    implementation ("com.cloudinary:cloudinary-android:3.0.2")
    implementation ("com.android.volley:volley:1.2.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation ("com.google.ai.client.generativeai:generativeai:0.9.0")
    implementation("com.google.guava:guava:31.0.1-android")
    implementation("org.reactivestreams:reactive-streams:1.0.4")
    implementation(libs.media3.ui)
    val fragment_version = "1.6.1"
    implementation("androidx.fragment:fragment:$fragment_version")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}