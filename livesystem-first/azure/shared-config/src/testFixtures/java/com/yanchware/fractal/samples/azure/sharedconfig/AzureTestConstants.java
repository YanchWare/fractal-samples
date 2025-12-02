package com.yanchware.fractal.samples.azure.sharedconfig;

public class AzureTestConstants {
  public static final EnvVarRecord RESOURCE_GROUP_ID_ENV =
    new EnvVarRecord("FRACTAL_RESOURCE_GROUP_ID", "Personal/14e43d41-dc32-4426-b13a-1acb6c16bc6f/short-name");

  public static final EnvVarRecord ENVIRONMENT_NAME_ENV =
      new EnvVarRecord("FRACTAL_ENVIRONMENT_NAME", "Fractal Cloud Samples");

  public static final EnvVarRecord ENVIRONMENT_SHORT_NAME_ENV =
      new EnvVarRecord("FRACTAL_ENVIRONMENT_SHORT_NAME", "fractal-cloud-samples");

  public static final EnvVarRecord ENVIRONMENT_OWNER_ID_ENV =
      new EnvVarRecord("FRACTAL_ENVIRONMENT_OWNER_ID", "0190269e-b79f-71a9-b6e3-1a2141e99792");

  public static final EnvVarRecord ENVIRONMENT_TYPE_ENV =
      new EnvVarRecord("FRACTAL_ENVIRONMENT_TYPE", "Personal");
  
  public static final EnvVarRecord AZURE_RESOURCE_GROUP_NAME_ENV = 
      new EnvVarRecord("AZURE_RESOURCE_GROUP_NAME", "rg-sample");
  
  public static final EnvVarRecord AZURE_TENANT_ID_ENV = new EnvVarRecord("AZURE_TENANT_ID", "019026ae-0e73-7c58-b76f-8d7958176647");
  public static final EnvVarRecord AZURE_SUBSCRIPTION_ID_ENV = new EnvVarRecord("AZURE_SUBSCRIPTION_ID", "019026ae-0e73-77db-8080-5148dec0431f");
  
  
}
