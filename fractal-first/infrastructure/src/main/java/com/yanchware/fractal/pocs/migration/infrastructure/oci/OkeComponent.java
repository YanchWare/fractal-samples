package com.yanchware.fractal.pocs.migration.infrastructure.oci;

import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciRegion;

public class OkeComponent {
    public static OciContainerEngineForKubernetes.OciContainerEngineForKubernetesBuilder getComponentBuilder(Compartment compartment, String liveSystemName)
    {
        var okeName = String.format("%s-hosting", liveSystemName);

        return OciContainerEngineForKubernetes.builder()
                .withId(okeName)
                .withDisplayName(okeName)
                .withRegion(OciRegion.EU_ZURICH_1)
                .withCompartment(compartment);
    }
}
