package com.yanchware.fractal.oci.elastic.datastore.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSElasticDataStore;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;

public class OkeComponent {

  public static OciContainerEngineForKubernetes getOkeWithElasticDatastore(String id, Compartment compartment, OciRegion region) {
    return OciContainerEngineForKubernetes.builder()
        .withId(id)
        .withDescription("Test OKE cluster")
        .withDisplayName("OKE #1")
        .withRegion(region)
        .withCompartment(compartment)
        .withDocumentDB(getElasticDataStoreExample())
        .build();
  }

  public static CaaSElasticDataStore getElasticDataStoreExample() {
    return CaaSElasticDataStore.builder()
            .withId("elastic-data")
            .withVersion("2.5.0")
            .withNamespace("elastic")
            .withKibana(true)
            .withElasticVersion("8.5")
            .withInstances(1)
            .withStorage("10Gi")
            .withStorageClassName("standard")
            .withMemory(1)
            .withCpu(1)
            .build();
  }
}
