package com.yanchware.fractal.oci.oke.minimum.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciRegion;

public class OkeComponent {

  public static OciContainerEngineForKubernetes getOke(String id, Compartment compartment, OciRegion region) {
    return OciContainerEngineForKubernetes.builder()
        .withId(id)
        .withDescription("Test OKE cluster")
        .withDisplayName("OKE #1")
        .withRegion(region)
        .withCompartment(compartment)
        .build();
  }
}
