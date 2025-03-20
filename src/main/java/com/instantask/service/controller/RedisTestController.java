package com.instantask.service.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/redis-test")
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    public RedisTestController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // set key-value
    @GetMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String val) {
        redisTemplate.opsForValue().set(key, val);
        return "OK. set key=" + key + " val=" + val;
    }

    // read key-value
    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        String val = redisTemplate.opsForValue().get(key);
        return "key=" + key + ", val=" + val;
    }
}
