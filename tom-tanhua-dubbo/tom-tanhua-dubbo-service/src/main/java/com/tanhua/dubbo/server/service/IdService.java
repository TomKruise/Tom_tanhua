package com.tanhua.dubbo.server.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 原理：使用Redis的自增长类型，实现自增长的id
 */
@Service
public class IdService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 生成自增长的id
     *
     * @return
     */
    public Long createId(String type, String objectId) {

        type = StringUtils.upperCase(type);

        String hashKey = "TANHUA_HASH_ID_" + type;
        //如果ObjectId已经存在的话，就返回对应的id
        if(this.redisTemplate.opsForHash().hasKey(hashKey, objectId)){
            return Long.valueOf(this.redisTemplate.opsForHash().get(hashKey, objectId).toString());
        }

        String key = "TANHUA_ID_" + type;
        Long id = this.redisTemplate.opsForValue().increment(key);

        // 将生成的id写入到hash表中
        this.redisTemplate.opsForHash().put(hashKey, objectId, id.toString());

        return id;
    }
}
