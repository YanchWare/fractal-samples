package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzurePostgreSqlDatabase;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzurePostgreSqlDbms;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.cosmos.*;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion.EUROPE_WEST;

public class CosmosComponent {

  public static AzureCosmosMongoDbms getDbmsAndDatabaseForMongoDb(String id) {
    return AzureCosmosMongoDbms.builder()
      .withId(id)
      .withRegion(EUROPE_WEST)
      .withCosmosEntity(
        AzureCosmosMongoDatabase.builder()
          .withId(String.format("%s-nice-db", id))
          .build())
    .build();
  }

  public static AzureCosmosGremlinDbms getDbmsAndDatabaseForGremlinDb(String id) {
    return AzureCosmosGremlinDbms.builder()
        .withId(id)
        .withRegion(EUROPE_WEST)
        .withCosmosEntity(
            AzureCosmosGremlinDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .build())
    .build();
  }

  public static AzureCosmosPostgreSqlDbms getDbmsAndDatabaseForPostgreSql(String id) {
    return AzureCosmosPostgreSqlDbms.builder()
      .withId(id)
      .withRegion(EUROPE_WEST)
      .withCosmosEntity(
        AzureCosmosPostgreSqlDatabase.builder()
          .withId(String.format("%s-nice-db", id))
          .build())
    .build();
  }

  public static AzureCosmosTableDbms getDbmsAndDatabaseForCosmosTable(String id) {
    return AzureCosmosTableDbms.builder()
      .withId(id)
      .withRegion(EUROPE_WEST)
      .withCosmosEntity(
        AzureCosmosTableEntity.builder()
          .withId(String.format("%s-nice-db", id))
          .build())
    .build();
  }

  public static AzureCosmosNoSqlDbms getDbmsAndDatabaseForNoSql(String id) {
    return AzureCosmosNoSqlDbms.builder()
      .withId(id)
      .withRegion(EUROPE_WEST)
      .withCosmosEntity(
        AzureCosmosNoSqlDatabase.builder()
          .withId(String.format("%s-nice-db", id))
          .build())
    .build();
  }

  public static AzureCosmosCassandraCluster getDbmsAndDatabaseForCassandra(String id) {
    return AzureCosmosCassandraCluster.builder()
      .withId(id)
      .withRegion(EUROPE_WEST)
    .build();
  }

}
