package com.yanchware.fractal.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.AzureServiceBus;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.valueobjects.ServiceBusSku;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.valueobjects.ServiceBusSkuTier;

public class AzServiceBus {
  public static AzureServiceBus getServiceBus(String componentName, AzureResourceGroup resourceGroup) {

    return AzureServiceBus.builder()
        .withId(componentName)
        .withName(componentName)
        .withRegion(resourceGroup.getRegion())
        .withAzureResourceGroup(resourceGroup)
        .withSku(ServiceBusSku.builder()
            .withTier(ServiceBusSkuTier.BASIC)
            .build())
        .build();
  }
}
