plugins {
    id(Plugin.androidLibrary)
    id(Plugin.androidKotlin)
}
android {
    namespace = "com.example.common"
    compileSdk = App.compileSdk

    defaultConfig {
        minSdk = App.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

    kotlinOptions {
        jvmTarget = Compiler.jvmTarget
    }
}

dependencies {
    implementation(Dependency.coreKtx)
    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitAndroidEx)
    androidTestImplementation(Dependency.espresso)
    androidTestImplementation(Dependency.testRunner)
    androidTestImplementation(Dependency.testRules)
}