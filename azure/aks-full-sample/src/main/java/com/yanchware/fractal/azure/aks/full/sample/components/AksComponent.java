package com.yanchware.fractal.azure.aks.full.sample.components;

import com.yanchware.fractal.sdk.domain.livesystem.caas.PreemptionPolicy;
import com.yanchware.fractal.sdk.domain.livesystem.caas.PriorityClass;
import com.yanchware.fractal.sdk.domain.livesystem.paas.PodManagedIdentity;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.aks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yanchware.fractal.sdk.domain.livesystem.caas.PreemptionPolicy.NEVER;
import static com.yanchware.fractal.sdk.domain.livesystem.caas.PreemptionPolicy.PREEMPT_LOWER_PRIORITY;
import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureOsType.LINUX;

public class AksComponent {

  public static AzureKubernetesService getAks(String id, AzureResourceGroup resourceGroup) {
    return AzureKubernetesService.builder()
        .withId(id)
        .withDescription("Test AKS cluster")
        .withDisplayName("AKS #1")
        .withRegion(resourceGroup.getRegion())
        .withResourceGroup(resourceGroup)
        .withServiceIpRange("10.2.0.0/16")
        .withPodIpRange("10.3.0.0/16")
        .withVnetAddressSpaceIpRange("10.1.0.0/22")
        .withVnetSubnetAddressIpRange("10.1.0.0/22")
        .withExternalWorkspaceResourceId("workplaceResourceId")
        .withOutboundIps(getOutboundIps(resourceGroup))
        .withAddonProfiles(getAddonProfiles())
        .withWindowsAdminUsername("")
        .withNodePool(getNodePools())
        .withPriorityClasses(List.of(
            getPriorityClass("fractal-critical", PREEMPT_LOWER_PRIORITY, 1_000_000_000),
            getPriorityClass("fractal-critical-2", NEVER, 999_999_000)))
        .withPodManagedIdentity(getPodManagedIdentity())
        .withKubernetesVersion("1.27.3")
        .withManagedClusterSkuTier(ManagedClusterSkuTier.STANDARD)
        .build();
  }
  
  private static List<AzureOutboundIp> getOutboundIps(AzureResourceGroup resourceGroup) {    
    return new ArrayList<>() {
      {
        add(AzureOutboundIp.builder()
            .withName("ip1")
            .withAzureResourceGroup(resourceGroup)
            .build());

        add(AzureOutboundIp.builder()
            .withName("ip2")
            .withAzureResourceGroup(resourceGroup)
            .build());
      }
    };
  }

  private static List<AzureKubernetesAddonProfile> getAddonProfiles() {
    return new ArrayList<>() {
      {
        add(AzureKubernetesAddonProfile.builder()
                .withAddonToEnable(AzureKubernetesAddon.AZURE_POLICY)
                .build());

        add(AzureKubernetesAddonProfile.builder()
                .withAddonToEnable(AzureKubernetesAddon.AZURE_KEYVAULT_SECRETS_PROVIDER)
                .withConfig(Map.of("enableSecretRotation", "false", "rotationPollInterval", "2m"))
                .build());
      }
    };
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
        .build();
  }

}
