plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("io.realm.kotlin")
}

android {
    namespace = "com.example.aggregationfocus"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aggregationfocus"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes +="/META-INF/proguard/androidx-annotations.pro" // I added since the code would not run or else
            excludes += "META-INF/native-image/**"                   // I added since the code would not run or else
        }
    }
}

dependencies {

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    // Mongo Realm
    implementation ("io.realm.kotlin:library-sync:1.13.0") // <-- this is the main implementations that works with the app.
    implementation("org.mongodb:mongodb-driver-sync:4.11.0")

    // MongoDB Kotlin driver dependency
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.11.0")

    // Hilt
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Desugar JDK
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.4")

    // Message Bar Compose
    implementation ("com.github.stevdza-san:MessageBarCompose:1.0.5")

    // One-Tap Compose
    implementation ("com.github.stevdza-san:OneTapCompose:1.0.3")

    // Google one-tap
    implementation ("com.google.android.gms:play-services-auth:21.0.0")

//    implementation ("org.minidns:minidns-hla:0.3.2") -> Use??


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.02"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.02"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}