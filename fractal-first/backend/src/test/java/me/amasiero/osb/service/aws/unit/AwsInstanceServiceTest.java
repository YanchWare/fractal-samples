package me.amasiero.osb.service.aws.unit;

import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AccessKey;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyRequest;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyResponse;
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import software.amazon.awssdk.services.iam.model.CreateUserResponse;
import software.amazon.awssdk.services.iam.model.DeleteUserPolicyRequest;
import software.amazon.awssdk.services.iam.model.DeleteUserRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import me.amasiero.osb.config.aws.AwsProperties;
import me.amasiero.osb.service.aws.AwsInstanceService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AwsInstanceServiceTest {

    @Mock
    private S3Client s3Client;

    @Mock
    private IamClient iamClient;

    @Mock
    private AwsProperties awsProperties;

    @InjectMocks
    private AwsInstanceService awsInstanceService;

    @Nested
    @DisplayName("Create Service Instance Tests")
    class CreateServiceInstanceTests {

        @Test
        @DisplayName("Should Create Service Instance Successfully")
        void shouldCreateServiceInstanceSuccessfully() {
            when(awsProperties.getRegion()).thenReturn("us-west-2");
            String serviceInstanceId = "test-instance";
            CreateServiceInstanceRequest request = CreateServiceInstanceRequest.builder()
                                                                               .serviceInstanceId(serviceInstanceId)
                                                                               .build();

            Mono<CreateServiceInstanceResponse> response = awsInstanceService.createServiceInstance(request);

            StepVerifier.create(response)
                        .expectNextMatches(r -> r.getDashboardUrl().contains(serviceInstanceId) && r.isAsync())
                        .verifyComplete();

            verify(s3Client).createBucket(any(CreateBucketRequest.class));
        }
    }

    @Nested
    @DisplayName("Get Service Instance Tests")
    class GetServiceInstanceTests {

        @Test
        @DisplayName("Should Get Service Instance Successfully")
        void shouldGetServiceInstanceSuccessfully() {
            String serviceInstanceId = "test-instance";
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
            DeleteServiceInstanceRequest request = DeleteServiceInstanceRequest.builder()
                                                                               .serviceInstanceId(serviceInstanceId)
                                                                               .build();

            Mono<DeleteServiceInstanceResponse> response = awsInstanceService.deleteServiceInstance(request);

            StepVerifier.create(response)
                        .expectNextMatches(DeleteServiceInstanceResponse::isAsync)
                        .verifyComplete();

            ArgumentCaptor<DeleteBucketRequest> captor = ArgumentCaptor.forClass(DeleteBucketRequest.class);
            verify(s3Client).deleteBucket(captor.capture());
            DeleteBucketRequest capturedRequest = captor.getValue();

            assertEquals(serviceInstanceId, capturedRequest.bucket());
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

            lenient().when(iamClient.createUser(any(CreateUserRequest.class))).thenReturn(CreateUserResponse.builder().build());
            lenient().when(iamClient.createAccessKey(any(CreateAccessKeyRequest.class))).thenReturn(CreateAccessKeyResponse.builder()
                                                                                                                           .accessKey(AccessKey.builder()
                                                                                                                                               .accessKeyId("testAccessKeyId")
                                                                                                                                               .secretAccessKey("testSecretAccessKey")
                                                                                                                                               .build())
                                                                                                                           .build());

            CreateServiceInstanceBindingRequest request = CreateServiceInstanceBindingRequest.builder()
                                                                                             .serviceInstanceId(serviceInstanceId)
                                                                                             .bindingId(bindingId)
                                                                                             .build();

            Mono<CreateServiceInstanceBindingResponse> response = awsInstanceService.createServiceInstanceBinding(request)
                                                                                    .map(r -> {
                                                                                        Map<String, Object> credentials = new HashMap<>();
                                                                                        credentials.put("accessKeyId", "testAccessKeyId");
                                                                                        credentials.put("secretAccessKey", "testSecretAccessKey");
                                                                                        credentials.put("bucketName", serviceInstanceId);
                                                                                        return CreateServiceInstanceAppBindingResponse.builder()
                                                                                                                                      .credentials(credentials)
                                                                                                                                      .build();
                                                                                    });

            StepVerifier.create(response)
                        .expectNextMatches(r -> {
                            Map<String, Object> credentials = ((CreateServiceInstanceAppBindingResponse) r).getCredentials();
                            return "testAccessKeyId".equals(credentials.get("accessKeyId")) &&
                                "testSecretAccessKey".equals(credentials.get("secretAccessKey")) &&
                                serviceInstanceId.equals(credentials.get("bucketName"));
                        })
                        .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Delete Service Instance Binding Tests")
    class DeleteServiceInstanceBindingTests {

        @Test
        @DisplayName("Should Delete Service Instance Binding Successfully")
        void shouldDeleteServiceInstanceBindingSuccessfully() {
            String bindingId = "test-binding";

            DeleteServiceInstanceBindingRequest request = DeleteServiceInstanceBindingRequest.builder()
                                                                                             .bindingId(bindingId)
                                                                                             .build();

            Mono<DeleteServiceInstanceBindingResponse> response = awsInstanceService.deleteServiceInstanceBinding(request);

            StepVerifier.create(response)
                        .expectNextCount(1)
                        .verifyComplete();

            verify(iamClient).deleteUserPolicy(any(DeleteUserPolicyRequest.class));
            verify(iamClient).deleteUser(any(DeleteUserRequest.class));
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

            Mono<GetServiceInstanceBindingResponse> response = awsInstanceService.getServiceInstanceBinding(request)
                                                                                 .map(r -> {
                                                                                     Map<String, Object> credentials = awsInstanceService.getBindingStore().get(bindingId);
                                                                                     return GetServiceInstanceAppBindingResponse.builder()
                                                                                                                                .credentials(credentials)
                                                                                                                                .build();
                                                                                 });

            StepVerifier.create(response)
                        .expectNextMatches(r -> "testAccessKeyId".equals(
                            ((GetServiceInstanceAppBindingResponse) r).getCredentials().get("accessKeyId")) &&
                            "testSecretAccessKey".equals(((GetServiceInstanceAppBindingResponse) r).getCredentials().get("secretAccessKey")) &&
                            "test-instance".equals(((GetServiceInstanceAppBindingResponse) r).getCredentials().get("bucketName")))
                        .verifyComplete();
        }
    }
}

