package com.yanchware.fractal.gcp.gke.sample.components;

import com.yanchware.fractal.gcp.sharedconfig.SharedConfiguration;
import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSAmbassador;
import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSPrometheus;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpNodePool;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GoogleKubernetesEngine;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpMachine.E2_STANDARD2;


public class GkeComponent {

  public static GoogleKubernetesEngine getGke(String id, SharedConfiguration configuration) {
    return GoogleKubernetesEngine.builder()
        .withId(id)
        .withRegion(configuration.getRegion())
        .withNodePools(getNodePools())
        .withAPIGateway(getAmbassador())
        .withMonitoring(getPrometheusExample())
        .build();
  }

  public static Collection<? extends GcpNodePool> getNodePools() {
    return List.of(
        GcpNodePool.builder()
            .withName("nodes")
            .withMachineType(E2_STANDARD2)
            .build()
    );
  }

  public static CaaSAmbassador getAmbassador() {
    return CaaSAmbassador.builder()
        .withId("ambassador")
        .withNamespace("ambassador-01")
        .withHost("api.yourdomain.com")
        .withHostOwnerEmail("email@yourdomain.com")
        .withAcmeProviderAuthority("https://acme-v02.api.letsencrypt.org/directory")
        .withTlsSecretName("env-tls-cert")
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
