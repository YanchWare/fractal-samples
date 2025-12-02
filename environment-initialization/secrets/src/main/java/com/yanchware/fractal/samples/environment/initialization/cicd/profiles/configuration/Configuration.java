package com.yanchware.fractal.samples.environment.initialization.cicd.profiles.configuration;

import com.yanchware.fractal.sdk.domain.environment.Secret;
import com.yanchware.fractal.sdk.domain.values.ResourceGroupId;

import java.util.Collection;
import java.util.UUID;

public interface Configuration {
  UUID getAzureTenantId();
  UUID getAzureSubscriptionId();
  String getAwsOrganizationId();
  String getAwsAccountId();
  UUID getEnvironmentOwnerId();
  ResourceGroupId getResourceGroupId();
  Collection<Secret> getAdditionalSecrets();
  Secret getSshPrivateKeySecret();
  Secret getSshPrivateKeyPassphraseSecret();
}
