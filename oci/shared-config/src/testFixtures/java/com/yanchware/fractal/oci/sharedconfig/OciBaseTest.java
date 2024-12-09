package com.yanchware.fractal.oci.sharedconfig;

import org.junit.jupiter.api.BeforeEach;

import static com.yanchware.fractal.oci.sharedconfig.OciTestConstants.*;


public class OciBaseTest {

  @BeforeEach
  public void setUp() {
    String testClassName = this.getClass().getSimpleName();
    System.setProperty(RESOURCE_GROUP_ID_ENV.key(), RESOURCE_GROUP_ID_ENV.value());
    System.setProperty(ENVIRONMENT_NAME_ENV.key(), ENVIRONMENT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_SHORT_NAME_ENV.key(), ENVIRONMENT_SHORT_NAME_ENV.value());
    System.setProperty(ENVIRONMENT_OWNER_ID_ENV.key(), ENVIRONMENT_OWNER_ID_ENV.value());
    System.setProperty(ENVIRONMENT_TYPE_ENV.key(), ENVIRONMENT_TYPE_ENV.value());
    System.setProperty(OCI_COMPARTMENT_NAME_ENV.key(), OCI_COMPARTMENT_NAME_ENV.value());
    System.setProperty(OCI_TENANCY_ID_ENV.key(), OCI_TENANCY_ID_ENV.value());
    System.setProperty(OCI_COMPARTMENT_ID_ENV.key(), OCI_COMPARTMENT_ID_ENV.value());
  }
}
