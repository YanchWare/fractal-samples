package com.yanchware.fractal.pocs.migration.infrastructure.aws;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsElasticKubernetesService;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.aws.AwsRegion;

public class EksComponent {
    public static AwsElasticKubernetesService.AwsElasticKubernetesServiceBuilder getComponentBuilder(String liveSystemName)
    {
        var okeName = String.format("%s-hosting", liveSystemName);

        return AwsElasticKubernetesService.builder()
                .withId(okeName)
                .withDisplayName(okeName)
                .withRegion(AwsRegion.EU_NORTH_1);
    }
}
