package com.yanchware.fractal.gcp.sharedconfig.tests;

import org.junit.jupiter.api.BeforeEach;


public class GcpBaseTest {

  @BeforeEach
  public void setUp() {
    String testClassName = this.getClass().getSimpleName();
    System.setProperty(GcpTestConstants.LIVE_SYSTEM_NAME_ENV_KEY, testClassName);
    System.setProperty(GcpTestConstants.RESOURCE_GROUP_ID_ENV.key(), GcpTestConstants.RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_NAME_ENV.key(), GcpTestConstants.ENVIRONMENT_NAME_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_SHORT_NAME_ENV.key(), GcpTestConstants.ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_OWNER_ID_ENV.key(), GcpTestConstants.ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(GcpTestConstants.ENVIRONMENT_TYPE_ENV.key(), GcpTestConstants.ENVIRONMENT_TYPE_ENV.value());
    System.setProperty(GcpTestConstants.GCP_REGION_ENV.key(), GcpTestConstants.GCP_REGION_ENV.value());
    System.setProperty(GcpTestConstants.GCP_ORGANIZATION_ID_ENV.key(), GcpTestConstants.GCP_ORGANIZATION_ID_ENV.value());
    System.setProperty(GcpTestConstants.GCP_PROJECT_ID_ENV.key(), GcpTestConstants.GCP_PROJECT_ID_ENV.value());
  }
}
