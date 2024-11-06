plugins {
    id("samples.java-conventions")
}

dependencies {
    implementation(project(":aws.shared.config"))
    testImplementation(testFixtures(project(":aws.shared.config")))
}

description = "ambassador-sample"
