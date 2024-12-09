package com.yanchware.fractal.samples.aws.ambassador.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsElasticKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public class EksComponent {

  public static AwsElasticKubernetesService getEks(String id, AwsRegion region) {
    return AwsElasticKubernetesService.builder()
        .withId(id)
        .withDescription("Test EKS cluster")
        .withDisplayName("EKS #1")
        .withRegion(region)
        .build();
  }
}
