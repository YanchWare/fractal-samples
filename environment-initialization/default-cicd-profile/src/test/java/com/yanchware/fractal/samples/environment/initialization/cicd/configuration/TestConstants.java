package com.yanchware.fractal.samples.environment.initialization.cicd.configuration;

import com.yanchware.fractal.samples.environment.initialization.cicd.EnvVarRecord;
import com.yanchware.fractal.samples.environment.initialization.cicd.Utils.TestUtils;

import java.util.UUID;

import static com.yanchware.fractal.samples.environment.initialization.cicd.configuration.Constants.*;


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

  public static final EnvVarRecord DEFAULT_CI_CD_PROFILE_SHORT_NAME_ENV_VAR =
          new EnvVarRecord(DEFAULT_CI_CD_PROFILE_SHORT_NAME_ENV_VAR_KEY, "default-profile");

  public static final EnvVarRecord DEFAULT_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR =
          new EnvVarRecord(DEFAULT_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR_KEY, "Default CI/CD Profile");

  public static final EnvVarRecord DEFAULT_CI_CD_PROFILE_DESCRIPTION_ENV_VAR =
          new EnvVarRecord(DEFAULT_CI_CD_PROFILE_DESCRIPTION_ENV_VAR_KEY, "This is the default CI/CD profile.");

  public static final EnvVarRecord DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR =
          new EnvVarRecord(DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR_KEY, "-----BEGIN OPENSSH PRIVATE KEY-----\nAAAAB3NzaC1yc2EAAAADAQABAAABAQ...\n-----END OPENSSH PRIVATE KEY-----");

  public static final EnvVarRecord DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR =
          new EnvVarRecord(DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR_KEY, "passphrase");

  public static final EnvVarRecord FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR =
          new EnvVarRecord(FRACTAL_ENVIRONMENT_OWNER_ID_ENV_VAR_KEY, UUID.randomUUID().toString());

  public static final EnvVarRecord FRACTAL_RESOURCE_GROUP_ID_ENV_VAR =
          new EnvVarRecord(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY, UUID.randomUUID().toString());

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_NAME_ENV =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_NAME_ENV_VAR_KEY, "Test Environment");

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY, "test-environment");

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY, "Personal");
}



