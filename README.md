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

| Cloud Provider        	                                        | Description                                                        |
|----------------------------------------------------------------|--------------------------------------------------------------------|
| [ AWS ](./aws/) 	                                              | Collection of sample projects using our SDK for AWS 	              |
| [ Azure ](./azure/) 	                                          | Collection of sample projects using our SDK for Azure 	            |
| [ GCP   ](./gcp/) 	                                            | Collection of sample projects using our SDK for GCP   	            |
| [ OCI   ](./oci/) 	                                            | Collection of sample projects using our SDK for OCI   	            |
| [ Environment Initialization](./environment-initialization/) 	 | A sample projects for initializing an Environment through SDK      |
| [ Fractal First](./fractal-first/) 	                           | A sample projects for governable Fractals in large Enterprises   	 |
