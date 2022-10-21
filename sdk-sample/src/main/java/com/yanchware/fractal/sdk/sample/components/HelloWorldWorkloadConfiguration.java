package com.yanchware.fractal.sdk.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.KubernetesWorkload;

public class HelloWorldWorkloadConfiguration {
  public static KubernetesWorkload getHelloWorldWorkloadConfiguration() {
    return KubernetesWorkload.builder()
        .withId("hello-world-2")
        .withDisplayName("Hello World Custom Workload")
        .withSSHRepositoryURI("git@github.com:YanchWare/fractal-hello-world.git")
        .withRepoId("YanchWare/fractal-hello-world")
        .withBranchName("env/prod")
        .withPrivateSSHKeySecretId("fractal-deployer-secret-id")
        .withPrivateSSHKeyPassphraseSecretId("fractal-deployer-passphrase-secret-id")
        .withNamespace("fractal")
        .build();
  }
}
