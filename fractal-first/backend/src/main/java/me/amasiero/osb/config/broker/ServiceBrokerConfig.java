package me.amasiero.osb.config.broker;

import org.springframework.cloud.servicebroker.model.catalog.Catalog;
import org.springframework.cloud.servicebroker.model.catalog.Plan;
import org.springframework.cloud.servicebroker.model.catalog.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBrokerConfig {

    @Bean
    public Catalog catalog() {
        return Catalog.builder()
                      .serviceDefinitions(
                          ServiceDefinition.builder()
                                           .id("cc4096cb-eaf8-410e-a590-4a113cb86a41")
                                           .name("aws-s3-service")
                                           .description("A service to create AWS S3 buckets")
                                           .bindable(true)
                                           .plans(
                                               Plan.builder()
                                                   .id("f01d9b10-c70e-4136-a6dd-22b9c95e67dd")
                                                   .name("default")
                                                   .description("Default plan for AWS S3 service")
                                                   .build()
                                           )
                                           .tags("aws", "s3", "storage")
                                           .metadata("displayName", "AWS S3 Service")
                                           .metadata("longDescription", "A service to provision AWS S3 buckets for storage")
                                           .metadata("providerDisplayName", "ACME Cloud")
                                           .metadata("documentationUrl", "https://aws.amazon.com/s3/")
                                           .build()
                      )
                      .build();
    }
}
