package com.yanchware.fractal.samples.oci.prometheus.ambassador.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSAmbassador;
import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSPrometheus;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;

public class OkeComponent {

  public static OciContainerEngineForKubernetes getOkeWithAmbassadorAndMonitoring(String id, Compartment compartment, OciRegion region) {
    return OciContainerEngineForKubernetes.builder()
        .withId(id)
        .withDescription("Test OKE cluster")
        .withDisplayName("OKE #1")
        .withRegion(region)
        .withCompartment(compartment)
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
