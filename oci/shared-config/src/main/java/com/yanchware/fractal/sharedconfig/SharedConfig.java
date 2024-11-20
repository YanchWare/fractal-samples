package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.environment.ManagementEnvironment;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;

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
  public OciRegion getOciRegion() {
    var region = getVariableValue("OCI_REGION", true);
    return OciRegion.fromString(region);
  }

  @Override
  public String getTenancyId() {
    return getVariableValue("TENANCY_ID", true);
  }

  @Override
  public Compartment getCompartment() {
    var resourceGroupName = getVariableValue("OCI_COMPARTMENT_NAME", false);

    return Compartment.builder()
            .withName(isBlank(resourceGroupName) ? "rg-samples" : resourceGroupName)
            .withTag("Purpose", "Samples")
            .build();
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
    
    return Automaton.getInstance().getEnvironmentBuilder().withManagementEnvironment(ManagementEnvironment.builder()
            .withId(new EnvironmentIdValue(
                    EnvironmentType.fromString(environmentType),
                    UUID.fromString(environmentOwnerId),
                    environmentShortName))
        .withName(environmentName)
        .withResourceGroup(getResourceGroupId())
        .withOciCloudAgent(
                getOciRegion(),
                getTenancyId(),
                getCompartment().getName())
        .build())
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
