package com.yanchware.fractal.pocs.migration.infrastructure.configuration;

import com.yanchware.fractal.sdk.domain.environment.CiCdProfile;

import java.util.UUID;

public interface InfrastructureConfiguration {
  UUID getEnvironmentOwnerId();
  String getEnvironmentShortName();
  String getFractalCloudResourceGroupId();
  String getEnvironmentName();
  UUID getTenantId();
  UUID getSubscriptionId();
  CiCdProfile getDefaultCiCdProfile();
}
