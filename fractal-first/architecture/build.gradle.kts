plugins {
    id("samples.java-conventions")
    id("com.mikepenz.aboutlibraries.plugin") version "11.4.0-b01"
}

dependencies {
    implementation(project(":migration.infrastructure"))
}

group = "com.yanchware.fractal.pocs"
description = "pocs.migration.architecture"

tasks.jar {
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")

    manifest {
        attributes(
            "Implementation-Version" to archiveVersion,
            "Main-Class" to "com.yanchware.fractal.pocs.migration.architecture.application.Main"
        )
    }

    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}