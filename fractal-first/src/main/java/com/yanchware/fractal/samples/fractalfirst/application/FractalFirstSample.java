package com.yanchware.fractal.samples.fractalfirst.application;

import com.yanchware.fractal.samples.fractalfirst.configuration.EnvVarConfiguration;
import com.yanchware.fractal.samples.fractalfirst.infrastructure.Provider;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

public class FractalFirstSample {
    public static void main(String[] args) throws InstantiatorException {
        var configuration = EnvVarConfiguration.getInstance();
        var app = Provider.getThreeTierApplication();
        app.addCaaSWorkload(HelloWorldCustomWorkload.getComponent(configuration));
        app.instantiate(configuration, app.getLiveSystemName());
    }
}