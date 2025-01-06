plugins {
    id("samples.java-conventions")
    id("com.mikepenz.aboutlibraries.plugin") version "11.4.0-b01"
}

dependencies {
    implementation("org.apache.commons:commons-lang3")
    implementation("com.yanchware:fractal.sdk")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.assertj:assertj-core")
    testImplementation("com.flextrade.jfixture:jfixture")
}

group = "com.yanchware.fractal.pocs"
description = "migration.infrastructure"
