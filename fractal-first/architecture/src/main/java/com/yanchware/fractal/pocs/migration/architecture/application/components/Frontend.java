package com.yanchware.fractal.pocs.migration.architecture.application.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;

import static com.yanchware.fractal.pocs.migration.architecture.configuration.Constants.*;
import static com.yanchware.fractal.pocs.migration.architecture.configuration.Constants.DEFAULT_NAMESPACE;

public class Frontend {
    public static CaaSKubernetesWorkload getComponent() {
        var id = "migration-poc-frontend";
        var nameAndDescription = "Migration PoC Frontend";

        return CaaSKubernetesWorkload.builder()
                .withId(id)
                .withDisplayName(nameAndDescription)
                .withDescription(nameAndDescription)
                .withSSHRepositoryURI("git@ssh.dev.azure.com:v3/YanchWare/Migration%20PoC/Frontend")
                .withRepoId("Migration%20PoC/Frontend")
                .withBranchName(DEFAULT_BRANCH_NAME)
                .withPrivateSSHKeySecretId(DEPLOYER_SSH_KEY_SECRET_ID)
                .withPrivateSSHKeyPassphraseSecretId(DEPLOYER_SSH_KEY_PASSPHRASE_SECRET_ID)
                .withNamespace(DEFAULT_NAMESPACE)
                .build();
    }
}
