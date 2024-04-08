package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.storageaccount.*;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.storageaccount.valueobjects.AzureStorageAccountSkuName;

public class StorageAccountComponent {
  public static AzureLegacyStorageAccount getLegacyStorageAccountComponent(String id,
                                                                           AzureResourceGroup resourceGroup) {
    return AzureLegacyStorageAccount.builder()
        .withId(id)
        .withName(id)
        .withDescription(getDescription(id, "Legacy"))
        .withDisplayName(getDisplayName(id, "Legacy"))
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withFileShare(AzureFileShare.builder()
            .withId("share-yanchware-legacy")
            .withName("share-yanchware-legacy")
            .withDisplayName("YanchWare Azure File Share Legacy")
            .withDescription("YanchWare Azure File Share on Legacy Storage Account")
            .build())
        .build();
  }

  public static AzureStorageAccount getStorageAccountComponent(String id, AzureResourceGroup resourceGroup) {
    return AzureStorageAccount.builder()
        .withId(id)
        .withName(id)
        .withDescription(getDescription(id, "V2"))
        .withDisplayName(getDisplayName(id, "V2"))
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withBackup(AzureStorageAccountBackup.builder()
            .withPolicy(getBackupPolicy())
            .withPolicyName("bkpol-fractal-cloud-demo")
            .withPolicyResourceGroupName(resourceGroup.getName())
            .withVaultName("rsv-fractal-cloud-demo")
            .withVaultResourceGroupName(resourceGroup.getName())
            .build())
        .build();
  }

  public static AzureFileStorageAccount getFileStorageAccountComponent(String id, AzureResourceGroup resourceGroup) {
    return AzureFileStorageAccount.builder()
        .withId(id)
        .withName(id)
        .withDescription(getDescription(id, "File"))
        .withDisplayName(getDisplayName(id, "File"))
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withSku(AzureStorageAccountSkuName.PREMIUM_LRS)
        .withFileShare(AzureFileShare.builder()
            .withId("share-yanchware")
            .withName("share-yanchware")
            .withDisplayName("YanchWare Azure File Share")
            .withDescription("YanchWare Azure FileS hare on File Storage Account")
            .build())
        .build();
  }

  public static AzureBlobStorageAccount getBlobStorageAccountComponent(String id, AzureResourceGroup resourceGroup) {
    var description = getDescription(id, "Blob");
    var displayName = getDisplayName(id, "Blob");
    var containerName = "container";
    
    return AzureBlobStorageAccount.builder()
        .withId(id)
        .withName(id)
        .withDescription(description)
        .withDisplayName(displayName)
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withContainer(AzureBlobContainer.builder()
            .withId(String.format("%s-%s", id, containerName))
            .withName(containerName)
            .withDescription(String.format("%s - %s", description, containerName))
            .withDisplayName(String.format("%s - %s", displayName, containerName))
            .build())
        .build();
  }

  public static AzureBlockBlobStorageAccount getBlockBlobStorageAccountComponent(String id, AzureResourceGroup resourceGroup) {
    var description = getDescription(id, "BlockBlob");
    var displayName = getDisplayName(id, "BlockBlob");
    var containerName = "container";
    
    return AzureBlockBlobStorageAccount.builder()
        .withId(id)
        .withName(id)
        .withDescription(getDescription(id, "BlockBlob"))
        .withDisplayName(getDisplayName(id, "BlockBlob"))
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withContainer(AzureBlobContainer.builder()
            .withId(String.format("%s-%s", id, containerName))
            .withName(containerName)
            .withDescription(String.format("%s - %s", description, containerName))
            .withDisplayName(String.format("%s - %s", displayName, containerName))
            .build())
        .build();
  }

  private static String getDisplayName(String name, String type) {
    return String.format("Azure %s Storage Account - %s", type, name);
  }

  private static String getDescription(String name, String type) {
    return String.format("Azure %s Storage Account instance named '%s'", type, name);
  }

  private static String getBackupPolicy() {

    // Backup policy definition:
    return """
            {
              "workLoadType": "AzureFileShare",
              "backupManagementType": "AzureStorage",
              "timeZone": "UTC",
              "schedulePolicy":
                {
                  "schedulePolicyType": "SimpleSchedulePolicy",
                  "scheduleRunFrequency": "Daily",
                  "scheduleRunTimes": ["2018-01-24T01:00:00.000000000Z"]
                },
              "retentionPolicy":
                {
                  "retentionPolicyType": "LongTermRetentionPolicy",
                  "dailySchedule":
                    {
                      "retentionDuration": { "count": 30, "durationType": "Days" },
                      "retentionTimes": ["2018-01-24T01:00:00.000000000Z"]
                    },
                  "weeklySchedule": null,
                  "monthlySchedule":
                    {
                      "retentionScheduleFormatType": "Daily",
                      "retentionScheduleDaily":
                        { "daysOfTheMonth": [{ "date": 1, "isLast": false }] },
                      "retentionDuration": { "count": 12, "durationType": "Months" },
                      "retentionScheduleWeekly": null,
                      "retentionTimes": ["2018-01-24T01:00:00.000000000Z"]
                    },
                  "yearlySchedule": null
                }
            }
        """;
  }

}
