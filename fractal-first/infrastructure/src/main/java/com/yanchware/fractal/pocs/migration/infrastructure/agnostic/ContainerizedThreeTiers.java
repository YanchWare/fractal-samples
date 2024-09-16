package com.yanchware.fractal.pocs.migration.infrastructure.agnostic;

import com.yanchware.fractal.pocs.migration.infrastructure.ThreeTierApplication;
import com.yanchware.fractal.pocs.migration.infrastructure.configuration.InfrastructureConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.blueprint.FractalIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sdk.domain.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.livesystem.paas.KubernetesCluster;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;

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

        var automaton = Automaton.getInstance();
        var environment = getEnvironment(automaton, configuration);
        var liveSystem = automaton.getLiveSystemBuilder()
                .withId(new LiveSystemIdValue(configuration.getFractalCloudResourceGroupId(), liveSystemName))
                .withFractalId(new FractalIdValue(configuration.getFractalCloudResourceGroupId(), "containerized-three-tiers", "v1.0"))
                .withDescription(String.format("%s Live System", liveSystemName))
                .withComponents(List.of(kubernetesClusterBuilder.build()))
                .withEnvironment(environment)
                .build();

        automaton.instantiate(environment);
        automaton.instantiate(List.of(liveSystem), instantiationConfig);
    }

    public void addCaaSWorkload(CaaSKubernetesWorkload workload) {
        kubernetesClusterBuilder.withK8sWorkload(workload);
    }

    public void addCaaSWorkloads(Collection<CaaSKubernetesWorkload> workloads) {
        kubernetesClusterBuilder.withK8sWorkloadInstances(workloads);
    }

    private static EnvironmentAggregate getEnvironment(Automaton automaton, InfrastructureConfiguration configuration) {
        return automaton.getEnvironmentBuilder()
                .withId(new EnvironmentIdValue(
                        EnvironmentType.PERSONAL,
                        configuration.getEnvironmentOwnerId(),
                        configuration.getEnvironmentShortName()))
                .withName(configuration.getEnvironmentName())
                .withAzureCloudAgent(
                        AzureRegion.WEST_EUROPE,
                        configuration.getTenantId(),
                        configuration.getSubscriptionId())
                .withResourceGroup(UUID.fromString(configuration.getFractalCloudResourceGroupId()))
                .build();
    }
}
