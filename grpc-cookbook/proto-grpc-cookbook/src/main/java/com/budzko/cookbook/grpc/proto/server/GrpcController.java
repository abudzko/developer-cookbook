package com.budzko.cookbook.grpc.proto.server;

import com.budzko.cookbook.grpc.PingMessage;
import com.budzko.cookbook.grpc.PingPongServiceGrpc;
import com.budzko.cookbook.grpc.PongMessage;
import io.grpc.stub.StreamObserver;

public class GrpcController extends PingPongServiceGrpc.PingPongServiceImplBase {

    @Override
    public void ping(PingMessage request, StreamObserver<PongMessage> responseObserver) {
        System.out.println("Server:" + request.getPayload());
        PongMessage pongMessage = PongMessage.newBuilder().setPayload("Pong from server").build();
        responseObserver.onNext(pongMessage);
        responseObserver.onCompleted();
    }
}
