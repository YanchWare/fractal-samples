package com.yanchware.fractal;

import com.yanchware.fractal.azure.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.EnvironmentType;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;

import java.util.List;
import java.util.Map;

import static com.yanchware.fractal.azure.sample.components.CosmosComponent.*;

public class CosmosSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = EnvVarConfiguration.getInstance();

    var env = Environment.builder()
        .withEnvironmentType(EnvironmentType.fromString(configuration.getEnvironmentType()))
        .withId(configuration.getEnvironmentId())
        .withOwnerId(configuration.getEnvironmentOwnerId())
        .build();

    var relationalResourceGroup = new AzureResourceGroup("rg-relational", AzureRegion.ASIA_SOUTHEAST, Map.of("Type", "Relational"));
    var noSqlResourceGroup = new AzureResourceGroup("rg-no-sql",AzureRegion.AUSTRALIA_CENTRAL, Map.of("Type", "NoSql"));


    // INSTANTIATION:
    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Cosmos sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponents(List.of(
          getDbmsAndDatabaseForMongoDb("nosql-1", noSqlResourceGroup),
          getDbmsAndDatabaseForGremlinDb("nosql-1", noSqlResourceGroup),
          getDbmsAndDatabaseForPostgreSql("nosql-1", relationalResourceGroup),
          getDbmsAndDatabaseForCosmosTable("nosql-1", noSqlResourceGroup),
          getDbmsAndDatabaseForNoSql("nosql-1", noSqlResourceGroup),
          getDbmsAndDatabaseForCassandra("nosql-1", noSqlResourceGroup)))
        .withEnvironment(env)
    .build();

    Automaton.instantiate(List.of(liveSystem));
  }
}