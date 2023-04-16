package com.yanchware.fractal;

import com.yanchware.fractal.gcp.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.gcp.sample.components.PostgreSqlComponent.getDbmsAndDatabase;

public class PostgreSqlSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = EnvVarConfiguration.getInstance();

    var env = Environment.builder()
        .withId(configuration.getProjectId())
        .withDisplayName(configuration.getEnvironmentDisplayName())
        .withParentId(configuration.getOrganizationId())
        .withParentType("organization")
        .build();


    // INSTANTIATION:
    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("PostgreSql sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponent(getDbmsAndDatabase("postrgresql-1"))
        .withEnvironment(env)
        .build();

    var instantiationConfig = new InstantiationConfiguration() {{
      setWaitConfiguration(new InstantiationWaitConfiguration() {{
        setWaitForInstantiation(true);
        setTimeoutMinutes(120);
      }});
    }};

    Automaton.instantiate(List.of(liveSystem), instantiationConfig);
;
  }
}