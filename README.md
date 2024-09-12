# Table of Contents
- [Introduction](#introduction)
- [Structure](#structure)
- [Build and run the project locally](#build-and-run-the-project-locally)
  * [Connect to YanchWare Maven Repository](#connect-to-yanchware-maven-repository)

## Introduction

This repository is split into different projects that test, in isolation, all the components supported by [Fractal Cloud](https://fractal.cloud/) using different cloud providers. 

The approach used here is LiveSystem-First so that we can use the powerful SDK constructs in order to define infrastructure through classic IaC approach.
This is a good enough approach for quick prototyping and MVPs.
However, we advise to use the Fractal-First approach as soon as possible, as that will allow delegating more control to the development teams without hindering governance and security.

You can find more information on this in our [documentation](https://fractal.cloud/docs)

## Structure

The repository is split into different directories based on the cloud provider. See the list below for easier navigation.

| Cloud Provider        	                 | Description                                                        |
|-----------------------------------------|--------------------------------------------------------------------|
| [ AWS ](./aws/) 	                       | Collection of sample projects using our SDK for AWS 	              |
| [ Azure ](./azure/) 	                   | Collection of sample projects using our SDK for Azure 	            |
| [ GCP   ](./gcp/) 	                     | Collection of sample projects using our SDK for GCP   	            |
| [ OCI   ](./oci/) 	                     | Collection of sample projects using our SDK for OCI   	            |
| [ Fractal First   ](./fractal-first/) 	 | A sample projects for governable Fractals in large Enterprises   	 |

### Connect to YanchWare Maven Repository

A [GitHub Personal Access Token (PAT)]((https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens)) 
with `read:packages` permission is required to install our packages as they are currently hosted on GitHub Packages.

You can then authenticate to YanchWare Packages with Maven by editing your `~/.m2/settings.xml` file to include your PAT. 
Create or extend the file at `~/.m2/settings.xml`. Feel free to use the following as a sample for its contents:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>YOUR_COMPANY</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>YOUR_COMPANY</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>yanchware</id>
          <url>https://maven.pkg.github.com/YanchWare/*</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>yanchware</id>
      <username>GITHUB_USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```