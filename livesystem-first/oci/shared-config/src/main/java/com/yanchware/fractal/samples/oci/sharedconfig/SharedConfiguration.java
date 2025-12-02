package com.yanchware.fractal.samples.oci.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;
import com.yanchware.fractal.sdk.domain.values.ResourceGroupId;

import java.util.UUID;

public interface SharedConfiguration {
  ResourceGroupId getFractalResourceGroupId();
  Compartment getOciCompartment();
  String getOciTenancyId();
  EnvironmentAggregate getFractalEnvironment(OciRegion region) throws InstantiatorException;
}
