plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "me.amasiero"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("software.amazon.awssdk:bom:${project.property("awsJavaSdkVersion")}"))
    implementation("software.amazon.awssdk:s3")
    implementation("software.amazon.awssdk:iam")
    implementation("org.springframework.boot:spring-boot-starter-actuator:${project.property("springFrameworkVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-security:${project.property("springFrameworkVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-web:${project.property("springFrameworkVersion")}")
    implementation("org.springframework.cloud:spring-cloud-starter-open-service-broker:${project.property("springCloudOpenServiceBrokerVersion")}")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools:${project.property("springFrameworkVersion")}")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${project.property("springFrameworkVersion")}")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:localstack")
    testImplementation("org.testcontainers:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    bootJar {
        archiveFileName.set("backend.jar")
    }
}
