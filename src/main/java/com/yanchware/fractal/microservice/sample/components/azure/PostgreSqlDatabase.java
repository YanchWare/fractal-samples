package com.yanchware.fractal.microservice.sample.components.azure;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PostgreSQLDB;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PostgreSQLDB.PostgreSQLDBBuilder;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzurePostgreSQL;
import com.yanchware.fractal.sdk.valueobjects.ComponentId;

public class PostgreSqlDatabase {

  public static PostgreSQLDBBuilder getBuilder() {
    return PostgreSQLDB.builder()
        .withId(ComponentId.from("demo-db"))
        .withDisplayName("PostgreSQL Database 2")
        .withDescription("PostgreSQL Database 2")
        .withName("demo-db");
  }

  public static PostgreSQLDB build() {
    return getBuilder().build();
  }
}
