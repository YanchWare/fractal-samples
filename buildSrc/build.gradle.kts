plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}


repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}


dependencies {
    implementation("com.adarshr:gradle-test-logger-plugin:3.2.0")
}