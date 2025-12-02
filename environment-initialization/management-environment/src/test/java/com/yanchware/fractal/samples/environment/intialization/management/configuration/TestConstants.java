package com.yanchware.fractal.samples.environment.intialization.management.configuration;


import com.yanchware.fractal.samples.environment.intialization.management.EnvVarRecord;
import com.yanchware.fractal.samples.environment.intialization.management.Utils.TestUtils;

import java.util.UUID;

import static com.yanchware.fractal.samples.environment.intialization.management.configuration.Constants.*;

public class TestConstants {

  public static final EnvVarRecord AWS_ACCOUNT_ID_ENV_VAR =
          new EnvVarRecord(AWS_ACCOUNT_ID_ENV_VAR_KEY, TestUtils.generate12DigitNumber());

  public static final EnvVarRecord AWS_ORGANIZATION_ID_ENV_VAR =
          new EnvVarRecord(AWS_ORGANIZATION_ID_ENV_VAR_KEY, TestUtils.generate12DigitNumber());

  public static final EnvVarRecord AWS_REGION_ENV_VAR =
          new EnvVarRecord(AWS_REGION_ENV_VAR_KEY, "af-south-1");

  public static final EnvVarRecord AZURE_REGION_ENV_VAR =
          new EnvVarRecord(AZURE_REGION_ENV_VAR_KEY, "westeurope");

  public static final EnvVarRecord AZURE_SUBSCRIPTION_ID_ENV_VAR =
          new EnvVarRecord(AZURE_SUBSCRIPTION_ID_ENV_VAR_KEY, UUID.randomUUID().toString());

  public static final EnvVarRecord AZURE_TENANT_ID_ENV_VAR =
          new EnvVarRecord(AZURE_TENANT_ID_ENV_VAR_KEY, UUID.randomUUID().toString());

  public static final EnvVarRecord FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR = 
      new EnvVarRecord(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY, UUID.randomUUID().toString());

  public static final EnvVarRecord FRACTAL_RESOURCE_GROUP_ID_ENV_VAR =
          new EnvVarRecord(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY, String.format("Personal/%s/test-env", UUID.randomUUID()));

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_NAME_ENV =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_NAME_ENV_VAR_KEY, "Test Environment");

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY, "test-environment");

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY, "Personal");
}



