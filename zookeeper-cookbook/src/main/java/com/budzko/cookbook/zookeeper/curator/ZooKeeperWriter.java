package com.budzko.cookbook.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ZooKeeperWriter {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("172.18.0.3:2181", new RetryForever(1000));
        curatorFramework.start();

        String path = "/path/status/x";
        ByteBuffer dataBuffer = ByteBuffer.allocate(Integer.BYTES).putInt(123);
        curatorFramework.setData().forPath(path, dataBuffer.array());
        byte[] data = curatorFramework.getData().forPath(path);
        System.out.println(Arrays.toString(data));
    }
}
