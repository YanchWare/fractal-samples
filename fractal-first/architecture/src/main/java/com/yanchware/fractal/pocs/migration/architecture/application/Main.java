package com.yanchware.fractal.pocs.migration.architecture.application;

import com.yanchware.fractal.pocs.migration.architecture.application.systems.ApplicationA;
import com.yanchware.fractal.pocs.migration.infrastructure.configuration.EnvVarInfrastructureConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

public class Main {
    public static void main(String[] args) throws InstantiatorException {
        var configuration = EnvVarInfrastructureConfiguration.getInstance();
        var applicationA = new ApplicationA(configuration);
        applicationA.instantiate();
    }
}
