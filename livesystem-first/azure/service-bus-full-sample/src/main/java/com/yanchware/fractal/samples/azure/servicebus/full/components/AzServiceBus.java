package com.yanchware.fractal.samples.azure.servicebus.full.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureIdentityType;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.AzureServiceBus;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.AzureServiceBusQueue;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.AzureServiceBusTopic;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.servicebus.valueobjects.*;

import java.time.Duration;
import java.util.List;


public class AzServiceBus {
  public static AzureServiceBus getServiceBus(AzureResourceGroup resourceGroup) {
    return AzureServiceBus.builder()
        .withId("sb-full-sample")
        .withName("sb-full-sample")
        .withRegion(resourceGroup.getRegion())
        .withAzureResourceGroup(resourceGroup)
        .withSku(ServiceBusSku.builder()
            .withCapacity(1)
            .withTier(ServiceBusSkuTier.BASIC)
            .build())
        .withEncryption(Encryption.builder()
            .withKeySource(KeySource.MICROSOFT_KEYVAULT)
            .withRequireInfrastructureEncryption(Boolean.TRUE)
            .withKeyVaultProperties(List.of(KeyVaultProperties.builder()
                .withIdentity("identity")
                .withKeyName("keyName")
                .withKeyVaultUri("keyVaultUri")
                .withKeyVersion("keyVaultVersion")
                .build()))
            .build())
        .withIdentity(AzureIdentityType.SYSTEM_ASSIGNED)
        .withDisableLocalAuth(null)
        .withZoneRedundant(Boolean.FALSE)
        .withQueue(AzureServiceBusQueue.builder()
            .withId("queue-1")
            .withAutoDeleteOnIdle(Duration.ofMinutes(1L))
            .withDeadLetteringOnMessageExpiration(false)
            .withDefaultMessageTimeToLive(Duration.ofMinutes(2))
            .withDuplicateDetectionHistoryTimeWindow(Duration.ofSeconds(21))
            .withEnableExpress(false)
            .withEnableBatchedOperations(true)
            .withEnablePartitioning(false)
            .withForwardTo("someRandomString")
            .withForwardDeadLetteredMessagesTo("differentRandomString")
            .withLockDuration(Duration.ofMinutes(5))
            .withMaxDeliveryCount(12)
            .withMaxSizeInMegabytes(22)
            .withMaxMessageSizeInKilobytes(300L)
            .withRequiresSession(false)
            .withRequiresDuplicateDetection(true)
            .build())
        .withTopic(AzureServiceBusTopic.builder()
            .withId("topic-1")
            .withAutoDeleteOnIdle(Duration.ofMinutes(2L))
            .withDefaultMessageTimeToLive(Duration.ofMinutes(3))
            .withDuplicateDetectionHistoryTimeWindow(Duration.ofSeconds(33))
            .withEnableExpress(false)
            .withEnableBatchedOperations(true)
            .withEnablePartitioning(false)
            .withMaxSizeInMegabytes(124)
            .withMaxMessageSizeInKilobytes(500L)
            .withRequiresDuplicateDetection(true)
            .withSupportOrdering(false)
            .build())
        .build();
  }
}
