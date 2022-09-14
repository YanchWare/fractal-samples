package com.yanchware.fractal.microservice.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.*;

public class AmbassadorWorkload {

  public static Ambassador.AmbassadorBuilder getBuilder() {
    return Ambassador.builder()
        .withId("api-gateway")
        .withDescription("API Gateway")
        .withDisplayName("API Gateway 1")
        .withNamespace("ambassador")
        .withAcmeProviderAuthority("https://acme-v02.api.letsencrypt.org/directory")
        .withHost("azure.test.fractal.cloud")
        .withHostOwnerEmail("simone@yanchware.com")
        .withTlsSecretName("ambassador-tls-secret");
  }

  public static Ambassador build() {
    return getBuilder().build();
  }

}
