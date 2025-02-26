package com.yanchware.fractal.samples.environment.initialization.configuration;

import com.yanchware.fractal.sdk.domain.environment.CiCdProfile;
import com.yanchware.fractal.sdk.domain.environment.Secret;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;

import java.util.UUID;

public interface Configuration {
  UUID getAzureTenantId();
  UUID getAzureSubscriptionId();
  String getAwsOrganizationId();
  String getAwsAccountId();
  UUID getEnvironmentOwnerId();
  UUID getResourceGroupId();
  UUID getOperationalEnvironmentAzureSubscriptionId();
  String getOperationalEnvironmentShortName();
  String getOperationalEnvironmentName();
  UUID getOperationalEnvironmentResourceGroup();
  AzureRegion getOperationalEnvironmentAzureRegion();
  CiCdProfile getOperationalEnvironmentDefaultCiCdProfile();
  Secret getOperationalEnvironmentSecret();
}
