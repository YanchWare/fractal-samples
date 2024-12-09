package com.yanchware.fractal.samples.gcp.postgresql.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpPostgreSqlDatabase;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpPostgreSqlDbms;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpRegion;

public class PostgreSqlComponent {

  public static GcpPostgreSqlDbms getDbmsAndDatabase(String id, GcpRegion region) {
    return GcpPostgreSqlDbms.builder()
        .withId(id)
        .withDisplayName(id)
        .withRegion(region)
        .withNetwork("network")
        .withDatabase(
            GcpPostgreSqlDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .withDisplayName(String.format("%s-nice-db", id))
                .build()
        )
        .build();
  }
}
