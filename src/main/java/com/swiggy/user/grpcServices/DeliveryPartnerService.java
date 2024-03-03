package com.swiggy.user.grpcServices;

import com.swiggy.user.models.responseModels.UserResponseModel;
import com.swiggy.user.services.interfaces.IUserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import proto.DeliveryPartnerRequest;
import proto.DeliveryPartnerResponse;
import proto.DeliveryPartnerServiceGrpc;

@GrpcService
public class DeliveryPartnerService extends DeliveryPartnerServiceGrpc.DeliveryPartnerServiceImplBase {
    @Autowired
    IUserService userService;
    @Override
    public void getDeliveryPartner(DeliveryPartnerRequest request, StreamObserver<DeliveryPartnerResponse> responseObserver) {
        UserResponseModel user = userService.fetchDeliveryPartner();
        DeliveryPartnerResponse deliveryPartnerResponse = DeliveryPartnerResponse.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .build();

        responseObserver.onNext(deliveryPartnerResponse);
        responseObserver.onCompleted();
    }
}
