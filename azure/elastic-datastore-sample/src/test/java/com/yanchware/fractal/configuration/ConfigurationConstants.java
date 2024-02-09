package com.yanchware.fractal.configuration;

public class ConfigurationConstants {

  public static final EnvVarRecord LIVE_SYSTEM_NAME_ENV = 
      new EnvVarRecord("LIVE_SYSTEM_NAME", "elastic-datastore-sample");
  
  public static final EnvVarRecord RESOURCE_GROUP_ID_ENV = 
      new EnvVarRecord("RESOURCE_GROUP_ID", "fractal-cloud-sample");

  public static final EnvVarRecord ENVIRONMENT_SHORT_NAME_ENV =
      new EnvVarRecord("ENVIRONMENT_SHORT_NAME", "fractal-cloud-samples");

    public static final EnvVarRecord ENVIRONMENT_OWNER_ID_ENV =
      new EnvVarRecord("ENVIRONMENT_OWNER_ID", "14e43d41-dc32-4426-b13a-1acb6c16bc6f");

  public static final EnvVarRecord ENVIRONMENT_TYPE_ENV =
      new EnvVarRecord("ENVIRONMENT_TYPE", "Personal");
}