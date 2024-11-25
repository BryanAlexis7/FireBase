// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    dependencies {
        // Plugin de Google Services necesario para Firebase
        classpath("com.google.gms:google-services:4.4.0")
    }
}
