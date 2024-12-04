import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("java")
    id("com.adarshr.test-logger")
}

repositories {
    mavenLocal()
    mavenCentral()
}

group = "com.yanchware.fractal"
version = "2.0.0"
//java.sourceCompatibility = JavaVersion.VERSION_21

var lombokVersion = "1.18.34"

dependencies {
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("com.yanchware:fractal.sdk:11.1.0")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    testImplementation("org.junit-pioneer:junit-pioneer:2.2.0")
    testImplementation("org.mockito:mockito-core:5.13.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.13.0")
    testImplementation("com.flextrade.jfixture:jfixture:2.7.2")

    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testlogger {
        theme = ThemeType.MOCHA
        showFullStackTraces = true
    }
}
