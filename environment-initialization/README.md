# Environment Initialization Samples

## Table of Contents
- [Introduction](#introduction)
- [Key concepts](#key-concepts)
- [What is in this folder](#what-is-in-this-folder)
- [How to build and run](#how-to-build-and-run)
- [Environment variables](#environment-variables)
  - [Shared variables](#shared-variables)
  - [Module specific variables](#module-specific-variables)
- [Suggested order](#suggested-order)

## Introduction

Before you can deploy any application or service with [Fractal Cloud](https://fractal.cloud), you need an **Environment** that is ready to receive your workloads. Setting it up is called *Environment Initialization*.

The samples in this folder show how to perform that initialization in code, using the Fractal Java SDK, instead of clicking through a portal. Each module is a small, self contained program that builds an environment definition and sends it to Fractal Cloud through the SDK.

You can find more information in our [documentation](https://fractal.cloud/docs).

## Key concepts

A few terms show up across all modules:

- **Management Environment**: the control plane. It holds the connections to your cloud accounts (the Cloud Agents) and shared configuration such as default CI/CD profiles and secrets. You always create this first.
- **Operational Environment**: an environment that hosts actual workloads. It lives under a Management Environment and points to a specific cloud subscription.
- **Cloud Agent**: the link between Fractal Cloud and one of your cloud accounts (AWS, Azure, and so on).
- **CI/CD Profile**: a named set of credentials (for example an SSH key and its passphrase) used to pull and deploy code. A default profile is required before you can add extra ones.
- **Secret**: a named value stored securely in the environment, available to your workloads at runtime.

## What is in this folder

Each module is an independent Gradle subproject with a single `main` class that you can run.

| Module | What it shows | Main class |
|--------|---------------|------------|
| [`management-environment`](./management-environment/) | The minimum setup: a Management Environment with an AWS and an Azure Cloud Agent | `EnvironmentInitializationSample` |
| [`default-cicd-profile`](./default-cicd-profile/) | A Management Environment that also defines the **default** CI/CD profile | `DefaultCiCdProfileSample` |
| [`cicd-profiles`](./cicd-profiles/) | A Management Environment with the default CI/CD profile **plus** additional profiles | `CiCdProfilesSample` |
| [`secrets`](./secrets/) | A Management Environment that stores several secrets (SSH key, passphrase, and custom secrets) | `SecretsSample` |
| [`all-features`](./all-features/) | The full picture: Cloud Agents, default and additional CI/CD profiles, and secrets together | `AllFeaturesSample` |
| [`operational-environment`](./operational-environment/) | A Management Environment that also creates an Operational Environment with its own profile and secret | `OperationalEnvironmentSample` |

Inside every module the layout is the same:

- `src/main/java/.../<Name>Sample.java`: the program you run. It reads configuration and calls the SDK.
- `src/main/java/.../configuration/`: how configuration is read. `Configuration` is the interface, `EnvVarConfiguration` reads values from environment variables, and `Constants` lists the exact variable names.
- `src/test/java/...`: tests that validate the environment definition without contacting Fractal Cloud.

## How to build and run

The general setup (JDK, Gradle wrapper, authentication) is described once in the main [README](../README.md#build-and-run-the-project-locally).

Build the module you want with its `jar` task, then run it. Start it with the classpath form below, passing the fully qualified class of the module. This `java -cp` form works for every module:

```bash
# build the jar
./gradlew :environment-initialization:management-environment:jar

# run it (set the module variables before the command, see the tables below)
CI_CD_SERVICE_ACCOUNT_NAME="xxxx" \
CI_CD_SERVICE_ACCOUNT_SECRET="xxxx" \
# ... module variables ... \
java -cp environment-initialization/management-environment/build/libs/management-environment-2.0.0.jar \
  com.yanchware.fractal.samples.environment.intialization.management.EnvironmentInitializationSample
```

Use this table to run any module. Each jar is created under the module's `build/libs` folder.

| Module | jar file | Class to run |
|--------|----------|--------------|
| `management-environment` | `management-environment-2.0.0.jar` | `com.yanchware.fractal.samples.environment.intialization.management.EnvironmentInitializationSample` |
| `default-cicd-profile` | `default-cicd-profile-2.0.0.jar` | `com.yanchware.fractal.samples.environment.initialization.cicd.DefaultCiCdProfileSample` |
| `cicd-profiles` | `cicd-profiles-2.0.0.jar` | `com.yanchware.fractal.samples.environment.initialization.cicd.profiles.CiCdProfilesSample` |
| `secrets` | `secrets-2.0.0.jar` | `com.yanchware.fractal.samples.environment.initialization.cicd.profiles.SecretsSample` |
| `all-features` | `all-features-2.0.0.jar` | `com.yanchware.fractal.samples.environment.initialization.cicd.secrets.AllFeaturesSample` |
| `operational-environment` | `operational-environment-2.0.0.jar` | `com.yanchware.fractal.samples.environment.intialization.operational.OperationalEnvironmentSample` |

Running a module contacts Fractal Cloud and creates or updates the environment, so check your values first. To validate without contacting Fractal Cloud, run the module tests instead, for example `./gradlew :environment-initialization:management-environment:test`.

## Environment variables

Set these on top of the two authentication variables (`CI_CD_SERVICE_ACCOUNT_NAME` and `CI_CD_SERVICE_ACCOUNT_SECRET`) described in the main README. A missing variable stops the program with a message naming it.

Most values are identifiers you copy from the [Resource Groups](https://fractal.cloud/resource-groups) and [Environments](https://fractal.cloud/environments) pages of Fractal Cloud, or from your cloud consoles. Any `*_SHORT_NAME` value for an environment must contain only lowercase letters, numbers, and dashes.

Variables grouped as `*_SHORT_NAME`, `*_DISPLAY_NAME`, `*_DESCRIPTION` (and `*_VALUE` or SSH key fields) describe a single CI/CD profile or secret: the short name is the identifier, the others are labels or values.

### Shared variables

Read by `management-environment`, `default-cicd-profile`, `cicd-profiles`, `all-features`, and `operational-environment`.

| Variable | Description |
|----------|-------------|
| `FRACTAL_RESOURCE_GROUP_ID` | The Resource Group used for access control. |
| `FRACTAL_ENVIRONMENT_OWNER_ID` | The owner id of the environment. |
| `MANAGEMENT_ENVIRONMENT_TYPE` | `personal` or `organizational`. |
| `MANAGEMENT_ENVIRONMENT_SHORT_NAME` | Short name of the Management Environment. |
| `MANAGEMENT_ENVIRONMENT_NAME` | Display name of the Management Environment. |
| `AWS_ACCOUNT_ID` | The AWS account to connect. |
| `AWS_ORGANIZATION_ID` | The AWS organization to connect. |
| `AWS_REGION` | The AWS region of the Cloud Agent, for example `eu-west-1`. |
| `AZURE_SUBSCRIPTION_ID` | The Azure subscription to connect. |
| `AZURE_TENANT_ID` | The Azure tenant to connect. |
| `AZURE_REGION` | The Azure region of the Cloud Agent, for example `westeurope`. |

### Module specific variables

- **`management-environment`**: only the shared variables.
- **`default-cicd-profile`**: shared variables, plus the default profile `DEFAULT_CI_CD_PROFILE_{SHORT_NAME, DISPLAY_NAME, DESCRIPTION, SSH_PRIVATE_KEY_DATA, SSH_PRIVATE_KEY_PASSPHRASE}`.
- **`cicd-profiles`**: shared variables, the default profile (the five `DEFAULT_CI_CD_PROFILE_*` above), plus `SECOND_CI_CD_PROFILE_*` and `THIRD_CI_CD_PROFILE_*` (same five fields each). The default profile must be present before additional ones can be added.
- **`all-features`**: shared variables, the default and two additional profiles (`DEFAULT_*`, `SECOND_*`, `THIRD_CI_CD_PROFILE_*`), plus three secrets `FIRST_SECRET_*`, `SECOND_SECRET_*`, `THIRD_SECRET_*`, each with `{SHORT_NAME, DISPLAY_NAME, DESCRIPTION, VALUE}`.
- **`secrets`**: this module hardcodes the Management Environment name and regions, so it needs a smaller shared set (`FRACTAL_RESOURCE_GROUP_ID`, `FRACTAL_ENVIRONMENT_OWNER_ID`, `AWS_ACCOUNT_ID`, `AWS_ORGANIZATION_ID`, `AZURE_SUBSCRIPTION_ID`, `AZURE_TENANT_ID`). It then stores `DEPLOYER_SSH_KEY_SECRET_*` and `DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_*` (each with `{SHORT_NAME, DISPLAY_NAME, DESCRIPTION, VALUE}`), plus `SECOND_SECRET_*` and `THIRD_SECRET_*`.
- **`operational-environment`**: shared variables, plus the Operational Environment definition:
  - `OPERATIONAL_ENVIRONMENT_{SHORT_NAME, NAME, FRACTAL_RESOURCE_GROUP_ID, AZURE_REGION, AZURE_SUBSCRIPTION_ID}`
  - its default profile `OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_{SHORT_NAME, DISPLAY_NAME, DESCRIPTION, SSH_PRIVATE_KEY_DATA, SSH_PRIVATE_KEY_PASSPHRASE}`
  - its secret `OPERATIONAL_ENVIRONMENT_SECRET_{SHORT_NAME, DISPLAY_NAME, DESCRIPTION, VALUE}`

The exact variable names are also listed in each module's `configuration/Constants.java`.

## Suggested order

If you are setting up an environment for the first time, the modules build on each other:

1. `management-environment` to confirm your Cloud Agents and credentials work.
2. `default-cicd-profile` to add the default CI/CD profile.
3. `cicd-profiles` or `secrets` to add more profiles or store secrets.
4. `all-features` to apply everything in one go.
5. `operational-environment`, once the Management Environment is ready, to create an environment that hosts workloads.

#### [Go back to the main README](../README.md)
