package com.yanchware.fractal.azure.ambassador.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSAmbassador;
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
        .withHost("api.yourdomain.com")
        .withHostOwnerEmail("email@yourdomain.com")
        .withAcmeProviderAuthority("https://acme-v02.api.letsencrypt.org/directory")
        .withTlsSecretName("env-tls-cert")
        .withNamespace("ambassador-01")
        .build();
  }

}
