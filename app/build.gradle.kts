plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Plugin de Firebase
}

android {
    namespace = "com.stomas.proyectofirebase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.stomas.proyectofirebase"
        minSdk = 31
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
}

dependencies {
    // Firebase BoM para manejar versiones compatibles
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))

    // Dependencias de Firebase
    implementation("com.google.firebase:firebase-analytics") // Firebase Analytics
    implementation("com.google.firebase:firebase-firestore-ktx") // Firestore con Kotlin Extensions
    implementation("com.google.firebase:firebase-auth-ktx") // Autenticación con Firebase

    // Dependencias ya existentes en tu archivo
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
