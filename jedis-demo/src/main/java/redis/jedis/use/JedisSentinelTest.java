package redis.jedis.use;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * jedis连接sentinel哨兵
 *
 * @author George on 2019-10-22
 **/
public class JedisSentinelTest {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMinIdle(20);

        String masterName ="mymaster";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
        sentinels.add(new HostAndPort("127.0.0.1", 26380).toString());
        sentinels.add(new HostAndPort("127.0.0.1", 26381).toString());

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, jedisPoolConfig, 3000, null);
        Jedis jedis = null;

        try{
            jedis = jedisSentinelPool.getResource();
            System.out.println(jedis.set("sentinel", "xiaoqiang"));
            System.out.println(jedis.get("sentinel"));
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
