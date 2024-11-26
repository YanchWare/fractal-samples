package com.yanchware.fractal.pocs.migration.infrastructure.oci;

import com.yanchware.fractal.pocs.migration.infrastructure.agnostic.ContainerizedThreeTiers;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.Compartment;
import com.yanchware.fractal.sdk.domain.livesystem.paas.providers.oci.OciContainerEngineForKubernetes;
import com.yanchware.fractal.sdk.domain.livesystem.service.dtos.ProviderType;

public class OciContainerizedThreeTiers extends ContainerizedThreeTiers<OciContainerEngineForKubernetes, OciContainerEngineForKubernetes.OciContainerEngineForKubernetesBuilder> {
    public OciContainerizedThreeTiers(String liveSystemName) {
        super(OkeComponent.getComponentBuilder(
                Compartment.builder()
                        .withName(liveSystemName)
                        .build(), liveSystemName), liveSystemName, ProviderType.OCI);
    }
}
