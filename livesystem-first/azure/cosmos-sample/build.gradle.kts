plugins {
    id("samples.java-conventions")
    id("com.mikepenz.aboutlibraries.plugin") version "11.4.0-b01"
}

dependencies {
    implementation(project(":azure.shared.config"))
    testImplementation(testFixtures(project(":azure.shared.config")))
}

description = "cosmos-sample"

tasks.jar {
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")

    manifest {
        attributes(
            "Implementation-Version" to archiveVersion,
            "Main-Class" to "com.yanchware.fractal.samples.azure.cosmos.Sample"
        )
    }

    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}