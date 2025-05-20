package com.yanchware.fractal.samples.environment.initialization.secrets.configuration;

import com.yanchware.fractal.sdk.domain.environment.Secret;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;

import java.util.Collection;
import java.util.UUID;

public interface Configuration {
  UUID getAzureTenantId();
  UUID getAzureSubscriptionId();
  String getAwsOrganizationId();
  String getAwsAccountId();
  UUID getEnvironmentOwnerId();
  UUID getResourceGroupId();

  Collection<Secret> getAdditionalSecrets();

  Secret getSshPrivateKeySecret();

  Secret getSshPrivateKeyPassphraseSecret();
  String getGcpOrganizationId();
  String getGcpProjectId();
  GcpRegion getGcpRegion();
}
