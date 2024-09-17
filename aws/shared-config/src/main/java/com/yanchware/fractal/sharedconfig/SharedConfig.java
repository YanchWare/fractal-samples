package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

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
  public UUID getResourceGroupId() {
    var resourceGroupId = getVariableValue("RESOURCE_GROUP_ID", true);
    return UUID.fromString(resourceGroupId);
  }

  @Override
  public String getLiveSystemName() {
    var liveSystemName = getVariableValue("LIVE_SYSTEM_NAME");
    return isBlank(liveSystemName)
        ? "fractal-cloud-samples"
        : liveSystemName;
  }

  @Override
  public AwsRegion getAwsRegion() {
    var region = getVariableValue("AWS_REGION", true);
    return AwsRegion.fromString(region);
  }

  @Override
  public String getOrganizationId() {
      return getVariableValue("ORGANIZATION_ID", true);
  }

  @Override
  public String getAccountId() {
    return getVariableValue("ACCOUNT_ID", true);
  }

  @Override
  public EnvironmentAggregate getEnvironment() throws InstantiatorException {
    var environmentType = getVariableValue("ENVIRONMENT_TYPE", true);
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
    
    return Automaton.getInstance().getEnvironmentBuilder()
            .withId(new EnvironmentIdValue(
                    EnvironmentType.fromString(environmentType),
                    UUID.fromString(environmentOwnerId),
                    environmentShortName))
        .withName(environmentName)
        .withResourceGroup(getResourceGroupId())
        .withAwsCloudAgent(
                getAwsRegion(),
                getOrganizationId(),
                getAccountId())
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

  // A convenience method to preserve existing behavior without changing all calls to this method
  public String getVariableValue(String key) {
    return getVariableValue(key, true); // By default, throw on missing
  }
}
