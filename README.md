# Table of Contents
- [Introduction](#introduction)
- [Structure](#structure)
- [Build and run the project locally](#build-and-run-the-project-locally)
  * [Connect to YanchWare Maven Repository](#connect-to-yanchware-maven-repository)

## Introduction

This repository is split into two main categories of samples: 

* **LiveSystem-First**: Using the approach we named "Live-System first" you are able to describe the desired state of your infrastructure through code, pretty much as you might already be doing today.
However, the difference is that with very limited amount of code you will be able to spawn tailored infrastructure to the need of your application using standard and security-hardened components ready to support your application and services.
When you are then ready for enterprise grade, you can extract a reusable Blueprint from your running Live System, making it available for your colleagues across your organization, ensuring cross-pollination of proven best-practices, technologies and architectures.

* **Fractal-First**: Through the approach we named "Fractal first" you tackle Cloud-Native complexity at the enterprise level from the very beginning.
You will first define Blueprints, reusable sets of components and their relations, which put the basis for a compliant solutions in your IT Landscape. To each Blueprint you can then associate an Interface, a versioned set of operations through which the Blueprint can be modified ensuring the application compliance remains unaltered.
Any teams authorized will then be able to instantiate a specific Blueprint on any cloud vendor you have enabled, resulting in the creation of a Live System they own.
The latter will be able to be extended and tailored directly by the team owning it without requiring any access to any actual cloud environment; they will instead use the APIs exposed by the associated Interface.

You can find more information on this in our [documentation](https://fractal.cloud/docs)

## Structure

The repository is split into different directories based on the cloud provider. See the list below for easier navigation.

| Cloud Provider        	                                        | Description                                                                                                                |
|----------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| [ Environment Initialization](./environment-initialization/) 	 | A sample projects for initializing an Environment through SDK                                                              |
| [ LiveSystem First](./livesystem-first/) 	                     | Isolated sample for each component supported by [Fractal Cloud](https://fractal.cloud) using different cloud providers   	 |
| [ Fractal First](./fractal-first/) 	                           | A sample project for governable Fractals in large Enterprises   	                                                          |

## Build and run the project locally

The samples are Java projects built with Gradle. The instructions below apply to every sample in this repository. You do not need to be a Java or Gradle expert to run them, and you do not need to install Gradle yourself.

**Prerequisites**

- Java Development Kit 21. Check your version with `java -version`. If you need to install or switch versions, a tool such as [SDKMAN](https://sdkman.io/) makes it easy.
- The Gradle wrapper, which is included in the repository. Use `./gradlew` on macOS and Linux, or `gradlew.bat` on Windows. The first run downloads the correct Gradle version automatically.
- A [Fractal Cloud](https://fractal.cloud) account with a Resource Group you can deploy to, and the cloud account details required by the specific sample.

**Build a sample**

Every sample is a Gradle subproject and produces a single runnable jar that bundles its dependencies. Build it with the `jar` task, replacing the project path with the one you want:

```bash
./gradlew :environment-initialization:management-environment:jar
```

The jar is written to that module's `build/libs` folder.

To validate a sample without contacting Fractal Cloud, run its tests instead:

```bash
./gradlew :environment-initialization:management-environment:test
```

**Run a sample**

Samples read their configuration from environment variables. The common way to run one is to set the variables on the same line, right before the `java -jar` command. Each line ends with a backslash so the shell treats it as a single command:

```bash
CI_CD_SERVICE_ACCOUNT_NAME="xxxx" \
CI_CD_SERVICE_ACCOUNT_SECRET="xxxx" \
# ... other variables required by the sample ... \
java -jar path/to/the-sample-2.0.0.jar
```

If a required variable is missing, the program stops immediately with a message naming the missing variable. Each sample folder has its own README listing the variables it needs.

Most samples start with `java -jar`. Some samples, including those under `environment-initialization`, need the class to run specified explicitly. In that case use `java -cp path/to/the-sample-2.0.0.jar <fully.qualified.MainClass>`, where the class is listed in the sample's own README.

**Authentication**

All samples authenticate to the Fractal Cloud API through two environment variables, in addition to the variables of the specific sample:

| Variable | Description |
|----------|-------------|
| `CI_CD_SERVICE_ACCOUNT_NAME` | The service account identifier (client id) used to call the Fractal Cloud API. |
| `CI_CD_SERVICE_ACCOUNT_SECRET` | The service account secret (client secret) paired with the name above. |

### Connect to YanchWare Maven Repository

The samples depend on the YanchWare Fractal SDK (`com.yanchware:fractal.sdk`). The build resolves it from Maven Central, so no extra repository configuration or credentials are required. Gradle downloads the SDK and the other dependencies on the first build and caches them for later runs.
