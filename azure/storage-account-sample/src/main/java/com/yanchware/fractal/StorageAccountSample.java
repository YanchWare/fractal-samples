package com.yanchware.fractal;

import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationConfiguration;
import com.yanchware.fractal.sdk.configuration.instantiation.InstantiationWaitConfiguration;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemAggregate;
import com.yanchware.fractal.sdk.domain.livesystem.LiveSystemIdValue;
import com.yanchware.fractal.sharedconfig.SharedConfig;

import java.util.List;

import static com.yanchware.fractal.azure.sample.components.StorageAccountComponent.*;

public class StorageAccountSample {
  public static void main(String[] args) throws InstantiatorException {

    // CONFIGURATION:
    var configuration = SharedConfig.getInstance();

    var instantiationConfig = InstantiationConfiguration.builder().withWaitConfiguration(InstantiationWaitConfiguration.builder()
              .withWaitForInstantiation(true)
              .withTimeoutMinutes(120)
              .build()).build();
    
    // LIVE-SYSTEM INSTANTIATION:
    var automaton = Automaton.getInstance();
    automaton.instantiate(List.of(getLiveSystem(automaton, configuration)), instantiationConfig);
  }

  public static LiveSystemAggregate getLiveSystem(Automaton automaton, SharedConfig configuration) throws InstantiatorException {

    var resourceGroup = configuration.getAzureResourceGroup();
    
    // LIVE-SYSTEM DEFINITION:
    return automaton.getLiveSystemBuilder()
        .withId(new LiveSystemIdValue(configuration.getResourceGroupId().toString(), configuration.getLiveSystemName()))
        .withDescription("Storage account sample")
        .withComponents(List.of(
            getLegacyStorageAccountComponent("stlegacyyw001", resourceGroup),
            getStorageAccountComponent("stv2yw001", resourceGroup),
            getFileStorageAccountComponent("stfileyw001", resourceGroup),
            getBlobStorageAccountComponent("stblobyw001", resourceGroup),
            getBlockBlobStorageAccountComponent("stblockblobyw001", resourceGroup)))
        .withEnvironmentId(configuration.getEnvironment().getManagementEnvironment().getId())
        .build();
  }
}