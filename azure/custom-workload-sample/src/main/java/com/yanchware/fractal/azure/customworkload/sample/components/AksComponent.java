package com.yanchware.fractal.azure.customworkload.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.KubernetesWorkload;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureNodePool;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;

public class AksComponent {

  public static AzureKubernetesService getAksWithCustomWorkload(String id) {
    return AzureKubernetesService.builder()
        .withId(id)
        .withRegion(EUROPE_WEST)
        .withNodePools(getNodePools())
        .withK8sWorkload(getK8sWorkload())
        .build();
  }

  public static KubernetesWorkload getK8sWorkload() {
    return KubernetesWorkload.builder()
        .withId("fractal-svc")
        .withDescription("Fractal Service on K8S")
        .withDisplayName("Fractal SVC")
        .withNamespace("fractal")
        .withPrivateSSHKeyPassphraseSecretId("fractal-private-passphrase")
        .withPrivateSSHKeySecretId("fractal-private-ssh")
        .withSSHRepositoryURI("ssh-uri")
        .withRepoId("fractal-svc-id")
        .withBranchName("env/fractal-test")
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
