package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.AzureRelay;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion.EUROPE_WEST;

public class Relay {
  public static AzureRelay getRelay() {
    AzureResourceGroup azureResourceGroup = AzureResourceGroup
        .builder()
        .withName("rg-relay")
        .withRegion(EUROPE_WEST)
        .build();
    return AzureRelay.builder()
        .withId("relay")
        .withName("relay")
        .withRegion(AzureRegion.EUROPE_WEST)
        .withAzureResourceGroup(azureResourceGroup)
        .build();
  }
}
