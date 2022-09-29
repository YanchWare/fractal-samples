# Introduction

This repository is using the SDK in order to instantiate a LiveSystem in [Fractal Cloud](https://fractal.cloud/)

You can find more information on this in our [documentation](https://fractal.cloud/docs) and in the parent [README](../README.md).

## Build and run the project locally

You can build the project by running the following command in its root folder:

`mvn clean compile assembly:single`

To run the project you can type the following command:

`java -jar target/sdk-sample-jar-with-dependencies.jar`

Remember that you need the following environment variables defined:

* **RESOURCE_GROUP_ID**: The name of the resource group you are working on in Fractal Cloud;
* **CLIENT_ID**: Credentials used to authenticate to Fractal. This field is required by the Fractal SDK;
* **CLIENT_SECRET**: same as above;
* **ENVIRONMENT_ID**: Required in this program to identify the Azure Subscription or the GCP Project on which the Fractal Cloud Agent is active;
