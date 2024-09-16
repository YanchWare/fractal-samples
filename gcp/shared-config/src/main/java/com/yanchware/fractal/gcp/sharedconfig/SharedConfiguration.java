package com.yanchware.fractal.gcp.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;

import java.util.UUID;

public interface SharedConfiguration {
  String getLiveSystemName();
  UUID getResourceGroupId();
  GcpRegion getRegion();
  String getOrganizationId();
  String getProjectId();
  EnvironmentAggregate getEnvironment() throws InstantiatorException;
}
