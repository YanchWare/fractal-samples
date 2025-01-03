package com.yanchware.fractal.samples.azure.multipleresources.components;

import com.yanchware.fractal.sdk.domain.ComponentLink;
import com.yanchware.fractal.sdk.domain.livesystem.caas.CustomWorkloadRole;
import com.yanchware.fractal.sdk.domain.livesystem.caas.RoleType;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzurePricingPlan;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.appservice.*;
import com.yanchware.fractal.sdk.domain.values.ComponentId;

import java.util.List;
import java.util.Map;

import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureOsType.LINUX;

public class WebAppComponent {

  public static AzureWebApp getDotnetWebAppComponent(String id, ComponentId linkedComponentId, AzureResourceGroup resourceGroup) {

    var appServicePlan = AzureAppServicePlan.builder()
        .withName("asp-sample")
        .withRegion(resourceGroup.getRegion())
        .withAzureResourceGroup(resourceGroup)
        .withOperatingSystem(LINUX)
        .withPricingPlan(AzurePricingPlan.BASIC_B1)
        .withNumberOfWorkers(1)
        .build();

    return AzureWebApp.builder()
        .withId(id)
        .withDisplayName(".NET WebApp")
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withRepoId("YanchWare/fractal-samples")
        .withBranchName("env/prod")
        .withPrivateSSHKeyPassphraseSecretId("fractal-deployer-secret-id")
        .withPrivateSSHKeySecretId("fractal-deployer-passphrase-secret-id")
        .withSSHRepositoryURI("git@github.com:YanchWare/fractal-samples.git")
        .withOperatingSystem(LINUX)
        .withRuntimeStack(AzureWebAppLinuxRuntimeStack.DOTNET_CORE_7_0)
        .withAppServicePlan(appServicePlan)
        .withCustomDomains(List.of("api.app.cloud"))
        .withCertificate(AzureKeyVaultCertificate.builder()
            .withKeyVaultId("/subscriptions/keyvault")
            .withName("sna-app")
            .build())
        .withConfiguration(AzureWebAppConfiguration.builder()
            .withAppSettings(Map.of("ASPNETCORE_ENVIRONMENT", "Development"))
            .withHealthCheckPath("/health/")
            .withNumberOfWorkers(2)
            .build())
        .withDeploymentSlot(AzureWebAppDeploymentSlot.builder()
            .withName("staging")
            .build())
        .withRoles(List.of(CustomWorkloadRole.builder().withName("role").withRoleType(RoleType.BUILT_IN_ROLE).build()))
        .withLink(ComponentLink.builder().withComponentId(linkedComponentId).withRoleName("Contributor").build())
        .build();
  }

}
