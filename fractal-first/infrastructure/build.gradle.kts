plugins {
    id("samples.java-conventions")
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
