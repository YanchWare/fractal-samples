package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzurePostgreSqlDatabase;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzurePostgreSqlDbms;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion.WEST_EUROPE;

public class PostgreSqlComponent {

  public static AzurePostgreSqlDbms getDbmsAndDatabase(String id) {
    return AzurePostgreSqlDbms.builder()
        .withId(id)
        .withRegion(WEST_EUROPE)
        .withDatabase(
            AzurePostgreSqlDatabase.builder()
                .withId(String.format("%s-nice-db", id))
                .withName(String.format("%s-nice-db", id))
                .build()
        )
        .build();
  }
}
