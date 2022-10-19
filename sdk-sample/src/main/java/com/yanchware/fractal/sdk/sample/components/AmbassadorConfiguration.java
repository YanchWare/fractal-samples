package com.yanchware.fractal.sdk.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.Ambassador;

public class AmbassadorConfiguration {
  
  public static Ambassador getAmbassador() {
    return Ambassador.builder()
        .withId("ambassador")
        .withDisplayName("Ambassador API Gateway")
        .withNamespace("ambassador")
        .withAcmeProviderAuthority("https://acme-v02.api.letsencrypt.org/directory")
        .withHost("hello-world.fractal.cloud")
        .withHostOwnerEmail("hello@domain.com")
        .withTlsSecretName("ambassador-tls-secret-name")
        .build();
  }
}
