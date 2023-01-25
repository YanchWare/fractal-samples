package com.yanchware.fractal.azure.sample.components;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.storageaccount.AzureStorageAccount;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.storageaccount.AzureStorageAccountBackup;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.storageaccount.AzureStorageAccountInfrastructure;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.azure.storageaccount.valueobjects.AzureStorageSkuName;

public class StorageAccountComponent {

  public static AzureStorageAccount getStorageAccountComponent(String id) {

    // Backup policy definition:

    return AzureStorageAccount.builder()
        .withId(id)
        .withInfrastructure(
            AzureStorageAccountInfrastructure.builder()
                .withSku(AzureStorageSkuName.PREMIUM_LRS)
                .build()
        )
        .withBackup(AzureStorageAccountBackup.builder()
            .withPolicy(getBackupPolicy())
            .build()
        )
        .build();
  }
  
  private static String getBackupPolicy() {
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
