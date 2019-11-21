package com.carlinx.club.quartz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.carlinx.club.cache.RedisOperator;
import com.carlinx.club.constant.MallCountConstant;
import com.carlinx.club.entity.StatisticalBanner;
import com.carlinx.club.entity.StatisticalProduct;
import com.carlinx.club.service.BannerCountService;
import com.carlinx.club.service.ProductCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author yj
 * @Create 2019/11/19 15:56
 */

@Service
public class StatisticsJob {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsJob.class);

    @Autowired
    private RedisOperator redisOperator;






    @Scheduled(cron = "0 0/2 * * * ?")
    public void statisticsProductAndBanner(){
        logger.info("开始定时任务  进行统计数据入库");
        List<StatisticalProduct> statisticalProducts = new ArrayList<>();
        List<StatisticalBanner> banners = new ArrayList<>();
        for (int i = 0;i<=2;i++){
            //操作商品数据
            Map<Object, Object> productHashMap = redisOperator.getAllHashByKey(MallCountConstant.ECARX_MALL_BASE + MallCountConstant.PRODUCT_CHANNEL + i);
            Collection<Object> productValues = productHashMap.values();
            for (Object value : productValues) {
                StatisticalProduct statisticalProduct = JSONUtil.toBean((String) value, StatisticalProduct.class);
                statisticalProducts.add(statisticalProduct);
            }
            //将商品数据插入数据库
            Map<Object, Object> bannerHashMap = redisOperator.getAllHashByKey(MallCountConstant.ECARX_MALL_BASE + MallCountConstant.BANNER_CHANNEL + i);
            Collection<Object> bannerValues = bannerHashMap.values();
            for (Object value : bannerValues) {
                StatisticalBanner statisticalBanner = JSONUtil.toBean((String) value, StatisticalBanner.class);
                banners.add(statisticalBanner);
            }
        }
        if(CollUtil.isNotEmpty(statisticalProducts)){
            //执行商品数据入库操作
        }
        if(CollUtil.isNotEmpty(banners)){
            //执行banner数据入库操作
        }

        logger.info("商品数据:{}",JSONUtil.toJsonStr(statisticalProducts));
        logger.info("Banner数据:{}",JSONUtil.toJsonStr(banners));


        logger.info("开始清空当天统计数据");
        redisOperator.deletePrefix(MallCountConstant.ECARX_MALL_BASE);
        logger.info("数量统计操作完毕");
    }

}
