package com.yanchware.fractal.gcp.sharedconfig;

public class GcpTestConstants {
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
}
