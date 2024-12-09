# Table of Contents
1. [Introduction](#introduction)
2. [Projects](#projects)
# Introduction

In here you can find example using our SDK for all supported components in the Azure environment.
Please, ensure that the Resource Group you are trying to use is enabled to deploy to the Environment identified.

Further information about the environment variables required are contained in the following table.

| Environment variable 	            | Description                                                                                                                                                                                                                              |
|-----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| FRACTAL_RESOURCE_GROUP_ID         | The id of the Resource Group you want to use to administrate access control. This information is available in the [Resource Group List](https://fractal.cloud/resource-groups) page of Fractal Cloud.                                    |
| FRACTAL_ENVIRONMENT_TYPE        	 | The type of the Environment being targeted, it can take the value of `personal` or `organizational`. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.             	 |
| FRACTAL_ENVIRONMENT_OWNER_ID      | The id of the owner of the Environment being targeted. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.                                                             |
| FRACTAL_ENVIRONMENT_SHORT_NAME    | The shortname of the Environment being targeted. This information is available in the [Environment Details](https://fractal.cloud/environments) page of Fractal Cloud.                                                                   |
| FRACTAL_ENVIRONMENT_NAME    	     | The display name of the Environment that will be created or updated. 	                                                                                                                                                                   |
| AZURE_TENANT_ID    	              | The ID of the Azure Tenant to use to deploy the sample services. 	                                                                                                                                                                       |
| AZURE_SUBSCRIPTION_ID    	        | The ID of the Azure Subscription in which to deploy the sample services. 	                                                                                                                                                               |
| CI_CD_SERVICE_ACCOUNT_NAME        | The name of the Service Account used to authenticate to Fractal Cloud. This information is stored in a secret called `fractal-ci-cd-service-account-name` in AWS Secrets Manager initialized during Environment Initialization.          |
| CI_CD_SERVICE_ACCOUNT_SECRET      | The secret of the Service Account used to authenticate to Fractal Cloud. This information is stored in a secret called `fractal-ci-cd-service-account-password` in AWS Secrets Manager initialized during Environment Initialization. 	  |

## Projects

| Project                                                                      	 | Description                                                                               	 |
|--------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| [ aks-full-sample ]( ./aks-full-sample/ )                                   	  | Sample project to emphasise the creation of an AKS with all the possible options available  |
| [ aks-minimum-sample ]( ./aks-minimum-sample/ )                              	 | Sample project to emphasise the creation of an AKS with the minimum requirements            |
| [ ambassador-sample ]( ./ambassador-sample/ ) 	                                | Sample project creating an Ambassador API Gateway in AKS                                    |
| [ appservice-dotnet-sample ]( ./appservice-dotnet-sample/ )                    | Sample project creating an Azure App Service with .NET web app                              |
| [ appservice-sample ]( ./appservice-sample/ ) 	                                | Sample project creating an Azure App Service                                                |
| [ azure-cdn-sample ]( ./azure-cdn-sample/ ) 	                                  | Sample project creating an Azure CDN                                                        |
| [ azure-multiple-resources-sample ]( ./azure-multiple-resources-sample/ ) 	    | Sample project creating multiple resources on Azure                                         |
| [ cosmos-sample ]( ./cosmos-sample/ ) 	                                        | Sample project creating an Azure Cosmos Entities                                            |
| [ custom-workload-sample ](./custom-workload-sample/)                          | Sample project creating a Custom Workload in AKS                                            |
| [ elastic-datastore-sample ]( ./elastic-datastore-sample/ ) 	                  | Sample project creating an Elastic Datastore in AKS                                         |
| [ elastic-logging-ambassador-sample ]( ./elastic-logging-ambassador-sample/ )  | Sample project creating an Elastic Logging with Ambassador in AKS            	              |
| [ elastic-logging-sample ]( ./elastic-logging-sample/ ) 	                      | Sample project creating an Elastic Logging in AKS                                           |
| [ postgresql-sample ]( ./postgresql-sample/ ) 	                                | Sample project creating a Postgresql in Azure                                               |
| [ prometheus-ambassador-sample ]( ./prometheus-ambassador-sample/ ) 	          | Sample project creating a Prometheus alongisde Ambassador in AKS                            |
| [ relay-sample ]( ./relay-sample/ ) 	                                          | Sample project creating an Azure Relay                                                      |
| [ service-bus-full-sample ]( ./service-bus-full-sample/ ) 	                    | Sample project creating an Azure Service Bus with full options                              |
| [ service-bus-minimum-sample ]( ./service-bus-minimum-sample/ ) 	              | Sample project creating an Azure Service Bus with minimum requirements                      |
| [ storage-account-sample ]( ./storage-account-sample/ ) 	                      | Sample project creating an Azure Storage Account                                            |

#### [Go back to previous README](../../README.md)