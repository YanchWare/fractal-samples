package com.yanchware.fractal.samples.environment.intialization.operational.configuration;

import com.yanchware.fractal.sdk.domain.environment.CiCdProfile;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.environment.Secret;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;

import java.util.UUID;

public interface Configuration {
  String getAwsAccountId();
  String getAwsOrganizationId();
  AwsRegion getAwsRegion();
  AzureRegion getAzureRegion();
  UUID getAzureSubscriptionId();
  UUID getAzureTenantId();
  UUID getEnvironmentOwnerId();
  EnvironmentIdValue getManagementEnvironmentId();
  String getManagementEnvironmentName();
  String getManagementEnvironmentShortName();
  EnvironmentType getManagementEnvironmentType();
  AzureRegion getOperationalEnvironmentAzureRegion();
  UUID getOperationalEnvironmentAzureSubscriptionId();
  CiCdProfile getOperationalEnvironmentDefaultCiCdProfile();
  String getOperationalEnvironmentShortName();
  String getOperationalEnvironmentName();
  UUID getOperationalEnvironmentResourceGroup();
  Secret getOperationalEnvironmentSecret();
  UUID getResourceGroupId();
}
