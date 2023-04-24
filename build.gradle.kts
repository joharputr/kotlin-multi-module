// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
    }
}

plugins {
    id ("com.android.application") version "7.3.0" apply false
    id ("com.android.library") version "7.3.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.android.dynamic-feature") version "7.3.0" apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}