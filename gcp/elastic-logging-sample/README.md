# Elastic Logging Sample 

## Introduction

The purpose of this project is to show an example of instantiating a GKE cluster with ECK (Elastic, Kibana, APM, Fluentd) as a Logging solution.

You can find more information on this in our [documentation](https://fractal.cloud/docs).

## Run project

In order to run the project, first, please consult the main [README](../../README.md#build-and-run-the-project-locally) for details on how to set up your environment.

You can build the project by running the following command in its root folder:

`mvn clean compile assembly:single`

To run the project you can type the following command:

`java -jar target/elastic-logging-sample-jar-with-dependencies.jar`

#### [Go back to previous README](../../gcp/README.md)
