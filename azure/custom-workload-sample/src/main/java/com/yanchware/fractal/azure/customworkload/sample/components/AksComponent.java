package com.yanchware.fractal.azure.customworkload.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureNodePool;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion.EUROPE_WEST;

public class AksComponent {

  public static AzureKubernetesService getAksWithCustomWorkload(String id) {
    return AzureKubernetesService.builder()
        .withId(id)
        .withRegion(EUROPE_WEST)
        .withNodePools(getNodePools())
        .withK8sWorkload(getK8sWorkload())
        .build();
  }

  public static CaaSKubernetesWorkload getK8sWorkload() {
    return CaaSKubernetesWorkload.builder()
        .withId("fractal-samples")
        .withDescription("Fractal Service on K8S")
        .withNamespace("fractal")
        .withPrivateSSHKeyPassphraseSecretId("fractal-deployer-secret-id")
        .withPrivateSSHKeySecretId("fractal-deployer-passphrase-secret-id")
        .withSSHRepositoryURI("git@github.com:YanchWare/fractal-samples.git")
        .withRepoId("YanchWare/fractal-samples")
        .withBranchName("env/prod")
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

}
