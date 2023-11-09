package com.yanchware.fractal.configuration;

public class ConfigurationConstancts {

  public static final EnvVarRecord LIVE_SYSTEM_NAME_ENV = 
      new EnvVarRecord("LIVE_SYSTEM_NAME", "service-bus-minimum-sample");
  
  public static final EnvVarRecord RESOURCE_GROUP_ID_ENV = 
      new EnvVarRecord("RESOURCE_GROUP_ID", "fractal-cloud-sample");

  public static final EnvVarRecord ENVIRONMENT_ID_ENV =
      new EnvVarRecord("ENVIRONMENT_ID", "06444dd3-3154-4057-a710-e4fdf299cb87");

    public static final EnvVarRecord ENVIRONMENT_OWNER_ID_ENV =
      new EnvVarRecord("ENVIRONMENT_OWNER_ID", "14e43d41-dc32-4426-b13a-1acb6c16bc6f");

  public static final EnvVarRecord ENVIRONMENT_TYPE_ENV =
      new EnvVarRecord("ENVIRONMENT_TYPE", "Personal");
}