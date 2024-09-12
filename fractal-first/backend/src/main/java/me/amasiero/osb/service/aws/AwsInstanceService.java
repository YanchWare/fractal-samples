package me.amasiero.osb.service.aws;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyRequest;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyResponse;
import software.amazon.awssdk.services.iam.model.DeleteUserPolicyRequest;
import software.amazon.awssdk.services.iam.model.DeleteUserRequest;
import software.amazon.awssdk.services.iam.model.PutUserPolicyRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;

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
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import me.amasiero.osb.config.aws.AwsProperties;
import me.amasiero.osb.exception.InvalidRequestException;
import me.amasiero.osb.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class AwsInstanceService implements ServiceInstanceService, ServiceInstanceBindingService {

    private final S3Client s3Client;
    private final IamClient iamClient;
    private final AwsProperties awsProperties;

    @Getter
    private final Map<String, Map<String, Object>> bindingStore = new ConcurrentHashMap<>();

    @Override
    public Mono<CreateServiceInstanceResponse> createServiceInstance(CreateServiceInstanceRequest request) {
        return Mono.fromCallable(() -> {
            try {
                String bucketName = request.getServiceInstanceId();
                CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                                                                             .bucket(bucketName)
                                                                             .build();
                s3Client.createBucket(createBucketRequest);

                String region = awsProperties.getRegion();
                String dashboardUrl = String.format("https://s3.console.aws.amazon.com/s3/buckets/%s?region=%s&tab=overview", bucketName, region);

                return CreateServiceInstanceResponse.builder()
                                                    .dashboardUrl(dashboardUrl)
                                                    .async(true)
                                                    .build();
            } catch (Exception e) {
                throw new InvalidRequestException("Failed to create service instance: " + e.getMessage());
            }
        });

    }

    @Override
    public Mono<GetServiceInstanceResponse> getServiceInstance(GetServiceInstanceRequest request) {
        return Mono.fromCallable(() -> {
            try {
                String bucketName = request.getServiceInstanceId();
                return GetServiceInstanceResponse.builder()
                                                 .parameters("bucketName", bucketName)
                                                 .build();
            } catch (Exception e) {
                throw new ResourceNotFoundException("Service instance not found: " + e.getMessage());
            }
        });
    }

    @Override
    public Mono<DeleteServiceInstanceResponse> deleteServiceInstance(DeleteServiceInstanceRequest request) {
        return Mono.fromCallable(() -> {
            try {
                String bucketName = request.getServiceInstanceId();
                DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                                                                             .bucket(bucketName)
                                                                             .build();
                s3Client.deleteBucket(deleteBucketRequest);

                return DeleteServiceInstanceResponse.builder()
                                                    .async(true)
                                                    .build();
            } catch (Exception e) {
                throw new InvalidRequestException("Failed to delete service instance: " + e.getMessage());
            }
        });
    }

    @Override
    public Mono<CreateServiceInstanceBindingResponse> createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {
        return Mono.fromCallable(() -> {
            try {
                String bucketName = request.getServiceInstanceId();
                String bindingId = request.getBindingId();

                CreateAccessKeyResponse createAccessKeyResponse = iamClient.createAccessKey(CreateAccessKeyRequest.builder().userName(bindingId).build());
                String accessKeyId = createAccessKeyResponse.accessKey().accessKeyId();
                String secretAccessKey = createAccessKeyResponse.accessKey().secretAccessKey();

                String policyDocument = String.format("{ \"Version\": \"2012-10-17\", \"Statement\": [{ \"Effect\": \"Allow\", \"Action\": \"s3:*\", \"Resource\": \"arn:aws:s3:::%s/*\" }] }", bucketName);
                iamClient.putUserPolicy(PutUserPolicyRequest.builder().userName(bindingId).policyName("S3AccessPolicy").policyDocument(policyDocument).build());

                Map<String, Object> credentials = new HashMap<>();
                credentials.put("accessKeyId", accessKeyId);
                credentials.put("secretAccessKey", secretAccessKey);
                credentials.put("bucketName", bucketName);

                return CreateServiceInstanceAppBindingResponse.builder()
                                                              .credentials(credentials)
                                                              .bindingExisted(false)
                                                              .build();
            } catch (Exception e) {
                throw new InvalidRequestException("Failed to create service instance binding: " + e.getMessage());
            }
        });
    }

    @Override
    public Mono<DeleteServiceInstanceBindingResponse> deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
        return Mono.fromCallable(() -> {
            try {
                String bindingId = request.getBindingId();

                iamClient.deleteUserPolicy(DeleteUserPolicyRequest.builder().userName(bindingId).policyName("S3AccessPolicy").build());

                iamClient.deleteUser(DeleteUserRequest.builder().userName(bindingId).build());

                return DeleteServiceInstanceBindingResponse.builder().build();
            } catch (Exception e) {
                throw new InvalidRequestException("Failed to delete service instance binding: " + e.getMessage());
            }
        });
    }

    @Override
    public Mono<GetServiceInstanceBindingResponse> getServiceInstanceBinding(GetServiceInstanceBindingRequest request) {
        return Mono.fromCallable(() -> {
            try {
                String bindingId = request.getBindingId();
                Map<String, Object> bindingInfo = bindingStore.get(bindingId);

                if (bindingInfo == null) {
                    throw new RuntimeException("Binding not found");
                }

                return GetServiceInstanceAppBindingResponse.builder()
                                                           .credentials(bindingInfo)
                                                           .build();
            } catch (Exception e) {
                throw new ResourceNotFoundException("Failed to get service instance binding: " + e.getMessage());
            }
        });
    }
}
