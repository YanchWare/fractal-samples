package com.yanchware.fractal.samples.environment.intialization.operational.configuration;


import com.yanchware.fractal.samples.environment.intialization.operational.EnvVarRecord;
import com.yanchware.fractal.samples.environment.intialization.operational.Utils.TestUtils;

import java.util.UUID;

import static com.yanchware.fractal.samples.environment.intialization.operational.configuration.Constants.*;

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
          new EnvVarRecord(FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY, UUID.randomUUID().toString());

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_NAME_ENV =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_NAME_ENV_VAR_KEY, "Test Environment");

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY, "test-environment");

  public static final EnvVarRecord MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR =
          new EnvVarRecord(MANAGEMENT_ENVIRONMENT_TYPE_ENV_VAR_KEY, "Personal");


  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_AZURE_REGION_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_AZURE_REGION_ENV_VAR_KEY, "westeurope");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_AZURE_SUBSCRIPTION_ID_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_AZURE_SUBSCRIPTION_ID_ENV_VAR_KEY,UUID.randomUUID().toString());
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_SHORT_NAME_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_SHORT_NAME_ENV_VAR_KEY, "operational-ci-cd-profile");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_DISPLAY_NAME_ENV_VAR_KEY, "Operational CI/CD profile");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_DESCRIPTION_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_DESCRIPTION_ENV_VAR_KEY, "Operational CI/CD profile description");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA_ENV_VAR_KEY, "Operational CI/CD profile private key");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE_ENV_VAR_KEY, "Operational CI/CD profile private key passphrase");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_FRACTAL_RESOURCE_GROUP_ID_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_FRACTAL_RESOURCE_GROUP_ID_ENV_VAR_KEY, UUID.randomUUID().toString());
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_NAME_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_NAME_ENV_VAR_KEY, "Operational Environment");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_SECRET_SHORT_NAME_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_SECRET_SHORT_NAME_ENV_VAR_KEY, "operational-environment");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_SECRET_DISPLAY_NAME_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_SECRET_DISPLAY_NAME_ENV_VAR_KEY, "Operational Environment Secret");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_SECRET_DESCRIPTION_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_SECRET_DESCRIPTION_ENV_VAR_KEY, "Operational Environment Secret description");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_SECRET_VALUE_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_SECRET_VALUE_ENV_VAR_KEY, "Operational Environment Secret value");
  
  public static final EnvVarRecord OPERATIONAL_ENVIRONMENT_SHORT_NAME_ENV_VAR =
          new EnvVarRecord(OPERATIONAL_ENVIRONMENT_SHORT_NAME_ENV_VAR_KEY, "operational-environment-secret");
}



