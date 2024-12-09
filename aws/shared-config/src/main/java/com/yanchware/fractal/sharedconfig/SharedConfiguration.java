package com.yanchware.fractal.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

import java.util.UUID;

public interface SharedConfiguration {
  String getFractalCloudResourceGroupId();
  String getOrganizationId();
  String getAccountId();
  EnvironmentAggregate getEnvironment(AwsRegion region) throws InstantiatorException;
}
