package com.yanchware.fractal.gcp.sharedconfig;

import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpRegion;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class SharedConfig implements SharedConfiguration {
  private final boolean readFromProperties;

  public SharedConfig() {
    this(false);
  }

  public SharedConfig(boolean readFromProperties) {
    this.readFromProperties = readFromProperties;
  }

  public static SharedConfig getInstance() {
    return new SharedConfig();
  }

  public static SharedConfig getInstance(boolean readFromProperties) {
    return new SharedConfig(readFromProperties);
  }
  
  @Override
  public String getLiveSystemName() {
    var liveSystemName = getVariableValue("LIVE_SYSTEM_NAME");
    return isBlank(liveSystemName)
        ? "fractal-cloud-samples"
        : liveSystemName;
  }

  @Override
  public UUID getResourceGroupId() {
    var resourceGroupId = getVariableValue("RESOURCE_GROUP_ID");
    if (isBlank(resourceGroupId)) {
      throw new IllegalArgumentException("The environment variable RESOURCE_GROUP_ID is required and it has not been defined");
    }

    return UUID.fromString(resourceGroupId);
  }

  @Override
  public GcpRegion getRegion() {
    var region = getVariableValue("GCP_REGION");
    if (isBlank(region)) {
      throw new IllegalArgumentException("The environment variable GCP_REGION is required and it has not been defined");
    }

    return GcpRegion.valueOf(region);
  }

  @Override
  public String getOrganizationId() {
    var organizationId = System.getenv("ORGANIZATION_ID");
    if(isBlank(organizationId)) {
      throw new IllegalArgumentException("The environment variable ORGANIZATION_ID is required and it has not been defined");
    }

    return organizationId;
  }

  @Override
  public String getProjectId() {
    var projectId = System.getenv("PROJECT_ID");
    if(isBlank(projectId)) {
      throw new IllegalArgumentException("The environment variable PROJECT_ID is required and it has not been defined");
    }

    return projectId;
  }

  @Override
  public Environment getEnvironment() {
    var environmentType = getVariableValue("ENVIRONMENT_TYPE");
    if (isBlank(environmentType)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_TYPE is required and it has not been defined");
    }

    var environmentOwnerId = getVariableValue("ENVIRONMENT_OWNER_ID");
    if (isBlank(environmentOwnerId)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_OWNER_ID is required and it has not been defined");
    }

    var environmentShortName = getVariableValue("ENVIRONMENT_SHORT_NAME");
    if (isBlank(environmentShortName)) {
      throw new IllegalArgumentException("The environment variable ENVIRONMENT_SHORT_NAME is required and it has not been defined");
    }

    var environmentName = getVariableValue("ENVIRONMENT_NAME");
    if (isBlank(environmentShortName)) {
      environmentName = environmentShortName;
    }
    
    return Environment.builder()
        .withEnvironmentType(EnvironmentType.fromString(environmentType))
        .withOwnerId(UUID.fromString(environmentOwnerId))
        .withShortName(environmentShortName)
        .withName(environmentName)
        .withResourceGroup(getResourceGroupId())
        .build();
  }

  /**
   * Retrieves the value of the specified environment or system property.
   *
   * @param key The key of the property.
   * @param throwOnMissing Determines whether to throw an exception if the property is missing.
   * @return The value of the property, or {@code null} if the property is missing and throwOnMissing is false.
   * @throws IllegalArgumentException if the property is missing and throwOnMissing is true.
   */
  public String getVariableValue(String key, boolean throwOnMissing) {
    var value = readFromProperties ? System.getProperty(key) : System.getenv(key);

    if (isBlank(value) && throwOnMissing) {
      throw new IllegalArgumentException(
          String.format("The environment variable or system property ['%s'] is required but it has not been defined", key));
    }

    return value;
  }
  
  public String getVariableValue(String key) {
    return getVariableValue(key, true);
  }
}
