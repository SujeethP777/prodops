plugins {
    id(Plugin.androidLibrary)
    id(Plugin.androidKotlin)
    kotlin(Plugin.kapt)
    id(Plugin.hilt)
    kotlin(Plugin.jsonSerialization)
}
android {
    namespace = "com.example.data"
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
    implementation(Dependency.jsonSerialization)
    implementation(project(Dependency.common))
    implementation(project(Dependency.domain))

    implementation(Dependency.hilt)
    kapt(Dependency.hiltDagger)

    implementation(Dependency.retrofit)
    implementation(Dependency.retrofitGsonConverter)
    implementation(Dependency.retrofitHttpLogInterceptor)

    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitAndroidEx)
    androidTestImplementation(Dependency.espresso)
    androidTestImplementation(Dependency.testRunner)
    androidTestImplementation(Dependency.testRules)
}
