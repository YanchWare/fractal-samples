plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":gcp.shared.config"))
    testImplementation(project(":gcp.shared.config.tests"))
}

description = "elastic-logging-sample"
