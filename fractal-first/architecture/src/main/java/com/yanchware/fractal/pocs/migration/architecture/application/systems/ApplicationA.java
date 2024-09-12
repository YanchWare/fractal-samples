package com.yanchware.fractal.pocs.migration.architecture.application.systems;

import com.yanchware.fractal.pocs.migration.architecture.application.components.Backend;
import com.yanchware.fractal.pocs.migration.architecture.application.components.Frontend;
import com.yanchware.fractal.pocs.migration.infrastructure.Provider;
import com.yanchware.fractal.pocs.migration.infrastructure.ThreeTierApplication;
import com.yanchware.fractal.pocs.migration.infrastructure.configuration.InfrastructureConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

public class ApplicationA {
    private final ThreeTierApplication app;
    private final InfrastructureConfiguration configuration;

    public ApplicationA(InfrastructureConfiguration configuration) {
        this.configuration = configuration;
        this.app = Provider.getThreeTierApplication("application-a");
        app.addCaaSWorkload(Backend.getComponent());
        app.addCaaSWorkload(Frontend.getComponent());
    }

    public void instantiate() throws InstantiatorException {
        app.instantiate(configuration);
    }
}
