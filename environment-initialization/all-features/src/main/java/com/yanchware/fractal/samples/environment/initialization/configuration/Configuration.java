package com.yanchware.fractal.samples.environment.initialization.configuration;

import com.yanchware.fractal.sdk.domain.environment.CiCdProfile;
import com.yanchware.fractal.sdk.domain.environment.Secret;

import java.util.Collection;
import java.util.UUID;

public interface Configuration {
  UUID getAzureTenantId();
  UUID getAzureSubscriptionId();
  String getAwsOrganizationId();
  String getAwsAccountId();
  UUID getEnvironmentOwnerId();
  UUID getResourceGroupId();
  CiCdProfile getDefaultCiCdProfile();
  Collection<CiCdProfile> getAdditionalCiCdProfile();
  Collection<Secret> getEnvironmentSecrets();
}
