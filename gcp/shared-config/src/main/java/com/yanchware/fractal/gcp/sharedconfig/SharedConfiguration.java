package com.yanchware.fractal.gcp.sharedconfig;

import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpRegion;

import java.util.UUID;

public interface SharedConfiguration {
  String getLiveSystemName();
  UUID getResourceGroupId();
  GcpRegion getRegion();
  String getOrganizationId();
  String getProjectId();
  Environment getEnvironment();
}
