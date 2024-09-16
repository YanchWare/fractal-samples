package com.yanchware.fractal.pocs.migration.architecture.application.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSKubernetesWorkload;

import static com.yanchware.fractal.pocs.migration.architecture.configuration.Constants.*;

public class Backend {

    public static CaaSKubernetesWorkload getComponent() {
        var id = "migration-poc-backend";
        var nameAndDescription = "Migration PoC Backend";

        return CaaSKubernetesWorkload.builder()
                .withId(id)
                .withDisplayName(nameAndDescription)
                .withDescription(nameAndDescription)
                .withSSHRepositoryURI("git@ssh.dev.azure.com:v3/YanchWare/Migration%20PoC/Backend")
                .withRepoId("Migration%20PoC/Backend")
                .withBranchName(DEFAULT_BRANCH_NAME)
                .withPrivateSSHKeySecretId(DEPLOYER_SSH_KEY_SECRET_ID)
                .withPrivateSSHKeyPassphraseSecretId(DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_ID)
                .withNamespace(DEFAULT_NAMESPACE)
                .build();
    }
}
