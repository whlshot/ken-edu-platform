package com.ken.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yhq
 * @date 2019/5/14
 */
@Component
public class RedisUtils {
    private final static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    //=============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public <T> T get(String key, Class<T> tClass) {
        return key == null ? null : (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public <T> T hget(String key, String item, Class<T> tClass) {
        return (T) redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public void hmset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public void hmset(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public void hset(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //==========================HyperLogLog======================

    /**
     * 添加元素到HyperLogLog中
     *
     * @param key     key值
     * @param objects 元素
     * @return
     */
    public long pfadd(String key, Object... objects) {
        return redisTemplate.opsForHyperLogLog().add(key, objects);
    }

    /**
     * 获取多个key对应的基数对应值
     *
     * @param keys
     * @return
     */
    public long pfcount(String... keys) {
        return redisTemplate.opsForHyperLogLog().size(keys);
    }

    /**
     * 获取多个key对应的基数对应值
     *
     * @param destKey 合并后的名称
     * @param keys    合并的key值
     * @return
     */
    public long pfMerge(String destKey, String... keys) {
        return redisTemplate.opsForHyperLogLog().union(destKey, keys);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public List<Object> sGetList(String key) {
        Set<Object> set = redisTemplate.opsForSet().members(key);
        if (set != null) {
            return new ArrayList<>(set);
        }
        return null;
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }


    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public <T> List<T> lGet(String key, long start, long end, Class<T> tClass) {
        return (List<T>) redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public <T> List<T> rGet(String key, long start, long end, Class<T> tClass) {
        return (List<T>) redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public Long rPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public void rPush(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public void lPush(String key, Object value, long time) {
        redisTemplate.opsForList().leftPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long rPushAll(String key, Object... value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public long lPushAll(String key, Object... value) {
        Object[] strs = value;
        return redisTemplate.opsForList().leftPushAll(key, strs);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public void rPush(String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 从list右边获取一个元素
     *
     * @param key
     * @return
     */
    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 从list右边获取一个元素
     *
     * @param key
     * @return
     */
    public <T> T rPop(String key, Class<T> tClass) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public void lUpdateIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        Long remove = redisTemplate.opsForList().remove(key, count, value);
        return remove;
    }

    /**
     * 根据指定的消息队列的消息数量采取不同的消息读取方式
     *
     * @param cls
     * @param id
     * @param num
     * @Title batchPop
     * @Date 2017年8月24日 上午8:39:26
     * @author xhb
     */
    public List<Object> batchPop(Class cls, String id, int num) {
        long size = lLen(id);
        List<Object> objList = new ArrayList<>();
        //数据量较少时采用阻塞式单条读取
        if (size < 2) {
            objList.add(brPop(cls, id));
        } else if (size > num) { //数据量超过指定的批数量时采用管道方式批量读取指定的数量
            objList = pipelineSample(cls, id, num);
        } else {//数据量未超过指定批数量且有少数存量时，采用管道方式批量读取队列内所有消息(读写频繁时可能有误差)
            objList = pipelineSample(cls, id, (int) size);
        }
        return objList;
    }

    /**
     * 获取指定消息队列的消息数量
     *
     * @param key
     * @Title lLen
     * @Date 2017年8月24日 上午8:33:06
     * @author xhb
     */
    public long lLen(String key) {
        long size = 0L;
        try {
            size = redisTemplate.boundListOps(key).size();
        } catch (Exception e) {
            logger.error("获取队列消息数量发生异常-----", e);
        }
        return size;
    }

    /**
     * 右侧读取消息--阻塞式
     *
     * @param cls 欲转换的类型
     * @param id  redis队列名称
     * @Title brPop
     * @Date 2017年8月9日 上午11:28:37
     * @author xhb
     */
    public Object brPop(Class cls, String id) {
        RedisConnection conn = null;
        Object info = null;
        try {
            conn = RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());
            byte[] rawkay = redisTemplate.getStringSerializer().serialize(id);
            List<byte[]> results = conn.bRPop(0, rawkay);// 无限等待

            if (!org.apache.commons.collections.CollectionUtils.isEmpty(results)) {

                Object value = redisTemplate.getValueSerializer().deserialize(results.get(1));
                info = JsonUtils.toObject(value.toString(), cls);
            }
        } catch (Exception e) {
            logger.error("阻塞式读取数据异常-----", e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return info;
    }


    /**
     * 批量获取队列数据
     *
     * @param cls 数据类型
     * @param id  队列名称
     * @param num 一次性读取数量（超过队列数据数量自动忽略null数据）
     * @Title
     * @Date 2017年8月2日 下午3:57:14
     * @author XHB
     */
    public List<Object> pipelineSample(Class cls, String id, int num) {
        final byte[] rawkay = redisTemplate.getStringSerializer().serialize(id);
        final int size = num;
        //pipeline
        RedisCallback<List<Object>> pipelineCallback = new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (int i = 0; i < size; i++) {
                    connection.rPop(rawkay);
                }
                return connection.closePipeline();
            }

        };

        List<Object> results = (List<Object>) redisTemplate.execute(pipelineCallback);

        Object info = null;
        List<Object> resList = new ArrayList<>();
        for (Object item : results) {
            if (item != null) {
                Object value = redisTemplate.getValueSerializer().deserialize((byte[]) item);
                resList.add(JsonUtils.toObject(value.toString(), cls));
            }
        }
        return resList;
    }
    //==============================sorted set=================================

    /**
     * 添加数据
     *
     * @param key   key值
     * @param value value值
     * @param score 分值
     * @return
     */
    public boolean zAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 获取有序集合的成员数量
     *
     * @param key
     * @return
     */
    public Long zCard(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 计算分值之间的数量
     *
     * @param key   key
     * @param start 开始的分值
     * @param end   结束的分值
     * @return
     */
    public long zCount(String key, double start, double end) {
        return redisTemplate.opsForZSet().count(key, start, end);
    }

    /**
     * 获取某个分值中间的成员
     *
     * @param key   key值
     * @param start 开始分值
     * @param end   结束分值
     * @return
     */
    public Set<Object> zRange(String key, double start, double end) {
        return redisTemplate.opsForZSet().rangeByScore(key, start, end);
    }
}
