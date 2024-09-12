package me.amasiero.osb.service.aws.integration;

import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import software.amazon.awssdk.services.iam.model.GetUserRequest;
import software.amazon.awssdk.services.iam.model.GetUserResponse;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.servicebroker.model.binding.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.binding.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.binding.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.binding.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.binding.DeleteServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.binding.GetServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.binding.GetServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.binding.GetServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceResponse;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.amasiero.osb.config.aws.AwsProperties;
import me.amasiero.osb.service.aws.AwsInstanceService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Import(AbstractIntegrationTest.TestConfig.class)
public class AwsInstanceServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AwsInstanceService awsInstanceService;

    @Autowired
    private AwsProperties awsProperties;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private IamClient iamClient;

    @BeforeEach
    void setUp() {
        awsProperties.setRegion(localstack.getRegion());
    }

    @Nested
    @DisplayName("Create Service Instance Tests")
    class CreateServiceInstanceTests {

        @Test
        @DisplayName("Should Create Service Instance Successfully")
        void shouldCreateServiceInstanceSuccessfully() {
            String serviceInstanceId = "test-instance";
            CreateServiceInstanceRequest request = CreateServiceInstanceRequest.builder()
                                                                               .serviceInstanceId(serviceInstanceId)
                                                                               .build();

            Mono<CreateServiceInstanceResponse> response = awsInstanceService.createServiceInstance(request);

            StepVerifier.create(response)
                        .expectNextMatches(r -> r.getDashboardUrl().contains(serviceInstanceId) && r.isAsync())
                        .verifyComplete();

            HeadBucketRequest headBucketRequest = HeadBucketRequest.builder().bucket(serviceInstanceId).build();
            assertDoesNotThrow(() -> s3Client.headBucket(headBucketRequest));
        }
    }

    @Nested
    @DisplayName("Get Service Instance Tests")
    class GetServiceInstanceTests {

        @Test
        @DisplayName("Should Get Service Instance Successfully")
        void shouldGetServiceInstanceSuccessfully() {
            String serviceInstanceId = "test-instance";
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket(serviceInstanceId).build();
            s3Client.createBucket(createBucketRequest);

            GetServiceInstanceRequest request = GetServiceInstanceRequest.builder()
                                                                         .serviceInstanceId(serviceInstanceId)
                                                                         .build();

            Mono<GetServiceInstanceResponse> response = awsInstanceService.getServiceInstance(request);

            StepVerifier.create(response)
                        .expectNextMatches(r -> r.getParameters().containsValue(serviceInstanceId))
                        .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Delete Service Instance Tests")
    class DeleteServiceInstanceTests {

        @Test
        @DisplayName("Should Delete Service Instance Successfully")
        void shouldDeleteServiceInstanceSuccessfully() {
            String serviceInstanceId = "test-instance";
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket(serviceInstanceId).build();
            s3Client.createBucket(createBucketRequest);

            DeleteServiceInstanceRequest request = DeleteServiceInstanceRequest.builder()
                                                                               .serviceInstanceId(serviceInstanceId)
                                                                               .build();

            Mono<DeleteServiceInstanceResponse> response = awsInstanceService.deleteServiceInstance(request);

            StepVerifier.create(response)
                        .expectNextMatches(DeleteServiceInstanceResponse::isAsync)
                        .verifyComplete();

            HeadBucketRequest headBucketRequest = HeadBucketRequest.builder().bucket(serviceInstanceId).build();
            assertThrows(NoSuchBucketException.class, () -> s3Client.headBucket(headBucketRequest));
        }
    }

    @Nested
    @DisplayName("Create Service Instance Binding Tests")
    class CreateServiceInstanceBindingTests {

        @Test
        @DisplayName("Should Create Service Instance Binding Successfully")
        void shouldCreateServiceInstanceBindingSuccessfully() {
            String serviceInstanceId = "test-instance";
            String bindingId = "test-binding";
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket(serviceInstanceId).build();
            s3Client.createBucket(createBucketRequest);

            CreateServiceInstanceBindingRequest request = CreateServiceInstanceBindingRequest.builder()
                                                                                             .serviceInstanceId(serviceInstanceId)
                                                                                             .bindingId(bindingId)
                                                                                             .build();

            Mono<CreateServiceInstanceBindingResponse> response = awsInstanceService.createServiceInstanceBinding(request);

            StepVerifier.create(response)
                        .expectNextMatches(r -> {
                            Map<String, Object> credentials = ((CreateServiceInstanceAppBindingResponse) r).getCredentials();
                            return credentials.containsKey("accessKeyId") &&
                                credentials.containsKey("secretAccessKey") &&
                                serviceInstanceId.equals(credentials.get("bucketName"));
                        })
                        .verifyComplete();

            GetUserRequest getUserRequest = GetUserRequest.builder().userName(bindingId).build();
            GetUserResponse getUserResponse = iamClient.getUser(getUserRequest);
            assertNotNull(getUserResponse.user());
        }
    }

    @Nested
    @DisplayName("Delete Service Instance Binding Tests")
    class DeleteServiceInstanceBindingTests {

        @Test
        @DisplayName("Should Delete Service Instance Binding Successfully")
        void shouldDeleteServiceInstanceBindingSuccessfully() {
            String serviceInstanceId = "test-instance";
            String bindingId = "test-binding";
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket(serviceInstanceId).build();
            s3Client.createBucket(createBucketRequest);

            CreateUserRequest createUserRequest = CreateUserRequest.builder().userName(bindingId).build();
            iamClient.createUser(createUserRequest);

            DeleteServiceInstanceBindingRequest request = DeleteServiceInstanceBindingRequest.builder()
                                                                                             .bindingId(bindingId)
                                                                                             .build();

            Mono<DeleteServiceInstanceBindingResponse> response = awsInstanceService.deleteServiceInstanceBinding(request);

            StepVerifier.create(response)
                        .expectNextCount(1)
                        .verifyComplete();

            ListUsersResponse listUsersResponse = iamClient.listUsers();
            boolean userExists = listUsersResponse.users().stream()
                                                  .anyMatch(user -> user.userName().equals(bindingId));
            assertFalse(userExists);
        }
    }

    @Nested
    @DisplayName("Get Service Instance Binding Tests")
    class GetServiceInstanceBindingTests {

        @Test
        @DisplayName("Should Get Service Instance Binding Successfully")
        void shouldGetServiceInstanceBindingSuccessfully() {
            String bindingId = "test-binding";
            Map<String, Object> bindingInfo = new HashMap<>();
            bindingInfo.put("accessKeyId", "testAccessKeyId");
            bindingInfo.put("secretAccessKey", "testSecretAccessKey");
            bindingInfo.put("bucketName", "test-instance");

            awsInstanceService.getBindingStore().put(bindingId, bindingInfo);

            GetServiceInstanceBindingRequest request = GetServiceInstanceBindingRequest.builder()
                                                                                       .bindingId(bindingId)
                                                                                       .build();

            Mono<GetServiceInstanceBindingResponse> response = awsInstanceService.getServiceInstanceBinding(request);

            StepVerifier.create(response)
                        .expectNextMatches(r -> "testAccessKeyId".equals(
                            ((GetServiceInstanceAppBindingResponse) r).getCredentials().get("accessKeyId")) &&
                            "testSecretAccessKey".equals(((GetServiceInstanceAppBindingResponse) r).getCredentials().get("secretAccessKey")) &&
                            "test-instance".equals(((GetServiceInstanceAppBindingResponse) r).getCredentials().get("bucketName")))
                        .verifyComplete();
        }
    }
}

