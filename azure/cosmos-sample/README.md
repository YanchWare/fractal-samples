# Cosmos sample 

## Introduction

The purpose of this project is to test the instantiation of a Cosmos Account using all supported variations.
At the time of writing, these are:

* [PostgreSQL](https://learn.microsoft.com/en-us/azure/cosmos-db/postgresql/)
* [Apache Gremlin](https://learn.microsoft.com/en-us/azure/cosmos-db/gremlin/)
* [Apache Cassandra](https://learn.microsoft.com/en-us/azure/cosmos-db/cassandra/)
* [MongoDB](https://learn.microsoft.com/en-us/azure/cosmos-db/mongodb/)
* [Azure Cosmos NoSql](https://learn.microsoft.com/en-us/azure/cosmos-db/nosql/)
* [Azure Cosmos Tables](https://learn.microsoft.com/en-us/azure/cosmos-db/table/)

You can find more information on this in our [documentation](https://fractal.cloud/docs).

## Run project

In order to run the project, first, please consult the main [README](../../README.md#build-and-run-the-project-locally) for details on how to set up your environment.

You can build the project by running the following command in its root folder:

`mvn clean compile assembly:single`

To run the project you can type the following command:

`java -jar target/cosmos-sample-jar-with-dependencies.jar`

#### [Go back to previous README](../README.md)
