package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.AzureServiceBus;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.valueobjects.ServiceBusSku;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.valueobjects.ServiceBusSkuTier;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion.EUROPE_WEST;

public class AzServiceBus {
  public static AzureServiceBus getServiceBus() {
    AzureResourceGroup azureResourceGroup = AzureResourceGroup
        .builder()
        .withName("rg-service-bus")
        .withRegion(EUROPE_WEST)
        .build();
    return AzureServiceBus.builder()
        .withId("sb-sample")
        .withName("sb-sample")
        .withRegion(AzureRegion.EUROPE_WEST)
        .withAzureResourceGroup(azureResourceGroup)
        .withSku(ServiceBusSku.builder()
            .withTier(ServiceBusSkuTier.BASIC)
            .build())
        .build();
  }
}
