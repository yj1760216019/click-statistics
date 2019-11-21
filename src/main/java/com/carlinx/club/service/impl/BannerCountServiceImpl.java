package com.carlinx.club.service.impl;

import cn.hutool.json.JSONUtil;
import com.carlinx.club.cache.RedisOperator;
import com.carlinx.club.constant.MallCountConstant;
import com.carlinx.club.entity.StatisticalBanner;
import com.carlinx.club.service.BannerCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yj
 * @Create 2019/11/19 10:02
 */

@Service
public class BannerCountServiceImpl implements BannerCountService {

    @Autowired
    private RedisOperator redisOperator;

    @Override
    public Boolean count(Long memberId, Long bannerId, Integer sourceType) {
        //查询对应渠道 对应Banner是否存在
        String bannerData = (String)redisOperator.getHash(MallCountConstant.ECARX_MALL_BASE + MallCountConstant.BANNER_CHANNEL + sourceType, String.valueOf(bannerId));
        StatisticalBanner statisticalBanner = null;
        if(bannerData == null){
            //缓存中无数据   生成初始化数据
            statisticalBanner = new StatisticalBanner(bannerId,0L,0L,sourceType);
        }else{
            statisticalBanner = JSONUtil.toBean(bannerData, StatisticalBanner.class);
        }
        Boolean result = countOperater(memberId, bannerId, sourceType, statisticalBanner);
        return result;
    }


    public Boolean countOperater(Long memberId,Long bannerId,Integer sourceType,StatisticalBanner statisticalBanner){
        //如果memberId不为空  判断缓存中是否有对应渠道信息
        Boolean isExist = Boolean.FALSE;
        if(memberId != null){
           isExist = redisOperator.hasHashKey(MallCountConstant.ECARX_MALL_BASE + MallCountConstant.BANNER_MEMBER + sourceType + "_" + bannerId, String.valueOf(memberId));
        }
        //判断是否增加uv
        if(memberId != null && !isExist){
            statisticalBanner.setUv(statisticalBanner.getUv() + 1);
        }
        statisticalBanner.setPv(statisticalBanner.getPv() + 1);
        if(memberId != null && !isExist){
            //用户id不为空且缓存中不存在   将用户信息加上缓存
            redisOperator.setHash(MallCountConstant.ECARX_MALL_BASE + MallCountConstant.BANNER_MEMBER + sourceType + "_" + bannerId,String.valueOf(memberId),"");
        }
        //添加统计数据到缓存
        redisOperator.setHash(MallCountConstant.ECARX_MALL_BASE +MallCountConstant.BANNER_CHANNEL+ sourceType,String.valueOf(bannerId),JSONUtil.toJsonStr(statisticalBanner));
        return true;
    }


}
