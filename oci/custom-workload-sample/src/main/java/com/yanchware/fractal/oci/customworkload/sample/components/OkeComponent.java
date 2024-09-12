package com.yanchware.fractal.oci.customworkload.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.aks.AzureNodePool;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciRegion;

import java.util.Collection;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureMachineType.STANDARD_B2S;

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
