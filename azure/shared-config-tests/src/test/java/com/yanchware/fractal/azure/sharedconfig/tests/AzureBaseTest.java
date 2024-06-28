package com.yanchware.fractal.azure.sharedconfig.tests;

import org.junit.jupiter.api.BeforeEach;

import static com.yanchware.fractal.azure.sharedconfig.tests.AzureTestConstants.*;


public class AzureBaseTest {

  @BeforeEach
  public void setUp() {
    String testClassName = this.getClass().getSimpleName();
    System.setProperty(LIVE_SYSTEM_NAME_ENV_KEY, testClassName);
    System.setProperty(RESOURCE_GROUP_ID_ENV.key(), RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(ENVIRONMENT_NAME_ENV.key(), ENVIRONMENT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_SHORT_NAME_ENV.key(), ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_OWNER_ID_ENV.key(), ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(ENVIRONMENT_TYPE_ENV.key(), ENVIRONMENT_TYPE_ENV.value());
    System.setProperty(AZURE_RESOURCE_GROUP_NAME_ENV.key(), AZURE_RESOURCE_GROUP_NAME_ENV.value());
    System.setProperty(AZURE_RESOURCE_GROUP_REGION_ENV.key(), AZURE_RESOURCE_GROUP_REGION_ENV.value());
    System.setProperty(AZURE_REGION_ENV.key(), AZURE_REGION_ENV.value());
    System.setProperty(AZURE_TENANT_ID_ENV.key(), AZURE_TENANT_ID_ENV.value());
    System.setProperty(AZURE_SUBSCRIPTION_ID_ENV.key(), AZURE_SUBSCRIPTION_ID_ENV.value());
  }
}
