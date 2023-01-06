package dependencies

object Build {
    val kotlinGradlePlugin ="org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val buildTools= "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlinExtension ="org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    val safeArgsPlugin= "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val hilt= "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    val googleService= "com.google.gms:google-services:${Versions.googleServices}"
    val crashlytics= "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics}"
}