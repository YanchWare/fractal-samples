# Elastic Datastore Sample 

## Introduction

The purpose of this project is to show an example of instantiating a OKE cluster with Elastic Datastore as a Document Storage solution.

You can find more information on this in our [documentation](https://fractal.cloud/docs).

## Run project

In order to run the project, first, please consult the main [README](../../README.md#build-and-run-the-project-locally) for details on how to set up your environment.

You can build the project by running the following command in its root folder:

`gradle clean test jar`

To run the project you can type the following command:

``` bash
FRACTAL_RESOURCE_GROUP_ID="xxxx" \
    FRACTAL_ENVIRONMENT_TYPE="xxxx" \
    FRACTAL_ENVIRONMENT_OWNER_ID="xxxx" \
    FRACTAL_ENVIRONMENT_SHORT_NAME="xxxx" \
    FRACTAL_ENVIRONMENT_NAME="xxxx" \
    OCI_TENANCY_ID="xxxx" \
    OCI_COMPARTMENT_NAME="xxxx" \
    CI_CD_SERVICE_ACCOUNT_NAME="xxxx" \
    CI_CD_SERVICE_ACCOUNT_SECRET="xxxx" \
    java -jar build/libs/oci.elastic.datastore.sample-2.0.0.jar
```

#### [Go back to previous README](../../gcp/README.md)
