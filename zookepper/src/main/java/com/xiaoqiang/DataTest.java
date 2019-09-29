package com.xiaoqiang;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据Test
 *
 * @author George on 2019-09-29
 **/
public class DataTest {
    ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper("127.0.0.1", 2181, event -> {
            System.out.println(event);
        });

    }

    /**
     * 获取节点数据
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getDataTest() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/xiaoqiang", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        }, stat);
        System.out.println(new String(data));
        System.out.println(stat);
    }

    /**
     * 设置节点数据
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void setDataTest() throws KeeperException, InterruptedException {
        //version 限制在某个版本基础之上进行修改，不想限制可以设置为-1
        zooKeeper.setData("/xiaoqiang", "hello".getBytes(), -1);
    }

    /**
     * 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void creatTest() throws KeeperException, InterruptedException {
        List<ACL> aclList= new ArrayList<>();
        int perm = ZooDefs.Perms.ALL;
        aclList.add(new  ACL(perm, new Id("world", "anyone")));
        zooKeeper.create("/xiaoqiang/man", "cool boy".getBytes(), aclList, CreateMode.PERSISTENT);
    }
}
