package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.cosmos.*;

public class CosmosComponent {

  public static AzureCosmosMongoDbms getDbmsAndDatabaseForMongoDb(String id, AzureResourceGroup azureResourceGroup) {
    return AzureCosmosMongoDbms.builder()
        .withId(id)
        .withAzureResourceGroup(azureResourceGroup)
        .withCosmosEntity(
            AzureCosmosMongoDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .build())
        .build();
  }

  public static AzureCosmosGremlinDbms getDbmsAndDatabaseForGremlinDb(String id, AzureResourceGroup azureResourceGroup) {
    return AzureCosmosGremlinDbms.builder()
        .withId(id)
        .withAzureResourceGroup(azureResourceGroup)
        .withCosmosEntity(
            AzureCosmosGremlinDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .build())
        .build();
  }

  public static AzureCosmosPostgreSqlDbms getDbmsAndDatabaseForPostgreSql(String id, AzureResourceGroup azureResourceGroup) {
    return AzureCosmosPostgreSqlDbms.builder()
        .withId(id)
        .withAzureResourceGroup(azureResourceGroup)
        .withCosmosEntity(
            AzureCosmosPostgreSqlDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .build())
        .build();
  }

  public static AzureCosmosTableDbms getDbmsAndDatabaseForCosmosTable(String id, AzureResourceGroup azureResourceGroup) {
    return AzureCosmosTableDbms.builder()
        .withId(id)
        .withAzureResourceGroup(azureResourceGroup)
        .withCosmosEntity(
            AzureCosmosTableEntity.builder()
                .withId(String.format("%s-nice-db", id))
                .build())
        .build();
  }

  public static AzureCosmosNoSqlDbms getDbmsAndDatabaseForNoSql(String id, AzureResourceGroup azureResourceGroup) {
    return AzureCosmosNoSqlDbms.builder()
        .withId(id)
        .withAzureResourceGroup(azureResourceGroup)
        .withCosmosEntity(
            AzureCosmosNoSqlDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .build())
        .build();
  }

  public static AzureCosmosCassandraCluster getDbmsAndDatabaseForCassandra(String id, AzureResourceGroup azureResourceGroup) {
    return AzureCosmosCassandraCluster.builder()
        .withId(id)
        .withAzureResourceGroup(azureResourceGroup)
        .withHoursBetweenBackups(24)
        .build();
  }

}
