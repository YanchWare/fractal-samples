package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

import java.util.UUID;

public interface SharedConfiguration {
  String getLiveSystemName();
  UUID getResourceGroupId();
  AwsRegion getAwsRegion();
  String getOrganizationId();
  String getAccountId();
  EnvironmentAggregate getEnvironment() throws InstantiatorException;
}
