# Table of Contents
- [Introduction](#introduction)
- [Structure](#structure)
- [Build and run the project locally](#build-and-run-the-project-locally)
  * [Connect to YanchWare Maven Repository](#connect-to-yanchware-maven-repository)

## Introduction

This repository is split into different projects that test, in isolation, all the components [Fractal Cloud](https://fractal.cloud/) using the different cloud providers supported. 

The approach used here is LiveSystem-First so that we can use the powerful SDK constructs in order to define infrastructure through classic IaC approach.
This is a good enough approach for quick prototyping and MVPs.
However, we advise to use the Fractal-First approach as soon as possible, as that will allow delegating more control to the development teams without hindering governance and security.

You can find more information on this in our [documentation](https://fractal.cloud/docs)

## Structure

The repository is split into different directories based on the cloud provider. See the list below for easier navigation.

| Cloud Provider        	| Description                                           	|
|-----------------------	|-------------------------------------------------------	|
| [ Azure ]( ./azure/ ) 	| Collection of sample projects using our SDK for Azure 	|

## Build and run the project locally

Each sample project has it's own instructions. Please refer to those for more details.

In most of the projects you will need these environment variables:

| Environment variable 	| Description                                                                                                	|
|----------------------	|------------------------------------------------------------------------------------------------------------	|
| RESOURCE_GROUP_ID    	| The name of the resource group you are working on in Fractal Cloud                                         	|
| CLIENT_ID            	| Credentials used to authenticate to Fractal. This field is required by the Fractal SDK                     	|
| CLIENT_SECRET        	| Credentials used to authenticate to Fractal. This field is required by the Fractal SDK                    	|
| ENVIRONMENT_ID       	| Required to identify the Azure Subscription or the GCP Project on which the Fractal Cloud Agent is active 	|

### Connect to YanchWare Maven Repository

You can authenticate to YanchWare Packages with Maven by editing your `~/.m2/settings.xml` file to include your personal access token. Create a new `~/.m2/settings.xml` file if one doesn't exist.
In order to get the credentials to access the packages please contact your administrator.

Sample contents:

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
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```