package com.budzko.cookbook.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;

import java.util.List;

public class ZooKeeperCurator {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryForever(1000));
        curatorFramework.start();

        List<String> data = curatorFramework.getChildren().forPath("/brokers/topics/quickstart-events/partitions/0");
        System.out.println(data);
    }
}
