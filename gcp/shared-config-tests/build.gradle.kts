plugins {
    id("samples.java-conventions")
}

description = "gcp-shared-config-tests"

val testsJar by tasks.registering(Jar::class) {
    archiveClassifier = "tests"
    from(sourceSets["test"].output)
}
