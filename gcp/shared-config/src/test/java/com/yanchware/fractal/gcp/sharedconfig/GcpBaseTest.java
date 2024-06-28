package com.yanchware.fractal.gcp.sharedconfig;

import org.junit.jupiter.api.BeforeEach;

import static com.yanchware.fractal.gcp.sharedconfig.GcpTestConstants.*;


public class GcpBaseTest {

  @BeforeEach
  public void setUp() {
    System.setProperty(RESOURCE_GROUP_ID_ENV.key(), RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(ENVIRONMENT_NAME_ENV.key(), ENVIRONMENT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_SHORT_NAME_ENV.key(), ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_OWNER_ID_ENV.key(), ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(ENVIRONMENT_TYPE_ENV.key(), ENVIRONMENT_TYPE_ENV.value());
    System.setProperty(GCP_REGION_ENV.key(), GCP_REGION_ENV.value());
  }
}
