// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        // Otros repositorios si los tienes
    }
    dependencies {
        // Otras dependencias
        classpath("com.google.gms:google-services:4.3.15") // Asegúrate de usar la versión correcta aquí
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
}