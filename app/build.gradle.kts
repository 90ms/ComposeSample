plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = projectApplicationId
    compileSdk = projectCompileSdkVersion

    defaultConfig {
        applicationId = projectApplicationId
        minSdk = projectMinSdkVersion
        targetSdk = projectTargetSdkVersion
        versionCode = projectVersionCode
        versionName = projectVersionName

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
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeDependencies.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":library:navigation"))
    implementation(project(":library:design"))
    DIDependencies.apply {
        implementation(hiltAndroid)
        kapt(dagerHiltCompiler)
        implementation(hiltWork)
    }
    TestDependencies.apply {
        testImplementation(junit)
        androidTestImplementation(junitExt)
    }
    implementation(ComposeDependencies.composeActivity)
    implementation(LifeCycleDependencies.lifeCycleRuntimeKtx)
    implementation(AndroidxDependencies.coreKtx)
    implementation(WorkDependencies.workRuntimeKtx)
}

kapt {
    correctErrorTypes = true
}