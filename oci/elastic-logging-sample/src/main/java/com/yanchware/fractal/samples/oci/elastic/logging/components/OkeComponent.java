package com.yanchware.fractal.samples.oci.elastic.logging.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSElasticLogging;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;

public class OkeComponent {

  public static OciContainerEngineForKubernetes getOkeWithElasticLogging(String id, Compartment compartment, OciRegion region) {
    return OciContainerEngineForKubernetes.builder()
        .withId(id)
        .withDescription("Test OKE cluster")
        .withDisplayName("OKE #1")
        .withRegion(region)
        .withCompartment(compartment)
        .withLogging(getElasticLoggingExample())
        .build();
  }

  public static CaaSElasticLogging getElasticLoggingExample() {
    return CaaSElasticLogging.builder()
            .withId("elastic-logging")
            .withVersion("2.5.0")
            .withNamespace("elastic")
            .withKibana(true)
            .withAPM(true)
            .withElasticVersion("8.5")
            .withInstances(1)
            .withStorage("10Gi")
            .withStorageClassName("standard")
            .withMemory(1)
            .withCpu(1)
            .build();
  }
}
