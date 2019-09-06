package com.ken;

import com.ken.redis.bean.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KenEduPlatformApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "1111");
        Assert.assertEquals("1111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        Person person = new Person("101", "cc", 20);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("com.ken", person);
        operations.set("com.kens", "1234556");
        operations.set("com.ken.f", person, 30, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.ken.f");
        boolean exists = redisTemplate.hasKey("com.ken.f");
        System.out.println("exists is " + exists);
        //Assert.assertEquals("cc", operations.get("com.ken"));
    }


}
