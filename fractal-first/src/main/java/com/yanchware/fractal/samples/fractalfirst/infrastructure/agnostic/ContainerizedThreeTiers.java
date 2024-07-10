package com.yanchware.fractal.samples.fractalfirst.infrastructure.agnostic;

import com.yanchware.fractal.samples.fractalfirst.configuration.Configuration;
import com.yanchware.fractal.samples.fractalfirst.infrastructure.ThreeTierApplication;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.KubernetesCluster;
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

    public void instantiate(Configuration configuration, String liveSystemName) throws InstantiatorException {
        var instantiationConfig = new InstantiationConfiguration() {{
            setWaitConfiguration(new InstantiationWaitConfiguration() {{
                setWaitForInstantiation(true);
                setTimeoutMinutes(55);
            }});
        }};

        var environment = getEnvironment(configuration);
        var liveSystem = LiveSystem.builder()
                .withFractalName("demo-migration-fractal")
                .withName(liveSystemName)
                .withDescription("Demo Migration Live System")
                .withResourceGroupId(configuration.getFractalCloudResourceGroupId())
                .withComponents(List.of(
                        kubernetesClusterBuilder.build()))
                .withEnvironment(environment)
                .build();

        Automaton.instantiate(List.of(liveSystem), instantiationConfig);
    }

    public void addCaaSWorkload(CaaSKubernetesWorkload workload) {
        kubernetesClusterBuilder.withK8sWorkload(workload);
    }

    public void addCaaSWorkloads(Collection<CaaSKubernetesWorkload> workloads) {
        kubernetesClusterBuilder.withK8sWorkloadInstances(workloads);
    }

    private static Environment getEnvironment(Configuration configuration) {
        return Environment.builder()
                .withEnvironmentType(EnvironmentType.PERSONAL)
                .withOwnerId(configuration.getEnvironmentOwnerId())
                .withShortName(configuration.getEnvironmentShortName())
                .withResourceGroup(UUID.fromString(configuration.getFractalCloudResourceGroupId()))
                .build();
    }
}
