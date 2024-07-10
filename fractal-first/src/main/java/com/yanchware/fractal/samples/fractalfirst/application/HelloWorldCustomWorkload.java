package com.yanchware.fractal.samples.fractalfirst.application;


import com.yanchware.fractal.samples.fractalfirst.configuration.Configuration;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;

public class HelloWorldCustomWorkload {
    public static final String DEPLOYER_SSH_KEY_SECRET_ID = "fractal-custom-workload-agent-ssh-public-key";
    public static final String DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_ID = "fractal-custom-workload-agent-ssh-public-key-passphrase";
    private static final String DEFAULT_BRANCH_NAME =  "ref/head/main";
    private static final String DEFAULT_NAMESPACE = "fractal-demo";

    public static CaaSKubernetesWorkload getComponent(Configuration configuration) {
        var id = "hello-world-workload";
        var displayName = "Hello World Workload";
        var description = "Hello World application showcasing usage of a Custom workload";

        return CaaSKubernetesWorkload.builder()
                .withId(id)
                .withDisplayName(displayName)
                .withDescription(description)
                .withSSHRepositoryURI(configuration.getHelloWorldCustomWorkloadGitUri())
                .withRepoId(configuration.getHelloWorldCustomWorkloadRepositoryName())
                .withBranchName(DEFAULT_BRANCH_NAME)
                .withPrivateSSHKeySecretId(DEPLOYER_SSH_KEY_SECRET_ID)
                .withPrivateSSHKeyPassphraseSecretId(DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_ID)
                .withNamespace(DEFAULT_NAMESPACE)
                .build();
    }
}
