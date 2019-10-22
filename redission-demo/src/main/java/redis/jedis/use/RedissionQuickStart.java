package redis.jedis.use;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

/**
 * redisson quick start
 *
 * @author George on 2019-10-21
 **/
public class RedissionQuickStart {
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config = Config.fromYAML(new File("config-file.yaml"));

        RedissonClient redisson = Redisson.create(config);
        RedissonReactiveClient redissonReactive = Redisson.createReactive(config);
        RedissonRxClient redissonRx = Redisson.createRx(config);


    }
}
