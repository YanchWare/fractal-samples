package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.AzureServiceBus;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.AzureServiceBusQueue;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.AzureServiceBusTopic;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.servicebus.valueobjects.*;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.storageaccount.valueobjects.AzureIdentityType;

import java.time.Duration;
import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureRegion.EUROPE_WEST;

public class AzServiceBus {
  public static AzureServiceBus getServiceBus() {
    AzureResourceGroup azureResourceGroup = AzureResourceGroup
        .builder()
        .withName("rg-service-bus")
        .withRegion(EUROPE_WEST)
        .build();
    
    return AzureServiceBus.builder()
        .withId("sb-full-sample")
        .withName("sb-full-sample")
        .withRegion(AzureRegion.EUROPE_WEST)
        .withAzureResourceGroup(azureResourceGroup)
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
