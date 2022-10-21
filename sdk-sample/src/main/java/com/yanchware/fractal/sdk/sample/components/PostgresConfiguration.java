package com.yanchware.fractal.sdk.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PostgreSQL;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PostgreSQLDB;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzurePostgreSQL;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureSkuName.B_GEN5_1;

public class PostgresConfiguration {
  
  public static PostgreSQL getPostgresDbms() {
    return AzurePostgreSQL.builder()
        .withId("rdbms-hw-2")
        .withDisplayName("PostgreSQL DBMS")
        .withRegion(EUROPE_WEST)
        .withDatabase(PostgreSQLDB.builder()
            .withId("sales-db-2")
            .withDisplayName("Sales Database")
            .withName("sales-db")
            .withSchema("customers")
            .build())
        .withDatabase(PostgreSQLDB.builder()
            .withId("invoices-db-2")
            .withDisplayName("Invoices Database")
            .withName("invoices-db")
            .withSchema("invoices")
            .build())
        .withSkuName(B_GEN5_1)
        .build();
  }
}
