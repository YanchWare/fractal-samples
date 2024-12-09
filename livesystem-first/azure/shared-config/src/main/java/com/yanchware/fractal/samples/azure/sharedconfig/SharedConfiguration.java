package com.yanchware.fractal.samples.azure.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.UUID;

public interface SharedConfiguration {
  UUID getAzureTenantId();
  UUID getAzureSubscriptionId();
  UUID getFractalResourceGroupId();
  EnvironmentAggregate getFractalEnvironment(AzureRegion region) throws InstantiatorException;
}
