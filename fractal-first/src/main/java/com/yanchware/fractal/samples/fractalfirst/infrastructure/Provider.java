package com.yanchware.fractal.samples.fractalfirst.infrastructure;

import com.yanchware.fractal.samples.fractalfirst.infrastructure.azure.AzureContainerizedThreeTiers;
import com.yanchware.fractal.samples.fractalfirst.infrastructure.oci.OciContainerizedThreeTiers;

public class Provider {
    public static ThreeTierApplication getThreeTierApplication() {
        return new AzureContainerizedThreeTiers();
        //return new OciContainerizedThreeTiers();
    }
}
