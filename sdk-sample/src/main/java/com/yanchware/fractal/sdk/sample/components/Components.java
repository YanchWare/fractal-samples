package com.yanchware.fractal.sdk.sample.components;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.NodeTaint;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.TaintEffect;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureNodePool;
import java.util.Collection;
import java.util.List;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureOsType.LINUX;

public class Components {

  public static Collection<? extends AzureNodePool> getNodePools() {
    return List.of(
        AzureNodePool.builder()
            .withName("linuxdynamic")
            .withOsType(LINUX)
            .withDiskSizeGb(64)
            .withInitialNodeCount(1)
            .withMachineType(STANDARD_B2S)
            .withMaxNodeCount(9)
            .withMaxPodsPerNode(30)
            .withMaxSurge(1)
            .withMinNodeCount(1)
            .withAutoscalingEnabled(true)
            .withNodeTaint(NodeTaint.builder()
                .withKey("CriticalAddonsOnly")
                .withValue("true")
                .withEffect(TaintEffect.NO_SCHEDULE)
                .build())
            .build()
    );
  }

}
