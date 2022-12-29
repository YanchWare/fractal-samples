package com.yanchware.fractal;

import com.yanchware.fractal.azure.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;

import static com.yanchware.fractal.azure.sample.components.CosmosComponent.*;

public class CosmosSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = EnvVarConfiguration.getInstance();

    var env = Environment.builder()
        .withId(configuration.getSubscriptionId())
        .withDisplayName(configuration.getEnvironmentDisplayName())
        .withParentId(configuration.getTenantId())
        .withParentType("tenant")
        .build();


    // INSTANTIATION:
    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("PostgreSql sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponents(List.of(
          getDbmsAndDatabaseForMongoDb("nosql-1"),
          getDbmsAndDatabaseForGremlinDb("nosql-1"),
          getDbmsAndDatabaseForPostgreSql("nosql-1"),
          getDbmsAndDatabaseForCosmosTable("nosql-1"),
          getDbmsAndDatabaseForNoSql("nosql-1"),
          getDbmsAndDatabaseForCassandra("nosql-1")))
        .withEnvironment(env)
    .build();

    Automaton.instantiate(List.of(liveSystem));;
  }
}