package com.yanchware.fractal.microservice.sample.livesystems;

import com.yanchware.fractal.microservice.sample.components.AmbassadorWorkload;
import com.yanchware.fractal.microservice.sample.components.SampleMicroserviceWorkload;
import com.yanchware.fractal.microservice.sample.configuration.Configuration;
import com.yanchware.fractal.microservice.sample.components.PostgreSqlDatabase;
import com.yanchware.fractal.microservice.sample.components.gcp.GkeCluster;
import com.yanchware.fractal.microservice.sample.components.gcp.PostgreSqlDbms;
import com.yanchware.fractal.microservice.sample.environments.GcpEnvironment;
import com.yanchware.fractal.sdk.aggregates.LiveSystem;

import java.util.List;

public class GcpDemoLiveSystem {

  public static void Instantiate(Configuration configuration) {

    // ENVIRONMENT:
    var environment = GcpEnvironment.getEnvironment(configuration);

    // BLUEPRINTS:
    var gke = GkeCluster.getBuilder()
        .withAPIGateway(AmbassadorWorkload.build())
        .withK8sWorkload(SampleMicroserviceWorkload.build())
        .build();

    var postgreSQLDbms = PostgreSqlDbms.getBuilder()
        .withDatabase(PostgreSqlDatabase.build())
        .build();

    // INSTANTIATION:
    LiveSystem liveSystem = LiveSystem.builder()
        .withName(configuration.getLiveSystemName())
        .withDescription("Fractal Gcp demo")
        .withResourceGroupId(configuration.getResourceGroupId())
        .withComponents(List.of(gke, postgreSQLDbms))
        .withEnvironment(environment)
        . build();
  }

}
