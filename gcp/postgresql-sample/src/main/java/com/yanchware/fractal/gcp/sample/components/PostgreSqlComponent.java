package com.yanchware.fractal.gcp.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpPostgreSqlDatabase;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpPostgreSqlDbms;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.gcp.GcpRegion.EU_WEST1;

public class PostgreSqlComponent {

  public static GcpPostgreSqlDbms getDbmsAndDatabase(String id) {
    return GcpPostgreSqlDbms.builder()
        .withId(id)
        .withRegion(EU_WEST1)
        .withDatabase(
            GcpPostgreSqlDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .build()
        )
        .build();
  }
}
