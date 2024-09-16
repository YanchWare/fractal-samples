package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;

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
  public AzureRegion getAzureRegion() {
    var azureRegion = getVariableValue("AZURE_REGION", true);
    return AzureRegion.fromString(azureRegion);
  }

  @Override
  public UUID getTenantId() {
    var tenantId = getVariableValue("AZURE_TENANT_ID", true);
    return UUID.fromString(tenantId);
  }

  @Override
  public UUID getSubscriptionId() {
    var subscriptionId = getVariableValue("AZURE_SUBSCRIPTION_ID", true);
    return UUID.fromString(subscriptionId);
  }

  @Override
  public UUID getResourceGroupId() {
    var resourceGroupId = getVariableValue("RESOURCE_GROUP_ID", true);
    return UUID.fromString(resourceGroupId);
  }

  @Override
  public EnvironmentAggregate getEnvironment() throws InstantiatorException {
    var environmentType = getVariableValue("ENVIRONMENT_TYPE", true);

    var environmentOwnerId = getVariableValue("ENVIRONMENT_OWNER_ID", true);

    var environmentShortName = getVariableValue("ENVIRONMENT_SHORT_NAME", true);

    var environmentName = getVariableValue("ENVIRONMENT_NAME");
    if (isBlank(environmentShortName)) {
      environmentName = environmentShortName;
    }

    var automaton = Automaton.getInstance();
    return automaton.getEnvironmentBuilder()
        .withId(new EnvironmentIdValue(
                    EnvironmentType.fromString(environmentType),
                    UUID.fromString(environmentOwnerId),
                    environmentShortName))
        .withName(environmentName)
        .withAzureCloudAgent(
                getAzureRegion(),
                getTenantId(),
                getSubscriptionId())
        .withResourceGroup(getResourceGroupId())
        .build();
  }

  @Override
  public AzureResourceGroup getAzureResourceGroup() {
    var resourceGroupName = getVariableValue("AZURE_RESOURCE_GROUP_NAME", false);
    var resourceGroupRegion = getVariableValue("AZURE_RESOURCE_GROUP_REGION", false);
    
    return AzureResourceGroup.builder()
        .withName(isBlank(resourceGroupName) ? "rg-samples" : resourceGroupName)
        .withRegion(isBlank(resourceGroupRegion) ? AzureRegion.WEST_EUROPE : AzureRegion.fromString(resourceGroupRegion))
        .withTag("Purpose", "Samples")
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
