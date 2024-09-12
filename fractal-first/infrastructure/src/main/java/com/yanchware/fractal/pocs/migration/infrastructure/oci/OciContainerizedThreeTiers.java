package com.yanchware.fractal.pocs.migration.infrastructure.oci;

import com.yanchware.fractal.pocs.migration.infrastructure.agnostic.ContainerizedThreeTiers;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes.OciContainerEngineForKubernetesBuilder;

public class OciContainerizedThreeTiers extends ContainerizedThreeTiers<OciContainerEngineForKubernetes, OciContainerEngineForKubernetesBuilder> {
    public OciContainerizedThreeTiers(String liveSystemName) {
        super(OkeComponent.getComponentBuilder(
                Compartment.builder()
                        .withName(liveSystemName)
                        .build(), liveSystemName), liveSystemName);
    }
}
