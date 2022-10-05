package com.yanchware.fractal.azure.aks.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PodManagedIdentity;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PreemptionPolicy;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PriorityClass;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureAddonProfile;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureNodePool;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PreemptionPolicy.NEVER;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PreemptionPolicy.PREEMPT_LOWER_PRIORITY;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureOsType.LINUX;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;

public class AksComponent {

  public static AzureKubernetesService getAks(String id) {
    return AzureKubernetesService.builder()
        .withId(id)
        .withDescription("Test AKS cluster")
        .withDisplayName("AKS #1")
        .withRegion(EUROPE_WEST)
        .withServiceIpRange("10.2.0.0/16")
        .withPodIpRange("10.3.0.0/16")
        .withVnetAddressSpaceIpRange("10.1.0.0/22")
        .withVnetSubnetAddressIpRange("10.1.0.0/22")
        .withExternalWorkspaceResourceId("workplaceResourceId")
        .withExternalLoadBalancerOutboundIps(List.of("ip1", "ip2"))
        .withAddonProfiles(List.of(AzureAddonProfile.AZURE_POLICY, AzureAddonProfile.AZURE_KEYVAULT_SECRETS_PROVIDER))
        .withWindowsAdminUsername("")
        .withNodePool(getNodePools())
        .withPriorityClass(getPriorityClass("fractal-critical", PREEMPT_LOWER_PRIORITY, 1_000_000_000))
        .withPriorityClass(getPriorityClass("fractal-critical-2", NEVER, 999_999_000))
        .withPodManagedIdentity(getPodManagedIdentity())
        .build();
  }

  private static PodManagedIdentity getPodManagedIdentity() {
    return PodManagedIdentity.builder()
        .withName("azure-pod-identity")
        .withNamespace("kube-system")
        .withExceptionPodLabels(Map.of("app", "mic", "component", "mic"))
        .withEnable(true)
        .withAllowNetworkPluginKubeNet(true)
        .build();
  }

  private static PriorityClass getPriorityClass(String name, PreemptionPolicy preemptLowerPriority, int value) {
    return PriorityClass.builder()
        .withName(name)
        .withDescription("Used for Fractal Components")
        .withPreemptionPolicy(preemptLowerPriority)
        .withValue(value)
        .build();
  }

  public static AzureNodePool getNodePools() {
    return AzureNodePool.builder()
        .withName("akslinux")
        .withDiskSizeGb(35)
        .withMachineType(STANDARD_B2S)
        .withMaxNodeCount(3)
        .withInitialNodeCount(1)
        .withMaxSurge(1)
        .withMinNodeCount(1)
        .withMaxPodsPerNode(100)
        .withOsType(LINUX)
        .withAutoscalingEnabled(true)
        .withKubernetesVersion("1.1.1")
        .build();
  }

}
