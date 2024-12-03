plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":gcp.shared.config"))
    testImplementation(testFixtures(project(":gcp.shared.config")))
}

description = "gke-full-sample"

tasks.jar {
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")

    manifest {
        attributes(
            "Implementation-Version" to archiveVersion,
            "Main-Class" to "com.yanchware.fractal.samples.gcp.gke.full.Sample"
        )
    }

    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}