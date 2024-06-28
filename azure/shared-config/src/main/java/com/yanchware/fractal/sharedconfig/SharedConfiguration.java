package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;

import java.util.UUID;

public interface SharedConfiguration {
  String getLiveSystemName();
  AzureRegion getAzureRegion();
  UUID getTenantId();
  UUID getSubscriptionId();
  UUID getResourceGroupId();
  Environment getEnvironment();  
  AzureResourceGroup getAzureResourceGroup();
}
