package com.yanchware.fractal.samples.environment.initialization.configuration;

import java.util.UUID;

public interface Configuration {
  UUID getTenantId();
  UUID getSubscriptionId();
  UUID getEnvironmentOwnerId();
  UUID getResourceGroupId();
}
