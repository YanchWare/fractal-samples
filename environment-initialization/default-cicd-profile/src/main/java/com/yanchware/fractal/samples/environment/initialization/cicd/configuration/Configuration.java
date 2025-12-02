package com.yanchware.fractal.samples.environment.initialization.cicd.configuration;

import com.yanchware.fractal.sdk.domain.environment.CiCdProfile;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentIdValue;
import com.yanchware.fractal.sdk.domain.environment.EnvironmentType;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.azure.AzureRegion;
import com.yanchware.fractal.sdk.domain.values.ResourceGroupId;

import java.util.UUID;

public interface Configuration {
  String getAwsAccountId();
  String getAwsOrganizationId();
  AwsRegion getAwsRegion();
  AzureRegion getAzureRegion();
  UUID getAzureSubscriptionId();
  UUID getAzureTenantId();
  CiCdProfile getDefaultCiCdProfile();
  UUID getEnvironmentOwnerId();
  EnvironmentIdValue getManagementEnvironmentId();
  String getManagementEnvironmentName();
  String getManagementEnvironmentShortName();
  EnvironmentType getManagementEnvironmentType();
  ResourceGroupId getResourceGroupId();
}
