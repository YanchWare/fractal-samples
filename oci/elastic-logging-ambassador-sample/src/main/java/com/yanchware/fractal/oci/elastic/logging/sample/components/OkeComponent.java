package com.yanchware.fractal.oci.elastic.logging.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSAmbassador;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSElasticDataStore;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSElasticLogging;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciRegion;

public class OkeComponent {

  public static OciContainerEngineForKubernetes getOkeWithAmbassadorAndElasticLogging(String id, Compartment compartment, OciRegion region) {
    return OciContainerEngineForKubernetes.builder()
        .withId(id)
        .withDescription("Test OKE cluster")
        .withDisplayName("OKE #1")
        .withRegion(region)
        .withCompartment(compartment)
        .withAPIGateway(getAmbassador())
        .withLogging(getElasticLoggingExample())
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
