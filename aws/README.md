# Table of Contents
1. [Introduction](#introduction)
2. [Projects](#projects)
# Introduction

In here you can find example using our SDK for all supported components in the AWS environment.
Consult the main [README](../README.md) for details on how to set up your environment to be able to run our projects.

The simplest way to use these samples is to build them from the parent project.
If for instance you would like to build and run the `eks-full-example` you could do it by running the following two 
commands from the current directory:

```bash
mvn clean compile assembly:single -pl shared-config,aks-full-sample

LIVE_SYSTEM_NAME="eks-full-sample" \
    RESOURCE_GROUP_ID="43f7d06d-1341-4655-b2d8-4fee6f2eb892" \
    ENVIRONMENT_TYPE="personal" \
    ENVIRONMENT_OWNER_ID="682edc62-e14d-4357-93f4-7c8b1b896716" \
    ENVIRONMENT_SHORT_NAME="yanch-sandbox-01" \
    CLIENT_ID="sp-xxx" \
    CLIENT_SECRET="xxx" \
    java -jar eks-full-sample/target/eks-full-sample-jar-with-dependencies.jar
```

The first command instruct Maven to build the `shared-config` library we need in all sample projects and the specific sample we are interested in.

The second command sets some environment variables required by the SDK to identify the [Environment](https://fractal.cloud/docs/definitions/concepts/) in which to deploy 
the infrastructure components and the [Resource Group](https://fractal.cloud/docs/definitions/concepts/) utilized to administrate access controls. 

Please, ensure that the Resource Group you are trying to use is enabled to deploy to the Environment identified.

Further information about the environment variables required are contained in the following table.

| Environment variable 	  | Description                                                                                                                                                                                                                                           |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| LIVE_SYSTEM_NAME    	     | The short name of the Live System that will be created or updated. 	                                                                                                                                                                                  |
| RESOURCE_GROUP_ID         | The id of the Resource Group you want to use to administrate access control. This information is available in the [Resource Group List](https://fractal.cloud/resource-groups) page of Fractal Cloud.                                                 |
| ENVIRONMENT_TYPE        	 | The type of the Environment being targeted, it can take the value of `personal` or `organizational`. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.             	              |
| ENVIRONMENT_OWNER_ID      | The id of the owner of the Environment being targeted. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.                                                                          |
| ENVIRONMENT_SHORT_NAME    | The shortname of the Environment being targeted. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.                                                                                |
| CLIENT_ID       	         | The Client Id of the Service Account used to authenticate to Fractal Cloud. This information is stored in a secret called `fractal-ci-cd-service-account-name` on the environment Key-Vault instantiated during Environment Initialization.           |
| CLIENT_SECRET       	     | The Client Secret of the Service Account used to authenticate to Fractal Cloud. This information is stored in a secret called `fractal-ci-cd-service-account-password` on the environment Key-Vault instantiated during Environment Initialization. 	 |

## Projects

| Project                                                                      	 | Description                                                                               	 |
|--------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| [ eks-full-sample ]( eks-full-sample/ )                                   	    | Sample project to emphasise the creation of an OKE with all the possible options available  |
| [ eks-minimum-sample ]( eks-minimum-sample/ )                              	   | Sample project to emphasise the creation of an OKE with the minimum requirements            |

#### [Go back to previous README](../README.md)