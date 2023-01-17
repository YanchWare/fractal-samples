package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.appservice.AzureWebApp;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.appservice.AzureWebAppHosting;

public class WebAppComponent {

  public static AzureWebApp getJavaWebAppComponent(String id, AzureResourceGroup azureResourceGroup) {
    return AzureWebApp.builder()
        .withId(id)
        .withPrivateSSHKeyPassphraseSecretId("fractal-deployer-secret-id")
        .withPrivateSSHKeySecretId("fractal-deployer-passphrase-secret-id")
        .withSSHRepositoryURI("git@github.com:YanchWare/fractal-samples.git")
        .withRepoId("YanchWare/fractal-samples")
        .withBranchName("env/prod")
        .withHosting(
            AzureWebAppHosting.builder()
                .withJavaVersion("1.7")
                .build()
        )
    .build();
  }

}
