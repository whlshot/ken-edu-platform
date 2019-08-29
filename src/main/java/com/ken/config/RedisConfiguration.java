package com.ken.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yhq
 * @date 2019/5/14
 */
@Configuration
public class RedisConfiguration {

    @Value("${redis.url}")
    private String redisUrl;

    @Value("${redis.pool.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.pool.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.pool.minIdle}")
    private Integer minIdle;

    @Value("${redis.pool.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.pool.testOnReturn}")
    private boolean testOnReturn;

    @Value("${redis.pool.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;

    @Value("${redis.pool.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${redis.pool.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Bean
    public RedisHttpSessionConfiguration redisHttpSessionConfiguration() {
        RedisHttpSessionConfiguration configuration = new RedisHttpSessionConfiguration();
        configuration.setMaxInactiveIntervalInSeconds(1800);
        configuration.setRedisNamespace("edu");
        return configuration;
    }

    @Bean
    public HeaderHttpSessionStrategy headerHttpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);//最大活动对象数
        config.setMaxIdle(maxIdle);//最大能够保持idle状态的对象数
        config.setMinIdle(minIdle);//最小能够保持idle状态的对象数
        config.setMaxWaitMillis(maxWaitMillis);//当池内没有返回对象时，最大等待时间
        config.setTestOnBorrow(testOnBorrow);//当调用borrow Object方法时，是否进行有效性检查
        //config.setTestOnReturn(testOnReturn);//当调用return Object方法时，是否进行有效性检查
        //config.setTestWhileIdle(testWhileIdle);//向调用者输出“链接”对象时，是否检测它的空闲超时；
        //config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);//对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3.
        //config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);//空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1.
        return config;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(jedisPoolConfig);
        factory.setHostName(redisUrl);
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory factory, StringRedisSerializer stringRedisSerializer) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

}
