package com.yanchware.fractal.pocs.migration.infrastructure.oci;

import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciRegion;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes.OciContainerEngineForKubernetesBuilder;

public class OkeComponent {
    public static OciContainerEngineForKubernetesBuilder getComponentBuilder(Compartment compartment, String liveSystemName)
    {
        var okeName = String.format("%s-hosting", liveSystemName);

        return OciContainerEngineForKubernetes.builder()
                .withId(okeName)
                .withDisplayName(okeName)
                .withRegion(OciRegion.EU_ZURICH_1)
                .withCompartment(compartment);
    }
}
