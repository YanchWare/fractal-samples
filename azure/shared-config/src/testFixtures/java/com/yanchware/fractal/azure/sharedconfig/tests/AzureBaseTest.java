package com.yanchware.fractal.azure.sharedconfig.tests;

import org.junit.jupiter.api.BeforeEach;


public class AzureBaseTest {

  @BeforeEach
  public void setUp() {
    String testClassName = this.getClass().getSimpleName();
    System.setProperty(AzureTestConstants.RESOURCE_GROUP_ID_ENV.key(), AzureTestConstants.RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(AzureTestConstants.ENVIRONMENT_NAME_ENV.key(), AzureTestConstants.ENVIRONMENT_NAME_ENV.value());
    System.setProperty(AzureTestConstants.ENVIRONMENT_SHORT_NAME_ENV.key(), AzureTestConstants.ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(AzureTestConstants.ENVIRONMENT_OWNER_ID_ENV.key(), AzureTestConstants.ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(AzureTestConstants.ENVIRONMENT_TYPE_ENV.key(), AzureTestConstants.ENVIRONMENT_TYPE_ENV.value());
    System.setProperty(AzureTestConstants.AZURE_RESOURCE_GROUP_NAME_ENV.key(), AzureTestConstants.AZURE_RESOURCE_GROUP_NAME_ENV.value());
    System.setProperty(AzureTestConstants.AZURE_TENANT_ID_ENV.key(), AzureTestConstants.AZURE_TENANT_ID_ENV.value());
    System.setProperty(AzureTestConstants.AZURE_SUBSCRIPTION_ID_ENV.key(), AzureTestConstants.AZURE_SUBSCRIPTION_ID_ENV.value());
  }
}
