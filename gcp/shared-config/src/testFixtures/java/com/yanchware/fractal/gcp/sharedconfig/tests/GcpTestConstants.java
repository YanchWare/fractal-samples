package com.yanchware.fractal.gcp.sharedconfig.tests;

public class GcpTestConstants {
  public static final String LIVE_SYSTEM_NAME_ENV_KEY = "LIVE_SYSTEM_NAME";
      
  public static final EnvVarRecord RESOURCE_GROUP_ID_ENV =
      new EnvVarRecord("RESOURCE_GROUP_ID", "01902519-89af-7d0e-b1ce-55c74d41ee5b");

  public static final EnvVarRecord ENVIRONMENT_NAME_ENV =
      new EnvVarRecord("ENVIRONMENT_NAME", "Fractal Cloud Samples");

  public static final EnvVarRecord ENVIRONMENT_SHORT_NAME_ENV =
      new EnvVarRecord("ENVIRONMENT_SHORT_NAME", "fractal-cloud-samples");

  public static final EnvVarRecord ENVIRONMENT_OWNER_ID_ENV =
      new EnvVarRecord("ENVIRONMENT_OWNER_ID", "14e43d41-dc32-4426-b13a-1acb6c16bc6f");

  public static final EnvVarRecord ENVIRONMENT_TYPE_ENV =
      new EnvVarRecord("ENVIRONMENT_TYPE", "Personal");

  public static final EnvVarRecord GCP_REGION_ENV =
      new EnvVarRecord("GCP_REGION", "EU_WEST1");

  public static final EnvVarRecord GCP_ORGANIZATION_ID_ENV =
          new EnvVarRecord("ORGANIZATION_ID", "11434e97-d73c-472e-9e90-e4bef0e4aa01");

  public static final EnvVarRecord GCP_PROJECT_ID_ENV =
          new EnvVarRecord("PROJECT_ID", "e2088f53-8ac7-4f37-818f-e895b3f4527c");

}
