plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":aws.shared.config"))
    testImplementation(project(":aws.shared.config.tests"))
}

description = "elastic-logging-sample"
