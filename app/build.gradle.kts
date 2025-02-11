plugins {
    id(Plugin.androidApp)
    id(Plugin.androidKotlin)
    kotlin(Plugin.kapt)
    id(Plugin.hilt)
    id(Plugin.ktlint) version Dependency.Version.ktlint
}

android {
    namespace = App.namespace
    compileSdk = App.compileSdk

    defaultConfig {
        applicationId = App.applicationId
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compiler.kotlinCompilerExtension
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    subprojects {
        apply(Plugin.ktlint)
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Dependency.coreKtx)
    implementation(Dependency.lifeCycleRuntimeKtx)

    implementation(Dependency.compose)
    implementation(Dependency.composeNavigation)
    implementation(Dependency.composeViewModel)
    implementation(platform(Dependency.composeBom))
    implementation(Dependency.composeUI)
    implementation(Dependency.composeUIGraphics)
    implementation(Dependency.composePreview)
    implementation(Dependency.material3)

    implementation(project(Dependency.common))
    implementation(project(Dependency.data))
    implementation(project(Dependency.domain))

    implementation(Dependency.hilt)
    kapt(Dependency.hiltDagger)
    implementation(Dependency.hiltNav)

    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitAndroidEx)
    androidTestImplementation(Dependency.espresso)
    androidTestImplementation(Dependency.testRunner)
    androidTestImplementation(Dependency.testRules)
    androidTestImplementation(platform(Dependency.composeBom))
    debugImplementation(Dependency.composeJUnitUITest)
    androidTestImplementation(Dependency.composeUITest)
    debugImplementation(Dependency.composeUITooling)

    implementation ("io.coil-kt:coil-compose:2.2.2")

}
