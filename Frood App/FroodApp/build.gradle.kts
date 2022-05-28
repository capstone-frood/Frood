buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath(Plugin.GRADLE_ANDROID)
        classpath(Plugin.GRADLE_KOTLIN)
        classpath(Plugin.JETPACK_HILT_CLASSPATH)
        classpath(Plugin.JETPACK_NAVIGATION_CLASSPATH)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }

}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register(name = "clean", type = Delete::class) {
    delete(rootProject.buildDir)
}