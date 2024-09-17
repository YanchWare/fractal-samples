package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.UUID;

public interface SharedConfiguration {
  String getLiveSystemName();
  AzureRegion getAzureRegion();
  UUID getTenantId();
  UUID getSubscriptionId();
  UUID getResourceGroupId();
  EnvironmentAggregate getEnvironment() throws InstantiatorException;
  AzureResourceGroup getAzureResourceGroup();
}
