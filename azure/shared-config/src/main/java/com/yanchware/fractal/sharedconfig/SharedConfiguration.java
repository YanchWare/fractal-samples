package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;

public interface SharedConfiguration {
  String getLiveSystemName();
  String getResourceGroupId();
  Environment getEnvironment();  
  AzureResourceGroup getAzureResourceGroup();
}
