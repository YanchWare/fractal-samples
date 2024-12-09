package com.yanchware.fractal.samples.azure.relay.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.AzureRelay;


public class Relay {
  public static AzureRelay getRelay(AzureResourceGroup resourceGroup) {
    return AzureRelay.builder()
        .withId("relay-fractal-cloud-demo")
        .withName("relay-fractal-cloud-demo")
        .withRegion(resourceGroup.getRegion())
        .withAzureResourceGroup(resourceGroup)
        .build();
  }
}
