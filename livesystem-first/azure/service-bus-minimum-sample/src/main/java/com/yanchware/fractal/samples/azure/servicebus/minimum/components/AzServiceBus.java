package com.yanchware.fractal.samples.azure.servicebus.minimum.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.AzureServiceBus;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.valueobjects.ServiceBusSku;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.valueobjects.ServiceBusSkuTier;


public class AzServiceBus {
  public static AzureServiceBus getServiceBus(AzureResourceGroup resourceGroup) {

    return AzureServiceBus.builder()
        .withId("sb-minimum-sample")
        .withName("sb-minimum-sample")
        .withRegion(resourceGroup.getRegion())
        .withAzureResourceGroup(resourceGroup)
        .withSku(ServiceBusSku.builder()
            .withTier(ServiceBusSkuTier.BASIC)
            .build())
        .build();
  }
}
