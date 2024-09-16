package com.yanchware.fractal.gcp.sample.components;

import com.yanchware.fractal.gcp.sharedconfig.SharedConfiguration;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpPostgreSqlDatabase;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.gcp.GcpPostgreSqlDbms;

public class PostgreSqlComponent {

  public static GcpPostgreSqlDbms getDbmsAndDatabase(String id, SharedConfiguration configuration) {
    return GcpPostgreSqlDbms.builder()
        .withId(id)
        .withRegion(configuration.getRegion())
        .withNetwork("network")
        .withDatabase(
            GcpPostgreSqlDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .build()
        )
        .build();
  }
}
