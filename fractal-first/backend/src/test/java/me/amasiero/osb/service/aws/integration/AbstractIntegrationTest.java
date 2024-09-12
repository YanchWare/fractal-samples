package me.amasiero.osb.service.aws.integration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.s3.S3Client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractIntegrationTest {

    @Container
    static final LocalStackContainer localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"))
        .withServices(LocalStackContainer.Service.S3, LocalStackContainer.Service.IAM);

    @BeforeAll
    static void startContainer() {
        localstack.start();
    }

    @DynamicPropertySource
    static void registerLocalstackProperties(DynamicPropertyRegistry registry) {
        registry.add("aws.s3.endpoint", () -> localstack.getEndpointOverride(LocalStackContainer.Service.S3).toString());
        registry.add("aws.iam.endpoint", () -> localstack.getEndpointOverride(LocalStackContainer.Service.IAM).toString());
        registry.add("aws.accessKey", localstack::getAccessKey);
        registry.add("aws.secretKey", localstack::getSecretKey);
        registry.add("aws.region", localstack::getRegion);
    }

    @Configuration
    @Profile("test")
    static class TestConfig {

        @Bean
        @Primary
        public S3Client s3Client() {
            return S3Client.builder()
                           .endpointOverride(localstack.getEndpointOverride(LocalStackContainer.Service.S3))
                           .credentialsProvider(StaticCredentialsProvider.create(
                               AwsBasicCredentials.create(localstack.getAccessKey(), localstack.getSecretKey())))
                           .region(Region.of(localstack.getRegion()))
                           .build();
        }

        @Bean
        @Primary
        public IamClient iamClient() {
            return IamClient.builder()
                            .endpointOverride(localstack.getEndpointOverride(LocalStackContainer.Service.IAM))
                            .credentialsProvider(StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(localstack.getAccessKey(), localstack.getSecretKey())))
                            .region(Region.of(localstack.getRegion()))
                            .build();
        }
    }
}

