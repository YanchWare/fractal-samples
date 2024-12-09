package com.yanchware.fractal.pocs.migration.infrastructure;

import com.yanchware.fractal.pocs.migration.infrastructure.aws.AwsContainerizedThreeTiers;
import com.yanchware.fractal.pocs.migration.infrastructure.azure.AzureContainerizedThreeTiers;

public class Provider {
    public static ThreeTierApplication getThreeTierApplication(String liveSystemName) {
//        return new AzureContainerizedThreeTiers(liveSystemName);

        // Uncomment the following line if you want to migrate the whole Fractal to a different Cloud Vendor
        // return new OciContainerizedThreeTiers(liveSystemName);
         return new AwsContainerizedThreeTiers(liveSystemName);
    }
}
