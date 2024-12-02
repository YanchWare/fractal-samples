plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":oci.shared.config"))
    testImplementation(testFixtures(project(":oci.shared.config")))
}

description = "prometheus-ambassador-sample"