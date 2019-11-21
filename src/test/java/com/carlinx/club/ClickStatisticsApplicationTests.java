package com.carlinx.club;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.json.JSONUtil;
import com.carlinx.club.cache.RedisOperator;
import com.carlinx.club.constant.MallCountConstant;
import com.carlinx.club.service.ProductCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClickStatisticsApplicationTests {

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private ProductCountService productCountService;

    @Test
    public void contextLoads() {
        Set<String> keys = redisOperator.getRedisTemplate().keys(MallCountConstant.ECARX_MALL_BASE + "*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

}
