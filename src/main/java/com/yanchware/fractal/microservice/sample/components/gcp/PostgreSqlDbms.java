package com.yanchware.fractal.microservice.sample.components.gcp;


import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PostgreSQLDB;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzurePostgreSQL;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzurePostgreSQL.AzurePostgreSQLBuilder;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpProgreSQL;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.gcp.GcpRegion;
import com.yanchware.fractal.sdk.valueobjects.ComponentId;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureSkuName.B_GEN5_1;

public class PostgreSqlDbms {

  public static GcpProgreSQL.GcpPostgreSQLBuilder getBuilder() {
    return GcpProgreSQL.builder()
        .withId(ComponentId.from("rdbms-1"))
        .withDescription("PostgreSQL DBMS")
        .withDisplayName("PostgreSQL DBMS")
        .withRegion(GcpRegion.EU_WEST1)
        .withDatabase(PostgreSQLDB.builder()
            .withId(ComponentId.from("demo-db"))
            .withDisplayName("PostgreSQL Database 2")
            .withDescription("PostgreSQL Database 2")
            .withName("demo-db")
            .build()
        );
  }

  public static GcpProgreSQL build() {
    return getBuilder().build();
  }
}
