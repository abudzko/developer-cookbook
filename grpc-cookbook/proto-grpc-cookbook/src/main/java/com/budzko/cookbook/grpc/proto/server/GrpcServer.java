package com.budzko.cookbook.grpc.proto.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(10080).addService(new GrpcController()).build();
        server.start();
        server.awaitTermination();
    }
}
