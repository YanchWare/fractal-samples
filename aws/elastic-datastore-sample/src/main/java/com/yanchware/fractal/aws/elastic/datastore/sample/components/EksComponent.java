package com.yanchware.fractal.aws.elastic.datastore.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSElasticDataStore;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsElasticKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public class EksComponent {

  public static AwsElasticKubernetesService getEksWithElasticDatastore(String id, AwsRegion region) {
    return AwsElasticKubernetesService.builder()
        .withId(id)
        .withDescription("Test EKS cluster")
        .withDisplayName("EKS #1")
        .withRegion(region)
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
