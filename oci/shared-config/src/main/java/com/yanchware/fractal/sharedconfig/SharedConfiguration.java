package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciRegion;

import java.util.UUID;

public interface SharedConfiguration {
  String getLiveSystemName();
  UUID getResourceGroupId();
  Compartment getCompartment();
  OciRegion getOciRegion();
  Environment getEnvironment();
}
