package com.yanchware.fractal.samples.aws.sharedconfig;

import org.junit.jupiter.api.BeforeEach;

import static com.yanchware.fractal.samples.aws.sharedconfig.AwsTestConstants.*;


public class AwsBaseTest {

  @BeforeEach
  public void setUp() {
    System.setProperty(RESOURCE_GROUP_ID_ENV.key(), RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(ENVIRONMENT_NAME_ENV.key(), ENVIRONMENT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_SHORT_NAME_ENV.key(), ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_OWNER_ID_ENV.key(), ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(ENVIRONMENT_TYPE_ENV.key(), ENVIRONMENT_TYPE_ENV.value());
    System.setProperty(ORGANIZATION_ID_ENV.key(), ORGANIZATION_ID_ENV.value());
    System.setProperty(ACCOUNT_ID_ENV.key(), ACCOUNT_ID_ENV.value());
  }
}
