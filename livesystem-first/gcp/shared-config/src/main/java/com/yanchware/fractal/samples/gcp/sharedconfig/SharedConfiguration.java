package com.yanchware.fractal.samples.gcp.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;
import com.yanchware.fractal.sdk.domain.values.ResourceGroupId;

public interface SharedConfiguration {
  EnvironmentAggregate getFractalCloudEnvironment(GcpRegion region) throws InstantiatorException;
  ResourceGroupId getFractalCloudResourceGroupId();
  String getGcpOrganizationId();
  String getGcpProjectId();
}
