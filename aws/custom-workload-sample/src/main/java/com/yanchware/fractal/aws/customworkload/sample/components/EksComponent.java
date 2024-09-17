package com.yanchware.fractal.aws.customworkload.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsElasticKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public class EksComponent {

  public static AwsElasticKubernetesService getEksWithCustomWorkload(String id, AwsRegion region) {
    return AwsElasticKubernetesService.builder()
        .withId(id)
        .withDescription("Test EKS cluster")
        .withDisplayName("EKS #1")
        .withRegion(region)
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
