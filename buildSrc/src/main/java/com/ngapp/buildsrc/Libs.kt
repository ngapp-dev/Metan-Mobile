package com.ngapp.buildsrc

object Versions {
    const val Compose = "1.4.3"
    const val CoreKtx = "1.9.0"
}

object SupportLib {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
    const val Appcompat = "androidx.appcompat:appcompat:1.6.1"
    const val Material = "com.google.android.material:material:1.8.0"
    const val CoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    const val CoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"

    const val Splashscreen = "androidx.core:core-splashscreen:1.0.0-beta02"
    const val Timber = "com.jakewharton.timber:timber:5.0.1"
    const val Paging = "androidx.paging:paging-runtime-ktx:3.1.1"
    const val Desugaring = "com.android.tools:desugar_jdk_libs:2.0.2"
}

object ComposeLib {
    const val Ui = "androidx.compose.ui:ui:1.3.3"
    const val Material = "androidx.compose.material:material:1.3.1"
    const val Material3 = "androidx.compose.material3:material3:1.0.1"
    const val Preview = "androidx.compose.ui:ui-tooling-preview:1.3.3"
    const val Runtime = "androidx.compose.runtime:runtime:1.3.3"
    const val Foundation = "androidx.compose.foundation:foundation:1.3.1"
    const val MaterialIconCore = "androidx.compose.material:material-icons-core:1.3.1"
    const val MaterialIconExtended = "androidx.compose.material:material-icons-extended:1.3.1"
    const val Tooling = "androidx.compose.ui:ui-tooling:1.3.3"
    const val Manifest = "androidx.compose.ui:ui-test-manifest:1.3.3"

    const val Activity = "androidx.activity:activity-compose:1.4.0"
    const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    const val Lottie = "com.airbnb.android:lottie-compose:5.0.3"
    const val Paging = "androidx.paging:paging-compose:1.0.0-alpha14"
    const val Coil = "io.coil-kt:coil-compose:2.0.0-rc02"
}

object NavigationLib {
    const val Navigation = "androidx.navigation:navigation-compose:2.5.3"
    const val DestinationCore = "io.github.raamcosta.compose-destinations:core:1.4.4-beta"
    const val DestinationKsp = "io.github.raamcosta.compose-destinations:ksp:1.4.4-beta"
    const val DestinationAnimation =
        "io.github.raamcosta.compose-destinations:animations-core:1.4.4-beta"
}

object AccompanistLib {
    const val Swiperefresh = "com.google.accompanist:accompanist-swiperefresh:0.23.1"
    const val Systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:0.23.1"
    const val Insets = "com.google.accompanist:accompanist-insets:0.23.1"
    const val PlaceholderMaterial = "com.google.accompanist:accompanist-placeholder-material:0.23.1"
    const val NavigationMaterial = "com.google.accompanist:accompanist-navigation-material:0.23.1"
    const val Permissions = "com.google.accompanist:accompanist-permissions:0.23.1"
    const val Pager = "com.google.accompanist:accompanist-pager:0.23.1"
    const val Indicators = "com.google.accompanist:accompanist-pager-indicators:0.23.1"
    const val Webview = "com.google.accompanist:accompanist-webview:0.24.6-alpha"
}

object NetworkLib {
    const val Moshi = "com.squareup.moshi:moshi-kotlin:1.13.0"
    const val MoshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"
    const val MoshiLazyAdapter = "com.serjltt.moshi:moshi-lazy-adapters:2.2"
    const val Retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val RetrofitMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"
    const val Okhttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.6"
    const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6"
    const val ChuckerDebug = "com.github.chuckerteam.chucker:library:3.5.2"
    const val ChuckerRelease = "com.github.chuckerteam.chucker:library-no-op:3.5.2"
    const val RssParse = "com.prof18.rssparser:rssparser:4.0.2"


    const val KotlinXSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    const val KotlinXSerializationRetrofit =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
}

object MapsLib {
    const val Maps = "com.google.maps.android:maps-compose:2.8.0"
    const val MapsServices = "com.google.android.gms:play-services-maps:18.1.0"
    const val MapsWidgets = "com.google.maps.android:maps-compose-widgets:2.8.0"
    const val MapsSecrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1"
}

object StorageLib {
    const val RoomKtx = "androidx.room:room-ktx:2.5.0"
    const val RoomCompiler = "androidx.room:room-compiler:2.5.0"
    const val DatastorePref = "androidx.datastore:datastore-preferences:1.0.0"
    const val Datastore = "androidx.datastore:datastore:1.0.0"
    const val SecurityPref = "androidx.security:security-crypto-ktx:1.1.0-alpha05"
}

object ServicesLib {
    const val Location = "com.google.android.gms:play-services-location:21.0.1"
    const val BroadcastManager = "androidx.localbroadcastmanager:localbroadcastmanager:1.0.0"
    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha01"
    const val LifecycleService = "androidx.lifecycle:lifecycle-service:2.4.0-alpha01"
}

object FirebaseLib {
    const val Base = "com.google.android.gms:play-services-base:18.2.0"
    const val Bom = "com.google.firebase:firebase-bom:31.2.2"
    const val Analytics = "com.google.firebase:firebase-analytics-ktx"
    const val Config = "com.google.firebase:firebase-config-ktx"
    const val Crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val Push = "com.google.firebase:firebase-messaging-ktx:23.0.3"
    const val Ads = "com.google.android.gms:play-services-ads:21.5.0"
}

object DaggerHiltLib {
    const val Android = "com.google.dagger:hilt-android:2.45"
    const val Compiler = "com.google.dagger:hilt-android-compiler:2.45"
    const val Compose = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object TestingLib {
    const val Junit = "junit:junit:4.13.2"
    const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
    const val Truth = "com.google.truth:truth:1.1.3"
    const val Robolectric = "org.robolectric:robolectric:4.7.3"
    const val Turbine = "app.cash.turbine:turbine:0.7.0"
    const val Mockk = "io.mockk:mockk:1.12.3"
    const val Okhttp = "com.squareup.okhttp3:mockwebserver:5.0.0-alpha.6"
}

object AndroidTestingLib {
    const val JunitExt = "androidx.test.ext:junit:1.1.5"
    const val ComposeTestJunit = "androidx.compose.ui:ui-test-junit4:1.3.3"
    const val EspressoCore = "androidx.test.espresso:espresso-core:3.5.1"
}