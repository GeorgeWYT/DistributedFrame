package redissentinelcluster.sentinelredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * master节点挂掉后，哨兵集群会选举出新的master节点，客户端实际实现了一个消息监听机制
 * 当哨兵把新master节点发布出去后，客户端会立马感知到新master节点信息，从而动态切换访问master的ip
 * @author George on 2019-10-22
 **/
@RestController
public class IndexController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test_sentinel")
    public void testSentinel() {
        int i = 1;
        while (true) {
            try {
                stringRedisTemplate.opsForValue().set("xiaoqiang" + i, i + "");
                System.out.println("设置key：" + "xiaoqiang " + i);
                ++i;
            } catch (Exception e) {
                System.out.println("错误" + e);
            }
        }
    }
}
