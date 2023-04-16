package com.yanchware.fractal;

import com.yanchware.fractal.azure.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
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
        .withId(configuration.getSubscriptionId())
        .withDisplayName(configuration.getEnvironmentDisplayName())
        .withParentId(configuration.getTenantId())
        .withParentType("tenant")
        .build();

    var standardTags = Map.of("managed-by", "fractal");
    var relationalResourceGroup = new AzureResourceGroup("Relational", AzureRegion.ASIA_SOUTHEAST, standardTags);
    var noSqlResourceGroup = new AzureResourceGroup("NoSql", AzureRegion.AUSTRALIA_CENTRAL, standardTags);


    // INSTANTIATION:
    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("PostgreSql sample")
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

    Automaton.instantiate(List.of(liveSystem));;
  }
}