package com.yanchware.fractal.pocs.migration.infrastructure.agnostic;

import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.CaaSTraefik;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.TraefikEntryPoint;

import java.util.Collections;
import java.util.List;

public class TraefikWorkload {
  public static CaaSTraefik getTraefik() {
    var name = "traefik";
    var displayName = String.format("Traefik - %s", name);
    var description = String.format("Traefik instance named '%s'", name);
    var hostname = "demo.fractal.cloud";

    return CaaSTraefik.builder()
        .withId(name)
        .withNamespace(name)
        .withDisplayName(displayName)
        .withDescription(description)
        .withEntryPoints(List.of(
            getWebEntryPoint(),
            getWebSecureEntryPoint()))
        .withHostname(hostname)
        .withDnsZoneConfig(Collections.emptyMap())
        .build();
  }
  

  private static TraefikEntryPoint getWebEntryPoint() {
    return TraefikEntryPoint.builder()
        .withName("web")
        .withProtocol("tcp")
        .withPort(8000)
        .withExposedPort(80)
        .withTlsEnabled(false)
        .withHttpsRedirected(true)
        .build();
  }

  private static TraefikEntryPoint getWebSecureEntryPoint() {
    return TraefikEntryPoint.builder()
        .withName("websecure")
        .withProtocol("tcp")
        .withPort(8443)
        .withExposedPort(443)
        .withTlsEnabled(true)
        .withHttpsRedirected(false)
        .build();
  }
}
