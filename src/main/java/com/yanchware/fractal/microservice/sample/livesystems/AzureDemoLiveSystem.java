package com.yanchware.fractal.microservice.sample.livesystems;

import com.yanchware.fractal.microservice.sample.components.AmbassadorWorkload;
import com.yanchware.fractal.microservice.sample.components.SampleMicroserviceWorkload;
import com.yanchware.fractal.microservice.sample.components.azure.PostgreSqlDatabase;
import com.yanchware.fractal.microservice.sample.configuration.Configuration;
import com.yanchware.fractal.microservice.sample.components.azure.AksCluster;
import com.yanchware.fractal.microservice.sample.components.azure.PostgreSqlDbms;
import com.yanchware.fractal.microservice.sample.environments.AzureEnvironment;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;

import java.util.List;

public class AzureDemoLiveSystem {

  public static void Instantiate(Configuration configuration) {

    // ENVIRONMENT:
    var environment= AzureEnvironment.getEnvironment(configuration);

    // BLUEPRINTS:
    var azureAks = AksCluster.getBuilder()
        .withAPIGateway(AmbassadorWorkload.build())
        .withK8sWorkload(SampleMicroserviceWorkload.build())
        .build();

    var postgreSQLDbms = PostgreSqlDbms.getBuilder()
        .withDatabase(PostgreSqlDatabase.build())
        .build();

    // INSTANTIATION:
    LiveSystem azureLiveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Fractal Azure demo")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponents(List.of(azureAks, postgreSQLDbms))
        .withEnvironment(environment)
        . build();
  }

}
