package com.yanchware.fractal.samples.azure.cdn.components;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureIdentityType;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureResourceGroup;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.cdn.AzureCdnProfile;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.cdn.AzureCdnSku;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.cdn.AzureManagedServiceIdentity;

public class CdnComponent {
  public static AzureCdnProfile getMinimalComponent(String id, AzureResourceGroup resourceGroup) {
    return AzureCdnProfile.builder()
        .withId(id)
        .withName(id)
        .withDescription(getDescription(id))
        .withDisplayName(getDisplayName(id))
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withSku(AzureCdnSku.STANDARD_MICROSOFT)
        .build();
  }
  

  public static AzureCdnProfile getFullComponent(String id, AzureResourceGroup resourceGroup) {
    return AzureCdnProfile.builder()
        .withId(id)
        .withName(id)
        .withDescription(getDescription(id))
        .withDisplayName(getDisplayName(id))
        .withResourceGroup(resourceGroup)
        .withRegion(resourceGroup.getRegion())
        .withSku(AzureCdnSku.STANDARD_AZURE_FRONT_DOOR)
        .withIdentity(AzureManagedServiceIdentity.builder()
            .withType(AzureIdentityType.SYSTEM_ASSIGNED)
            .build())
        .withOriginResponseTimeoutSeconds(90)
        .build();
  }

  private static String getDisplayName(String name) {
    return String.format("Azure CDN Profile - %s", name);
  }

  private static String getDescription(String name) {
    return String.format("Azure CDN Profile instance named '%s'", name);
  }
}
