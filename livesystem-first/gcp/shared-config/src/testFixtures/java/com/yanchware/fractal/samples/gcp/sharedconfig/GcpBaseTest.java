package com.yanchware.fractal.samples.gcp.sharedconfig;

import org.junit.jupiter.api.BeforeEach;


public class GcpBaseTest {

  @BeforeEach
  public void setUp() {
    System.setProperty(GcpTestConstants.RESOURCE_GROUP_ID_ENV.key(), GcpTestConstants.RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_NAME_ENV.key(), GcpTestConstants.ENVIRONMENT_NAME_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_SHORT_NAME_ENV.key(), GcpTestConstants.ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_OWNER_ID_ENV.key(), GcpTestConstants.ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_TYPE_ENV.key(), GcpTestConstants.ENVIRONMENT_TYPE_ENV.value());
    System.setProperty(GcpTestConstants.GCP_ORGANIZATION_ID_ENV.key(), GcpTestConstants.GCP_ORGANIZATION_ID_ENV.value());
    System.setProperty(GcpTestConstants.GCP_PROJECT_ID_ENV.key(), GcpTestConstants.GCP_PROJECT_ID_ENV.value());
  }
}
