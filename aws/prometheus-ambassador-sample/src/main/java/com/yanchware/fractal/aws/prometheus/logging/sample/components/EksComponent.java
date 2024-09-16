package com.yanchware.fractal.aws.prometheus.logging.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSAmbassador;
import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSPrometheus;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsElasticKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public class EksComponent {

  public static AwsElasticKubernetesService getEksWithAmbassadorAndMonitoring(String id, AwsRegion region) {
    return AwsElasticKubernetesService.builder()
        .withId(id)
        .withDescription("Test EKS cluster")
        .withDisplayName("EKS #1")
        .withRegion(region)
        .withAPIGateway(getAmbassador())
        .withMonitoring(getPrometheusExample())
        .build();
  }

  public static CaaSAmbassador getAmbassador() {
    return CaaSAmbassador.builder()
            .withId("ambassador")
            .withHost("api.yourdomain.com")
            .withHostOwnerEmail("email@yourdomain.com")
            .withAcmeProviderAuthority("https://acme-v02.api.letsencrypt.org/directory")
            .withTlsSecretName("env-tls-cert")
            .withNamespace("ambassador-01")
            .build();
  }

  public static CaaSPrometheus getPrometheusExample() {
    return CaaSPrometheus.builder()
            .withId("prometheus")
            .withDescription("Prometheus monitoring")
            .withDisplayName("Prometheus")
            .withNamespace("monitoring")
            .withApiGatewayUrl("api.yourdomain.com")
            .build();
  }

}
