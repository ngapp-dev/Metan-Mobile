import com.ngapp.buildsrc.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.ngapp.framework"
    compileSdk = Configs.CompileSdk

    defaultConfig {
        minSdk = Configs.MinSdk
        testInstrumentationRunner = Configs.AndroidJunitRunner
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = listOf(
            "-Xjvm-default=all",
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.Experimental",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.InternalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview"
        )
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("org.robolectric:robolectric:4.7.3")
    testImplementation("app.cash.turbine:turbine:0.7.0")
    testImplementation("io.mockk:mockk:1.12.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // Network
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
//    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.serjltt.moshi:moshi-lazy-adapters:2.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.6")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.6")
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")
    implementation("io.ktor:ktor-client-cio:1.6.7")

    // Local
    implementation("androidx.room:room-ktx:2.5.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha05")

    // Common
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    // Firebase
    implementation("com.google.android.gms:play-services-base:18.0.1")
    implementation("com.google.firebase:firebase-analytics-ktx:20.1.2")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.2.9")
    implementation("com.google.firebase:firebase-messaging-ktx:23.0.3")
    implementation("com.google.firebase:firebase-config-ktx:21.0.2")

    // Huawei
//    implementation("com.huawei.hms:base:6.4.0.303")
//    implementation("com.huawei.hms:hianalytics:6.4.1.302")
//    implementation("com.huawei.agconnect:agconnect-crash:1.6.5.300")
//    implementation("com.huawei.hms:push:6.3.0.304")
//    implementation("com.huawei.agconnect:agconnect-remoteconfig:1.6.5.300")
}