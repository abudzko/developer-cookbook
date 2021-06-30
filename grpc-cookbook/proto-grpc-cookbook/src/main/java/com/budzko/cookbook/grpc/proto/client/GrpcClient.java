package com.budzko.cookbook.grpc.proto.client;

import com.budzko.cookbook.grpc.PingMessage;
import com.budzko.cookbook.grpc.PingPongServiceGrpc;
import com.budzko.cookbook.grpc.PongMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 10080).usePlaintext().build();
        PingPongServiceGrpc.PingPongServiceBlockingStub stub = PingPongServiceGrpc.newBlockingStub(channel);

        while (true) {
            Thread.sleep(1000);
            PongMessage pongMessage = stub.ping(PingMessage.newBuilder().setPayload("Ping from client").build());
            System.out.println("Client:" + pongMessage.getPayload());
        }
    }
}
