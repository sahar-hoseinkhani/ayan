plugins {
    id(Plugins.androidApplication)
    kotlin(KotlinPlugins.android)
}

android {
    compileSdk = Application.compileSdk

    defaultConfig {
        applicationId = Application.appId
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":pishkhancore"))
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompatActivity)
    implementation(MaterialDesign.materialDesign)

    //Multi Dex
    implementation(AndroidX.multiDex)
    //Why Google
    implementation(ayan.WhyGoogle.whyGoogle)

    implementation(ayan.Networking.pishkhanNetworking)
    //-----------------------------------Ayan-----------------------------------
    implementation(ayan.WhyGoogle.whyGoogle)
    implementation("com.coolerfall:android-http-download-manager:1.6.1")
    implementation("com.github.shadowalker77:wp7progressbar:1.1.0")
    implementation("com.github.shadowalker77:pushnotification:1.1.8")
}