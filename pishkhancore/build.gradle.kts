plugins {
    id(Plugins.androidLibrary)
    kotlin(KotlinPlugins.android)
    id(KotlinPlugins.kotlinAndroid)
    id(KotlinPlugins.maven)
}

android {
    compileSdk = Application.compileSdk

    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    publishing {
        singleVariant("release")
    }
}

dependencies {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompatActivity)
    implementation(MaterialDesign.materialDesign)

    implementation(AndroidX.swipeRefreshLayout)
    implementation(Glide.glide)
    annotationProcessor(Glide.glideCompiler)
    implementation(AndroidX.browser)

    //--------------------------------Networking--------------------------------
    compileOnly(ayan.Networking.pishkhanNetworking)
    //-----------------------------------Ayan-----------------------------------
    compileOnly(ayan.WhyGoogle.whyGoogle)
    implementation("com.coolerfall:android-http-download-manager:1.6.1")
    implementation("com.github.shadowalker77:wp7progressbar:1.1.0")
    implementation("com.github.shadowalker77:pushnotification:1.1.8")
    //compileOnly("com.github.sahar-hoseinkhani:advertisement:0.1.3")
    //--------------------------------------------------------------------------

    //-----------------------------------Material Dialogs---------------------------------------
    api(MaterialDialogs.materialDialogsCore)
    api(MaterialDialogs.materialDialogsBottomSheet)
    api(MaterialDialogs.materialDialogsLifecycle)

    //Advertisement
    compileOnly(ayan.WhyGoogle.whyGoogleAdvertisement)

    //SMS Retriever API
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
    implementation ("com.google.android.gms:play-services-auth-api-phone:18.0.1")

}


afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            register("release", MavenPublication::class) {
                // Applies the component for the release build variant.
                // NOTE : Delete this line code if you publish Native Java / Kotlin Library
                from(components["release"])


                // Library Package Name (Example : "com.frogobox.androidfirstlib")
                // NOTE : Different GroupId For Each Library / Module, So That Each Library Is Not Overwritten
                groupId = "com.github.sahar-hoseinkhani"

                // Library Name / Module Name (Example : "androidfirstlib")
                // NOTE : Different ArtifactId For Each Library / Module, So That Each Library Is Not Overwritten
                artifactId = "ayan"

                // Version Library Name (Example : "1.0.0")
                version = "3.2.2"
            }
        }
    }
}
