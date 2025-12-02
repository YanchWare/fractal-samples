package com.yanchware.fractal.pocs.migration.infrastructure.configuration;

import com.yanchware.fractal.sdk.domain.environment.CiCdProfile;
import com.yanchware.fractal.sdk.domain.values.ResourceGroupId;

import java.util.UUID;

public interface InfrastructureConfiguration {
  UUID getEnvironmentOwnerId();
  String getEnvironmentShortName();
  ResourceGroupId getFractalCloudResourceGroupId();
  String getEnvironmentName();
  UUID getTenantId();
  UUID getSubscriptionId();
  CiCdProfile getDefaultCiCdProfile();
}
