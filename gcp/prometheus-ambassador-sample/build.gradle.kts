plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":gcp.shared.config"))
    testImplementation(testFixtures(project(":gcp.shared.config")))
}

description = "prometheus-sample"
