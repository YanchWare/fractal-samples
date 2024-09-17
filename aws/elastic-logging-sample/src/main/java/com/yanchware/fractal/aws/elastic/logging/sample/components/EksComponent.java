package com.yanchware.fractal.aws.elastic.logging.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSElasticLogging;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsElasticKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public class EksComponent {

  public static AwsElasticKubernetesService getEksWithElasticLogging(String id, AwsRegion region) {
    return AwsElasticKubernetesService.builder()
        .withId(id)
        .withDescription("Test EKS cluster")
        .withDisplayName("EKS #1")
        .withRegion(region)
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
