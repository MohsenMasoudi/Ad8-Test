package dependencies

object Dependencies {
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val coroutinesCore ="org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid= "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    //android
    //noinspection GradleDependency

    val fragmentExt = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    //dependency injection
    val daggerHilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltExt}"
    val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"


    //network
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    val retrofit2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2}"
    val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
    val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
    val coroutinesRetrofitAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesRetrofitAdapter}"



    //parser
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"

    val zelory = "id.zelory:compressor:${Versions.zelory}"

    val firebaseKtx = "com.google.firebase:firebase-messaging-ktx"
    val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    val analytics = "com.google.firebase:firebase-analytics"

    val playServiceMap = "com.google.android.gms:play-services-maps:17.0.1"
    val playServiceLocation = "com.google.android.gms:play-services-location:18.0.0"

    //dependency injection
    val workRunTime = "androidx.work:work-runtime-ktx:2.7.1"

    //ui


    //other


    // CameraX core library using the camera2 implementation


    // The following line is optional, as the core library is included indirectly by camera-camera2
    val cameraCore = "androidx.camera:camera-core:${Versions.camerax}"
    val cameraX2 = "androidx.camera:camera-camera2:${Versions.camerax}"

    // If you want to additionally use the CameraX Lifecycle library
    val cameraXLifecycle = "androidx.camera:camera-lifecycle:${Versions.camerax}"
    // If you want to additionally use the CameraX View class
    val cameraView = "androidx.camera:camera-view:${Versions.cameraView}"
    // If you want to additionally use the CameraX Extensions library
    val cameraExtensions = "androidx.camera:camera-extensions:${Versions.cameraVExtention}"

    //other
    val hawk = "com.orhanobut:hawk:${Versions.hawk}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val easyPermissions = "pub.devrel:easypermissions:${Versions.easyPermissions}"

}

