package com.yanchware.fractal.samples.azure.prometheus.ambassador.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSAmbassador;
import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSPrometheus;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.aks.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.aks.AzureNodePool;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureMachineType.STANDARD_B2S;

public class AksComponent {

  public static AzureKubernetesService getAks(String id, AzureResourceGroup resourceGroup) {
    return AzureKubernetesService.builder()
        .withId(id)
        .withRegion(resourceGroup.getRegion())
        .withResourceGroup(resourceGroup)
        .withNodePools(getNodePools())
        .withAPIGateway(getAmbassador())
        .withMonitoring(getPrometheusExample())
        .build();
  }

  public static Collection<? extends AzureNodePool> getNodePools() {
    return List.of(
        AzureNodePool.builder()
            .withName("linuxdynamic")
            .withMachineType(STANDARD_B2S)
            .build()
    );
  }
  
  public static CaaSAmbassador getAmbassador() {
    return CaaSAmbassador.builder()
        .withId("ambassador")
        .withDisplayName("ambassador")
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
        .withDisplayName("prometheus")
        .withNamespace("monitoring")
        .withApiGatewayUrl("api.yourdomain.com")
        .build();
  }

}
