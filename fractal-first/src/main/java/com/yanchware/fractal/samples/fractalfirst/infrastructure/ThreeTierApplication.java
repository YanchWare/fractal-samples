package com.yanchware.fractal.samples.fractalfirst.infrastructure;

import com.yanchware.fractal.samples.fractalfirst.configuration.Configuration;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSKubernetesWorkload;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.Collection;

public interface ThreeTierApplication {
    String getLiveSystemName();
    void instantiate(Configuration configuration, String liveSystemName) throws InstantiatorException;
    void addCaaSWorkload(CaaSKubernetesWorkload workload);
    void addCaaSWorkloads(Collection<CaaSKubernetesWorkload> workloads);
}
