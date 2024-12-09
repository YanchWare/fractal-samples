plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":aws.shared.config"))
    testImplementation(testFixtures(project(":aws.shared.config")))
}

description = "prometheus-ambassador-sample"

tasks.jar {
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")

    manifest {
        attributes(
            "Implementation-Version" to archiveVersion,
            "Main-Class" to "com.yanchware.fractal.samples.aws.prometheus.ambassador.Sample"
        )
    }

    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}