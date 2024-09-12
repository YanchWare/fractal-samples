package com.yanchware.fractal.pocs.migration.infrastructure.configuration;

import java.util.UUID;

public interface InfrastructureConfiguration {
  UUID getEnvironmentOwnerId();
  String getEnvironmentShortName();
  String getFractalCloudResourceGroupId();
  String getEnvironmentName();
  UUID getTenantId();
  UUID getSubscriptionId();
}
