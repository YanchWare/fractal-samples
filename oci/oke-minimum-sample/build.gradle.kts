plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":oci.shared.config"))
    testImplementation(project(":oci.shared.config.tests"))
}

description = "oke-minimum-sample"
