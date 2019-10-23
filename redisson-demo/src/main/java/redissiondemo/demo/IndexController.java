package redissiondemo.demo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Redisson
 * @author George on 2019-10-23
 **/
public class IndexController {
    @Autowired
    private Redisson redisson;


    @RequestMapping("/test/index")
    public void test(String[] args) {
        //声明锁名称
        String lockKey = "key";
        //获取锁
        RLock rLock = redisson.getLock(lockKey);
        //加锁
        rLock.lock();
        //释放锁
        rLock.unlock();
    }
}
