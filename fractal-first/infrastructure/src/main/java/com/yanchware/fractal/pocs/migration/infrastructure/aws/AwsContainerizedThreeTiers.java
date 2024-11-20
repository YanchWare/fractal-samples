package com.yanchware.fractal.pocs.migration.infrastructure.aws;

import com.yanchware.fractal.pocs.migration.infrastructure.agnostic.ContainerizedThreeTiers;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsElasticKubernetesService;

public class AwsContainerizedThreeTiers extends ContainerizedThreeTiers<AwsElasticKubernetesService, AwsElasticKubernetesService.AwsElasticKubernetesServiceBuilder> {
    public AwsContainerizedThreeTiers(String liveSystemName) {
        super(EksComponent.getComponentBuilder(liveSystemName), liveSystemName);
    }
}
