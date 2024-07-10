package com.yanchware.fractal.samples.fractalfirst.infrastructure.oci;

import com.yanchware.fractal.samples.fractalfirst.infrastructure.agnostic.ContainerizedThreeTiers;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;

import static com.yanchware.fractal.sdk.domain.entities.livesystem.paas.providers.oci.OciContainerEngineForKubernetes.OciContainerEngineForKubernetesBuilder;

public class OciContainerizedThreeTiers extends ContainerizedThreeTiers<OciContainerEngineForKubernetes, OciContainerEngineForKubernetesBuilder> {
    public OciContainerizedThreeTiers() {
        super(OkeComponent.getComponentBuilder(
                Compartment.builder()
                        .withName("fractal-migration-oci-1")
                        .build()), "oci-migration-livesystem");
    }
}
