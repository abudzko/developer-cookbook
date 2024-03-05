package com.budzko.cookbook.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = Client.builder()
                .target("http://localhost:2379")
//                .target("http://172.24.0.2:2379")
                .connectTimeout(Duration.ofSeconds(2))
                .build();
        KV kvClient = client.getKVClient();
        ByteSequence key = ByteSequence.from("test_key".getBytes(StandardCharsets.UTF_8));
        ByteSequence value = ByteSequence.from("test_value".getBytes(StandardCharsets.UTF_8));

        // put the key-value
        kvClient.put(key, value).get();

        // get the CompletableFuture
        CompletableFuture<GetResponse> getFuture = kvClient.get(key);

        // get the value from CompletableFuture
        GetResponse response = getFuture.get();
        response.getKvs().stream().forEach(keyValue -> {
            System.out.println(keyValue.getValue());
        });
//
//        // delete the key
//        kvClient.delete(key).get();
    }
}
