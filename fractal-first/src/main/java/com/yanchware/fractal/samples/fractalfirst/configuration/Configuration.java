package com.yanchware.fractal.samples.fractalfirst.configuration;

import java.util.UUID;

public interface Configuration {
  UUID getEnvironmentOwnerId();
  String getEnvironmentShortName();
  String getFractalCloudResourceGroupId();

  String getHelloWorldCustomWorkloadRepositoryName();
  String getHelloWorldCustomWorkloadGitUri();

  String getFractalDeployerSshKeySecretValue();
  String getFractalDeployerSshKeyPassphraseSecretValue();
}
