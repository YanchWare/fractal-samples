package com.yanchware.fractal.pocs.migration.infrastructure;

import com.yanchware.fractal.pocs.migration.infrastructure.configuration.InfrastructureConfiguration;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.Collection;

public interface ThreeTierApplication {
    String getLiveSystemName();
    void instantiate(InfrastructureConfiguration configuration) throws InstantiatorException;
    void addCaaSWorkload(CaaSKubernetesWorkload workload);
    void addCaaSWorkloads(Collection<CaaSKubernetesWorkload> workloads);
}
