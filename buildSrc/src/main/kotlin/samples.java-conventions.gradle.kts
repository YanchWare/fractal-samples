import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("java")
    id("com.adarshr.test-logger")
}

repositories {
    mavenLocal()

    maven {
        name = "YanchWare Maven Packages"
        url = uri("https://maven.pkg.github.com/YanchWare/fractal-java-sdk")
        credentials {
            username = System.getenv("GITHUB_MAVEN_REGISTRY_USER")
            password = System.getenv("GITHUB_MAVEN_REGISTRY_TOKEN")
        }
    }

    mavenCentral()
}

group = "com.yanchware.fractal"
version = "2.0.0"
//java.sourceCompatibility = JavaVersion.VERSION_21

var lombokVersion = "1.18.34"

dependencies {
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("com.yanchware:fractal.sdk:10.0.2")
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

tasks.jar {
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")

    manifest {
        attributes(
            "Implementation-Version" to archiveVersion,
            "Main-Class" to "com.yanchware.fractal.samples.environment.initialization.EnvironmentInitializationSample"
        )
    }

    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
