package com.yanchware.fractal.samples.environment.intialization.management;

import org.junit.jupiter.api.BeforeEach;

import static com.yanchware.fractal.samples.environment.intialization.management.configuration.TestConstants.*;

public class BaseTest {
    @BeforeEach
    public void setUp() {
        System.setProperty(AWS_ACCOUNT_ID_ENV_VAR.key(), AWS_ACCOUNT_ID_ENV_VAR.value());
        System.setProperty(AWS_ORGANIZATION_ID_ENV_VAR.key(), AWS_ORGANIZATION_ID_ENV_VAR.value());
        System.setProperty(AWS_REGION_ENV_VAR.key(), AWS_REGION_ENV_VAR.value());
        System.setProperty(AZURE_REGION_ENV_VAR.key(), AZURE_REGION_ENV_VAR.value());
        System.setProperty(AZURE_SUBSCRIPTION_ID_ENV_VAR.key(), AZURE_SUBSCRIPTION_ID_ENV_VAR.value());
        System.setProperty(AZURE_TENANT_ID_ENV_VAR.key(), AZURE_TENANT_ID_ENV_VAR.value());
        System.setProperty(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR.key(), FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR.value());
        System.setProperty(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR.key(), FRACTAL_RESOURCE_GROUP_ID_ENV_VAR.value());
        System.setProperty(GCP_ORGANIZATION_ID_ENV_VAR.key(), GCP_ORGANIZATION_ID_ENV_VAR.value());
        System.setProperty(GCP_PROJECT_ID_ENV_VAR.key(), GCP_PROJECT_ID_ENV_VAR.value());
        System.setProperty(GCP_REGION_ENV_VAR.key(), GCP_REGION_ENV_VAR.value());
        System.setProperty(MANAGEMENT_ENVIRONMENT_NAME_ENV.key(), MANAGEMENT_ENVIRONMENT_NAME_ENV.value());
        System.setProperty(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR.key(), MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR.value());
        System.setProperty(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR.key(), MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR.value());
    }
}
