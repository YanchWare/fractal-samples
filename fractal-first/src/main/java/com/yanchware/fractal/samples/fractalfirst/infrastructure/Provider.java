package com.yanchware.fractal.samples.fractalfirst.infrastructure;

import com.yanchware.fractal.samples.fractalfirst.infrastructure.azure.AzureContainerizedThreeTiers;
import com.yanchware.fractal.samples.fractalfirst.infrastructure.oci.OciContainerizedThreeTiers;

public class Provider {
    public static ThreeTierApplication getThreeTierApplication() {
        return new AzureContainerizedThreeTiers();

        // Uncomment the following line if you want to migrate the whole Fractal to a different Cloud Vendor
        //return new OciContainerizedThreeTiers();
    }
}
