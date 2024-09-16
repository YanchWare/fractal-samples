package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;

import java.util.UUID;

public interface SharedConfiguration {
  String getLiveSystemName();
  UUID getResourceGroupId();
  Compartment getCompartment();
  OciRegion getOciRegion();
  EnvironmentAggregate getEnvironment() throws InstantiatorException;
}
