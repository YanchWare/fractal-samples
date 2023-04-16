package com.yanchware.fractal.gcp.gke.elastic.logging.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSAmbassador;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSElasticLogging;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpNodePool;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GoogleKubernetesEngine;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpMachine.E2_STANDARD2;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpRegion.EU_WEST1;

public class GkeComponent {

  public static GoogleKubernetesEngine getGke(String id) {
    return GoogleKubernetesEngine.builder()
        .withId(id)
        .withRegion(EU_WEST1)
        .withNodePools(getNodePools())
        .withAPIGateway(getAmbassador())
        .withLogging(getElasticLoggingExample())
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
        .withNamespace("ambassador")
        .withHost("api.yourdomain.com")
        .withHostOwnerEmail("email@yourdomain.com")
        .withAcmeProviderAuthority("https://acme-v02.api.letsencrypt.org/directory")
        .withTlsSecretName("env-tls-cert")
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
