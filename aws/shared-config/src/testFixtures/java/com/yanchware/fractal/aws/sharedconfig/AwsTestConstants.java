package com.yanchware.fractal.aws.sharedconfig;

public class AwsTestConstants {
  public static final String LIVE_SYSTEM_NAME_ENV_KEY = "LIVE_SYSTEM_NAME";
      
  public static final EnvVarRecord RESOURCE_GROUP_ID_ENV =
      new EnvVarRecord("RESOURCE_GROUP_ID", "0190269e-712c-7abf-9eee-180bb4c22d0b");

  public static final EnvVarRecord ENVIRONMENT_NAME_ENV =
      new EnvVarRecord("ENVIRONMENT_NAME", "Fractal Cloud Samples");

  public static final EnvVarRecord ENVIRONMENT_SHORT_NAME_ENV =
      new EnvVarRecord("ENVIRONMENT_SHORT_NAME", "fractal-cloud-samples");

  public static final EnvVarRecord ENVIRONMENT_OWNER_ID_ENV =
      new EnvVarRecord("ENVIRONMENT_OWNER_ID", "0190269e-b79f-71a9-b6e3-1a2141e99792");

  public static final EnvVarRecord ENVIRONMENT_TYPE_ENV =
      new EnvVarRecord("ENVIRONMENT_TYPE", "Personal");

  public static final EnvVarRecord ORGANIZATION_ID_ENV =
          new EnvVarRecord("ORGANIZATION_ID", "11434e97-d73c-472e-9e90-e4bef0e4aa01");

  public static final EnvVarRecord ACCOUNT_ID_ENV =
          new EnvVarRecord("ACCOUNT_ID", "e2088f53-8ac7-4f37-818f-e895b3f4527c");

  public static final EnvVarRecord AWS_REGION_ENV = new EnvVarRecord("AWS_REGION", "eu-north-1");

  
}
