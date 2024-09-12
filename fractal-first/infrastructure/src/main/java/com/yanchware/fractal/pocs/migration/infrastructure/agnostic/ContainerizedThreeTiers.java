package com.yanchware.fractal.pocs.migration.infrastructure.agnostic;

import com.yanchware.fractal.pocs.migration.infrastructure.ThreeTierApplication;
import com.yanchware.fractal.pocs.migration.infrastructure.configuration.InfrastructureConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.KubernetesCluster;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class ContainerizedThreeTiers<T extends KubernetesCluster, B extends KubernetesCluster.Builder<T, B>> implements ThreeTierApplication {
    protected final KubernetesCluster.Builder<T, B> kubernetesClusterBuilder;

    private final String liveSystemName;

    public String getLiveSystemName() {
        return liveSystemName;
    }

    public ContainerizedThreeTiers(KubernetesCluster.Builder<T, B> kubernetesClusterBuilder, String liveSystemName) {
        this.kubernetesClusterBuilder = kubernetesClusterBuilder;
        this.liveSystemName = liveSystemName;
        kubernetesClusterBuilder.withAPIGateway(TraefikWorkload.getTraefik());
    }

    public void instantiate(InfrastructureConfiguration configuration) throws InstantiatorException {
        var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(
                InstantiationWaitConfiguration.builder()
                        .withTimeoutMinutes(55)
                        .withWaitForInstantiation(true)
                        .build())
                .build();

        var environment = getEnvironment(configuration);
        var liveSystem = LiveSystem.builder()
                .withFractalName("containerized-three-tiers")
                .withName(liveSystemName)
                .withDescription(String.format("%s Live System", liveSystemName))
                .withResourceGroupId(configuration.getFractalCloudResourceGroupId())
                .withComponents(List.of(
                        kubernetesClusterBuilder.build()))
                .withEnvironment(environment)
                .build();

        Automaton.instantiate(environment);
        Automaton.instantiate(List.of(liveSystem), instantiationConfig);
    }

    public void addCaaSWorkload(CaaSKubernetesWorkload workload) {
        kubernetesClusterBuilder.withK8sWorkload(workload);
    }

    public void addCaaSWorkloads(Collection<CaaSKubernetesWorkload> workloads) {
        kubernetesClusterBuilder.withK8sWorkloadInstances(workloads);
    }

    private static Environment getEnvironment(InfrastructureConfiguration configuration) {
        return Environment.builder()
                .withEnvironmentType(EnvironmentType.PERSONAL)
                .withOwnerId(configuration.getEnvironmentOwnerId())
                .withShortName(configuration.getEnvironmentShortName())
                .withName(configuration.getEnvironmentName())
                .withRegion(AzureRegion.WEST_EUROPE)
                .withResourceGroup(UUID.fromString(configuration.getFractalCloudResourceGroupId()))
                .withTenantId(configuration.getTenantId())
                .withSubscriptionId(configuration.getSubscriptionId())
                .build();
    }
}
