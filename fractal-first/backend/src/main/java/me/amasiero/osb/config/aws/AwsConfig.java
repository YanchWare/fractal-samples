package me.amasiero.osb.config.aws;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static io.micrometer.common.util.StringUtils.isNotBlank;

@Configuration
@Profile("!test")
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsProperties.class)
public class AwsConfig {
    private final AwsProperties awsProperties;

    @Bean
    public S3Client s3Client() {
        S3ClientBuilder s3ClientBuilder = S3Client.builder()
                                                  .region(Region.of(awsProperties.getRegion()));
        if (isNotBlank(awsProperties.getProfile())) {
            return s3ClientBuilder
                .credentialsProvider(ProfileCredentialsProvider.create(awsProperties.getProfile()))
                .build();
        }

        if (isNotBlank(awsProperties.getAccessKeyId()) && isNotBlank(awsProperties.getSecretKey())) {
            AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(awsProperties.getAccessKeyId(),
                awsProperties.getSecretKey());
            return s3ClientBuilder
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
        }

        return s3ClientBuilder.build();

    }

    @Bean
    public IamClient iamClient() {
        return IamClient.builder().build();
    }
}
