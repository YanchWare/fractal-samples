package com.yanchware.fractal;

import org.junit.jupiter.api.BeforeEach;

import static com.yanchware.fractal.configuration.ConfigurationConstants.*;

public class BaseTest {
  
  @BeforeEach
  public void setUp() {
    System.setProperty(LIVE_SYSTEM_NAME_ENV.key(), LIVE_SYSTEM_NAME_ENV.value());
    System.setProperty(RESOURCE_GROUP_ID_ENV.key(), RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(ENVIRONMENT_SHORT_NAME_ENV.key(), ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_OWNER_ID_ENV.key(), ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(ENVIRONMENT_TYPE_ENV.key(), ENVIRONMENT_TYPE_ENV.value());
  }
}
