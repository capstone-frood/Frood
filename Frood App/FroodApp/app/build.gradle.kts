import AppProperty.Field
import AppProperty.TYPE_RES_STRING

plugins {
    id(Plugin.ANDROID)
    id(Plugin.KOTLIN_ANDROID)
    id(Plugin.KOTLIN_PARCELIZE)
    id(Plugin.KOTLIN_KAPT)
    id(Plugin.JETPACK_NAVIGATION)
    id(Plugin.JETPACK_HILT)
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Config.SDK_COMPILE_VERSION
    buildToolsVersion = Config.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = Config.SDK_MINIMUM_VERSION
        targetSdk = Config.SDK_TARGET_VERSION

        applicationId = Config.APP_ID
        versionCode = Config.APP_VERSION_CODE
        versionName = Config.APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create(Config.BuildType.RELEASE) {
            storeFile = rootProject.file(Config.KEYSTORE_PATH)
            storePassword = Config.KEYSTORE_PASSWORD
            keyAlias = Config.KEYSTORE_ALIAS
            keyPassword = Config.KEYSTORE_PASSWORD
        }
    }

    buildTypes {
        getByName(Config.BuildType.DEBUG) {
            applicationIdSuffix = Field.Debug.APP_ID_SUFFIX
            versionNameSuffix = Field.Debug.APP_VERSION_NAME_SUFFIX

            resValue(TYPE_RES_STRING, AppProperty.APP_NAME, Field.Debug.APP_NAME)
        }

        getByName(Config.BuildType.RELEASE) {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName(Config.BuildType.RELEASE)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue(TYPE_RES_STRING, AppProperty.APP_NAME, Field.Release.APP_NAME)
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("androidx.camera:camera-core:1.0.2")
    implementation("androidx.camera:camera-lifecycle:1.0.2")
    implementation("com.github.CanHub:Android-Image-Cropper:4.2.1")
    implementation("androidx.activity:activity-ktx:1.6.0-alpha04")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")

    kapt(Dependency.Jetpack.HILT_COMPILER)
    kapt(Dependency.GLIDE_COMPILER)

    implementation(Dependency.Kotlin.LANG)
    implementation(Dependency.Kotlin.COROUTINES)
    implementation(Dependency.Kotlin.COROUTINES_ANDROID)
    implementation(Dependency.Kotlin.COROUTINES_PLAY_SERVICES)

    implementation(Dependency.Jetpack.CORE)
    implementation(Dependency.Jetpack.HILT)
    implementation(Dependency.Jetpack.HILT_NAVIGATION)
    implementation(Dependency.Jetpack.LEGACY)
    implementation(Dependency.Jetpack.LIFECYCLE)
    implementation(Dependency.Jetpack.LIVE_DATA)
    implementation(Dependency.Jetpack.MULTI_DEX)
    implementation(Dependency.Jetpack.NAVIGATION_FRAGMENT)
    implementation(Dependency.Jetpack.NAVIGATION_UI)
    implementation(Dependency.Jetpack.VIEW_MODEL)

    implementation(Dependency.Jetpack.UI.APP_COMPAT)
    implementation(Dependency.Jetpack.UI.ACTIVITY)
    implementation(Dependency.Jetpack.UI.FRAGMENT)

    implementation(Dependency.Jetpack.UI.MATERIAL)
    implementation(Dependency.Jetpack.UI.CARD_VIEW)
    implementation(Dependency.Jetpack.UI.CONSTRAINT_LAYOUT)
    implementation(Dependency.Jetpack.UI.RECYCLER_VIEW)

    implementation(Dependency.GLIDE)
    implementation(Dependency.IMAGE_PICKER)
    implementation(Dependency.IMAGE_VIEWER)

    testImplementation(Dependency.Testing.JUNIT)
    androidTestImplementation(Dependency.Testing.ESPRESSO)
    androidTestImplementation(Dependency.Testing.JUNIT_EXT)
}