package com.yanchware.fractal.gcp.customworkload.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.Ambassador;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.KubernetesWorkload;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpNodePool;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GoogleKubernetesEngine;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpMachine.E2_STANDARD2;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpRegion.EU_WEST1;

public class GkeComponent {

  public static GoogleKubernetesEngine getGke(String id) {
    return GoogleKubernetesEngine.builder()
        .withId(id)
        .withRegion(EU_WEST1)
        .withNodePools(getNodePools())
        .withK8sWorkload(getK8sWorkload())
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

  public static KubernetesWorkload getK8sWorkload() {
    return KubernetesWorkload.builder()
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
}
