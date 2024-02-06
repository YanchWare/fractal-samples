package com.yanchware.fractal;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.azure.sample.components.StorageAccountComponent.*;

public class StorageAccountSample {
  public static void main(String[] args) throws InstantiatorException {

    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = new InstantiationConfiguration() {{
      setWaitConfiguration(new InstantiationWaitConfiguration() {{
        setWaitForInstantiation(true);
        setTimeoutMinutes(120);
      }});
    }};
    
    // LIVE-SYSTEM INSTANTIATION:
    Automaton.instantiate(List.of(getLiveSystem(configuration)), instantiationConfig);
  }

  public static LiveSystem getLiveSystem(SharedConfig configuration) {

    var resourceGroup = configuration.getAzureResourceGroup();
    
    // LIVE-SYSTEM DEFINITION:
    return LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Storage account sample")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponents(List.of(
            getLegacyStorageAccountComponent("stlegacyyw001", resourceGroup),
            getStorageAccountComponent("stv2yw001", resourceGroup),
            getFileStorageAccountComponent("stfileyw001", resourceGroup),
            getBlobStorageAccountComponent("stblobyw001", resourceGroup),
            getBlockBlobStorageAccountComponent("stblockblobyw001", resourceGroup)))
        .withEnvironment(configuration.getEnvironment())
        .build();
  }
}