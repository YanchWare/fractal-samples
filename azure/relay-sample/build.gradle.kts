plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":azure.shared.config"))
    testImplementation(project(":azure.shared.config.tests"))
}

description = "relay-sample"
