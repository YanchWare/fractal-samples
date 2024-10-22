plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":migration.infrastructure"))
}

group = "com.yanchware.fractal.pocs"
description = "pocs.migration.architecture"
