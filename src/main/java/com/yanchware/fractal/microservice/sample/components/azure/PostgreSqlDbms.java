package com.yanchware.fractal.microservice.sample.components.azure;


import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PostgreSQLDB;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzurePostgreSQL;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzurePostgreSQL.AzurePostgreSQLBuilder;
import com.yanchware.fractal.sdk.valueobjects.ComponentId;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.*;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureSkuName.*;

public class PostgreSqlDbms {

  public static AzurePostgreSQLBuilder getBuilder() {
    return AzurePostgreSQL.builder()
        .withId(ComponentId.from("rdbms-1"))
        .withDescription("PostgreSQL DBMS")
        .withDisplayName("PostgreSQL DBMS")
        .withRegion(EUROPE_WEST)
        .withDatabase(PostgreSQLDB.builder()
            .withId(ComponentId.from("demo-db"))
            .withDisplayName("PostgreSQL Database 2")
            .withDescription("PostgreSQL Database 2")
            .withName("demo-db")
            .build()
        )
        .withSkuName(B_GEN5_1);
  }

  public static AzurePostgreSQL build() {
    return getBuilder().build();
  }
}
