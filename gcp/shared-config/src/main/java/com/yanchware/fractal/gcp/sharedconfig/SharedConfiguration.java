package com.yanchware.fractal.gcp.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;

public interface SharedConfiguration {
  EnvironmentAggregate getFractalCloudEnvironment(GcpRegion region) throws InstantiatorException;
  String getFractalCloudResourceGroupId();
  String getGcpOrganizationId();
  String getGcpProjectId();
}
