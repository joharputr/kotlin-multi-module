plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 33
    namespace = "id.co.indocyber.basemodule"
    defaultConfig {
        applicationId = "id.indocyber.baseModule"
        minSdk = 21
        targetSdk = 33
        versionCode = 5
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding {
        enable = true
    }
    buildFeatures {
        viewBinding = true
    }
    dynamicFeatures += arrayOf(":Home")
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.google.dagger:hilt-android:2.38.1")
    implementation("com.google.android.play:core-ktx:1.8.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    api(project(":common"))

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    val multidex_version = "2.0.1"
    implementation("androidx.multidex:multidex:$multidex_version")
}