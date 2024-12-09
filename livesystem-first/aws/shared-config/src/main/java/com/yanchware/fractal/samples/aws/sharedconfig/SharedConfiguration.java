package com.yanchware.fractal.samples.aws.sharedconfig;

import com.yanchware.fractal.sdk.domain.environment.EnvironmentAggregate;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public interface SharedConfiguration {
  String getFractalCloudResourceGroupId();
  String getOrganizationId();
  String getAccountId();
  EnvironmentAggregate getEnvironment(AwsRegion region) throws InstantiatorException;
}
