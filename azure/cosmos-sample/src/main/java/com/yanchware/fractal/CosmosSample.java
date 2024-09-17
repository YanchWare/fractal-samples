package com.yanchware.fractal;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;
import java.util.Map;

import static com.yanchware.fractal.azure.sample.components.CosmosComponent.*;

public class CosmosSample {
  public static void main(String[] args) throws InstantiatorException {
    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(InstantiationWaitConfiguration.builder()
              .withWaitForInstantiation(true)
              .withTimeoutMinutes(120)
              .build()).build();
    
    // INSTANTIATION:
    var automaton = Automaton.getInstance();
automaton.instantiate(List.of(getLiveSystem(automaton, configuration)), instantiationConfig);
  }
  
  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {

    var relationalResourceGroup = new AzureResourceGroup("rg-relational", AzureRegion.SOUTHEAST_ASIA, Map.of("Type", "Relational"));
    var noSqlResourceGroup = new AzureResourceGroup("rg-no-sql",AzureRegion.AUSTRALIA_CENTRAL, Map.of("Type", "NoSql"));
    
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getResourceGroupId().toString(), configuration.getLiveSystemName()))
        .withDescription("Cosmos sample")
        .withComponents(List.of(
            getDbmsAndDatabaseForMongoDb("nosql-mongo-1", noSqlResourceGroup),
            getDbmsAndDatabaseForGremlinDb("nosql-gremlin-1", noSqlResourceGroup),
            getDbmsAndDatabaseForPostgreSql("nosql-postgresql-1", relationalResourceGroup),
            getDbmsAndDatabaseForCosmosTable("nosql-cosmos-table-1", noSqlResourceGroup),
            getDbmsAndDatabaseForNoSql("nosql-1", noSqlResourceGroup),
            getDbmsAndDatabaseForCassandra("nosql-casandra-1", noSqlResourceGroup)))
        .withEnvironment(configuration.getEnvironment())
        .build();
  }
}