plugins {
    id(Plugin.androidLibrary)
    id(Plugin.androidKotlin)
    kotlin(Plugin.kapt)
    id(Plugin.hilt)
}
android {
    namespace = "com.prodops.domain"
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
    implementation(Dependency.compose)
    implementation(Dependency.composeNavigation)
    implementation(platform(Dependency.composeBom))
    implementation(Dependency.composeUI)
    implementation(Dependency.composeUIGraphics)
    implementation(Dependency.composePreview)
    implementation(Dependency.material3)
    implementation(Dependency.jsonSerialization)
    implementation(Dependency.retrofitGsonConverter)
    implementation(project(Dependency.common))
    implementation(Dependency.hilt)
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    kapt(Dependency.hiltDagger)
    implementation(Dependency.hiltNav)

    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitAndroidEx)
    androidTestImplementation(Dependency.espresso)
    androidTestImplementation(Dependency.testRunner)
    androidTestImplementation(Dependency.testRules)
}
