package com.yanchware.fractal.microservice.sample.components.gcp;

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
        .withNetwork("fractal-demo-host");
  }

  public static GcpProgreSQL build() {
    return getBuilder().build();
  }
}
