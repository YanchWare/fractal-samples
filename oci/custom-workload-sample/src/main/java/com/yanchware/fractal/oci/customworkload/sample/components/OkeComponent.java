package com.yanchware.fractal.oci.customworkload.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;

public class OkeComponent {

  public static OciContainerEngineForKubernetes getOkeWithCustomWorkload(String id, Compartment compartment, OciRegion region) {
    return OciContainerEngineForKubernetes.builder()
        .withId(id)
        .withDescription("Test OKE cluster")
        .withDisplayName("OKE #1")
        .withRegion(region)
        .withCompartment(compartment)
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
}
