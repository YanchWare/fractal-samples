plugins {
    id("samples.java-conventions")
    id("com.mikepenz.aboutlibraries.plugin") version "11.4.0-b01"
}

description = "environment-initialization"

tasks.jar {
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")

    manifest {
        attributes(
            "Implementation-Version" to archiveVersion,
            "Main-Class" to "com.yanchware.fractal.samples.environment.initialization.EnvironmentInitializationSample"
        )
    }

    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
