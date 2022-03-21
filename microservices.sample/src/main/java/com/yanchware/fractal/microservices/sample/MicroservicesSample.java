package com.yanchware.fractal.microservices.sample;

import com.yanchware.fractal.microservices.sample.configuration.EnvVarConfiguration;
import com.yanchware.fractal.sdk.Automaton;
import com.yanchware.fractal.sdk.aggregates.Environment;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.Ambassador;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.PostgreSQLDB;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureKubernetesService;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureNodePool;
import com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzurePostgreSQL;
import com.yanchware.fractal.sdk.domain.exceptions.InstantiatorException;
import com.yanchware.fractal.sdk.valueobjects.ComponentId;

import java.util.List;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureMachineType.STANDARD_B2S;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureRegion.EUROPE_WEST;
import static com.yanchware.fractal.sdk.domain.entities.livesystem.caas.providers.azure.AzureSkuName.B_GEN5_1;

public class MicroservicesSample {
  public static void main (String[] args) throws InstantiatorException {

    var configuration = EnvVarConfiguration.getInstance();

    var env = Environment.builder()
      .withId(configuration.getSubscriptionId())
      .withDisplayName(configuration.getEnvironmentDisplayName())
      .withParentId(configuration.getTenantId())
      .withParentType("tenant")
      .build();


    var aks = AzureKubernetesService.builder()
      .withId(ComponentId.from("aks-1"))
      .withDescription("Microservices AKS cluster")
      .withDisplayName("AKS #1")
      .withRegion(EUROPE_WEST)

      .withNetwork("network-host")
        .withSubNetwork("compute-tier-1")
        .withPodsRange("tier-1-pods")
        .withServiceRange("tier-1-services")

      .withAPIGateway(Ambassador.builder()
        //TODO
        .build())

      .withNodePool(AzureNodePool.builder()
        .withDiskSizeGb(300)
        .withInitialNodeCount(1)
        .withMachineType(STANDARD_B2S)
        .withMaxNodeCount(9)
        .withMaxPodsPerNode(100)
        .withMaxSurge(1)
        .withMinNodeCount(1)
        .withName("systempool")
        .build())
      .build();

      var postgresDb = PostgreSQLDB.builder()
        .withId(ComponentId.from("svc-acl-erp"))
        .withDisplayName("PostgreSQL Database")
        .withDescription("PostgreSQL Database for Demo")
        .withDisplayName("svc-acl-erp")
        .build();

      var postgresDbms = AzurePostgreSQL.builder()
        .withId(ComponentId.from("core-rdbms-2"))
        .withDescription("PostgreSQL DBMS.")
        .withDisplayName("PostgreSQL #2")
        .withRegion(EUROPE_WEST)
        .withDatabase(postgresDb)
        .withSkuName(B_GEN5_1)
      .build();

    LiveSystem liveSystem = LiveSystem.builder()
      .withName(configuration.getLiveSystemName())
      .withDescription("Microservices Sample Fractal")
      .withResourceGroupId(configuration.getResourceGroupId())
      .withComponents(List.of(aks, postgresDbms))
      .withEnvironment(env)
      .build();

    Automaton.instantiate(List.of(liveSystem));
  }
}