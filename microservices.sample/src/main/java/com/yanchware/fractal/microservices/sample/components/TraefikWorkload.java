package com.yanchware.fractal.microservices.sample.components;

import com.yanchware.fractal.microservices.sample.configuration.Configuration;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.ForwardAuthSettings;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.Traefik;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.TraefikEntryPoint;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.TraefikTlsCertificate;

import java.util.List;

public class TraefikWorkload {

  public static Traefik getTraefik(Configuration configuration) {
    return Traefik.builder()
        .withId("traefik")
        .withNamespace("traefik")
        .withDisplayName("Traefik")
        .withDescription("Traefik")
        .withEntryPoints(List.of(
            getMetricsEntryPoint(),
            getWebEntryPoint(),
            getWebSecureEntryPoint())
        )
        .withHostname("traefik-test.fractal.cloud")
        .withCertificates(List.of(new TraefikTlsCertificate(
            "traefik-tls-cert",
            "traefik-tls-key",
            true)))
        .withForwardAuth(new ForwardAuthSettings(
            "7cf5b2fa-9655-4baa-994d-1897eefd6fac",
            "traefik-oidc-client-secret",
            "traefik-forward-auth-secret",
            "https://login.microsoftonline.com/82a60e0b-b00c-45e3-b4cb-74ee4799e0c1/v2.0"
        ))
        .withJaegerHost("jaeger-agent.observability.svc")
        .build();
  }

  private static TraefikEntryPoint getMetricsEntryPoint() {
    return new TraefikEntryPoint(
        "metrics",
        false,
        9100,
        "tcp",
        false);
  }

  private static TraefikEntryPoint getWebSecureEntryPoint() {
    return new TraefikEntryPoint(
        "websecure",
        true,
        8443,
        "tcp",
        false);
  }

  private static TraefikEntryPoint getWebEntryPoint() {
    return new TraefikEntryPoint(
        "web",
        false,
        8000,
        "tcp",
        true);
  }

}
