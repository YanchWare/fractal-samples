# Prometheus in AKS sample 

## Introduction

The purpose of this project is to test the instantiation of Prometheus alongside Ambassador in AKS.

You can find more information on this in our [documentation](https://fractal.cloud/docs).

## Run project

In order to run the project, first, please consult the main [README](../../README.md#build-and-run-the-project-locally) for details on how to set up your environment.

You can build the project by running the following command in its root folder:

`mvn clean compile assembly:single`

To run the project you can type the following command:

`java -jar target/prometheus-ambassador-sample-jar-with-dependencies.jar`

#### [Go back to previous README](../README.md)
