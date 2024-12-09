# Table of Contents
1. [Introduction](#introduction)
2. [Projects](#projects)
# Introduction

In here you can find example using our SDK for all supported components in the AWS environment.
Please, ensure that the Resource Group you are trying to use is enabled to deploy to the Environment identified.

Further information about the environment variables required are contained in the following table.

| Environment variable 	            | Description                                                                                                                                                                                                                              |
|-----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| FRACTAL_RESOURCE_GROUP_ID         | The id of the Resource Group you want to use to administrate access control. This information is available in the [Resource Group List](https://fractal.cloud/resource-groups) page of Fractal Cloud.                                    |
| FRACTAL_ENVIRONMENT_TYPE        	 | The type of the Environment being targeted, it can take the value of `personal` or `organizational`. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.             	 |
| FRACTAL_ENVIRONMENT_OWNER_ID      | The id of the owner of the Environment being targeted. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.                                                             |
| FRACTAL_ENVIRONMENT_SHORT_NAME    | The shortname of the Environment being targeted. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.                                                                   |
| FRACTAL_ENVIRONMENT_NAME    	     | The display name of the Environment that will be created or updated. 	                                                                                                                                                                   |
| AWS_ORGANIZATION_ID    	          | The ID of the AWS Organization to use to deploy the sample services. 	                                                                                                                                                                   |
| AWS_ACCOUNT_ID    	               | The ID of the AWS Account in which to deploy the sample services. 	                                                                                                                                                                      |
| CI_CD_SERVICE_ACCOUNT_NAME        | The name of the Service Account used to authenticate to Fractal Cloud. This information is stored in a secret called `fractal-ci-cd-service-account-name` in AWS Secrets Manager initialized during Environment Initialization.          |
| CI_CD_SERVICE_ACCOUNT_SECRET      | The secret of the Service Account used to authenticate to Fractal Cloud. This information is stored in a secret called `fractal-ci-cd-service-account-password` in AWS Secrets Manager initialized during Environment Initialization. 	  |

## Projects

| Project                                                                      	              | Description                                                                               	                     |
|---------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| [ ambassador-sample ]( ambassador-sample/ )                                   	             | Sample project creating an EKS and running ambassador API Gateway on it                                         |
| [ custom-workload-sample ]( ambassador-sample/ )                                   	        | Sample project creating an EKS and running a custom workload on it                                              |
| [ eks-full-sample ]( eks-full-sample/ )                                   	                 | Sample project to emphasise the creation of an EKS with all the possible options available                      |
| [ eks-minimum-sample ]( eks-minimum-sample/ )                              	                | Sample project to emphasise the creation of an EKS with the minimum requirements                                |
| [ elastic-datastore-sample ]( eks-minimum-sample/ )                              	          | Sample project creating an EKS and running elastic as datastore                                                 |
| [ elastic-logging-ambassador-sample ]( eks-minimum-sample/ )                              	 | Sample project creating an EKS and running elastic logging exposing its services through Ambassador API Gateway |
| [ elastic-logging-sample ]( eks-minimum-sample/ )                              	            | Sample project creating an EKS and running elastic logging on it in isolation                                   |
| [ prometheus-ambassador-sample ]( eks-minimum-sample/ )                              	      | Sample project creating an EKS and running prometheus exposing its services through Ambassador API Gateway      |

#### [Go back to previous README](../../README.md)