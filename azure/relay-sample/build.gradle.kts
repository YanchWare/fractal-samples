plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":azure.shared.config"))
    testImplementation(testFixtures(project(":azure.shared.config")))
}

description = "relay-sample"
