package com.yanchware.fractal.microservice.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.KubernetesWorkload;

public class SampleMicroserviceWorkload {

  public static KubernetesWorkload.KubernetesWorkloadBuilder getBuilder() {
    return KubernetesWorkload.builder()
        .withId("microservice")
        .withDisplayName("Sample microservice")
        .withDescription("Sample microservice")
        .withSSHRepositoryURI("git@github.com:ximbor/dotnetcore-docs-hello-world.git")
        .withRepoId("ximbor/dotnetcore-docs-hello-world")
        .withBranchName("master")
        .withPrivateSSHKeySecretId("fractalDeployerSshKey")
        .withPrivateSSHKeyPassphraseSecretId("fractalDeployerSshPassphrase")
        .withNamespace("microservice");
  }

  public static KubernetesWorkload build() {
    return getBuilder().build();
  }
}
