plugins {
    id("samples.java-conventions")
}

description = "oci-shared-config-tests"

val testsJar by tasks.registering(Jar::class) {
    archiveClassifier = "tests"
    from(sourceSets["test"].output)
}

