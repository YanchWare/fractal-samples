# Table of Contents
- [Introduction](#introduction)
- [Structure](#structure)
- [Build and run the project locally](#build-and-run-the-project-locally)
  * [Connect to YanchWare Maven Repository](#connect-to-yanchware-maven-repository)

## Introduction

This repository is split into two main categories of samples: 

* **LiveSystem-First**: Using the approach we named "Live-System first" you are able to describe the desired state of your infrastructure through code, pretty much as you might already be doing today.
However, the difference is that with very limited amount of code you will be able to spawn tailored infrastructure to the need of your application using standard and security-hardened components ready to support your application and services.
When you are then ready for enterprise grade, you can extract a reusable Blueprint from your running Live System, making it available for your colleagues across your organization, ensuring cross-pollination of proven best-practices, technologies and architectures.

* **Fractal-First**: Through the approach we named "Fractal first" you tackle Cloud-Native complexity at the enterprise level from the very beginning.
You will first define Blueprints, reusable sets of components and their relations, which put the basis for a compliant solutions in your IT Landscape. To each Blueprint you can then associate an Interface, a versioned set of operations through which the Blueprint can be modified ensuring the application compliance remains unaltered.
Any teams authorized will then be able to instantiate a specific Blueprint on any cloud vendor you have enabled, resulting in the creation of a Live System they own.
The latter will be able to be extended and tailored directly by the team owning it without requiring any access to any actual cloud environment; they will instead use the APIs exposed by the associated Interface.

You can find more information on this in our [documentation](https://fractal.cloud/docs)

## Structure

The repository is split into different directories based on the cloud provider. See the list below for easier navigation.

| Cloud Provider        	                                        | Description                                                                                                                |
|----------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| [ Environment Initialization](./environment-initialization/) 	 | A sample projects for initializing an Environment through SDK                                                              |
| [ LiveSystem First](./livesystem-first/) 	                     | Isolated sample for each component supported by [Fractal Cloud](https://fractal.cloud) using different cloud providers   	 |
| [ Fractal First](./fractal-first/) 	                           | A sample project for governable Fractals in large Enterprises   	                                                          |
