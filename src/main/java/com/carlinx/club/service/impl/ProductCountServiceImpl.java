package com.carlinx.club.service.impl;

import cn.hutool.json.JSONUtil;
import com.carlinx.club.cache.RedisOperator;
import com.carlinx.club.constant.MallCountConstant;
import com.carlinx.club.entity.StatisticalProduct;
import com.carlinx.club.enums.DataTypeEnum;
import com.carlinx.club.service.ProductCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yj
 * @Create 2019/11/18 13:38
 */


@Service
public class ProductCountServiceImpl implements ProductCountService {


    @Autowired
    private RedisOperator redisOperator;


    @Override
    public Boolean count(Long memberId,Long productId ,Integer type,Integer sourceType) {
         String productData = (String)redisOperator.getHash(MallCountConstant.ECARX_MALL_BASE +MallCountConstant.PRODUCT_CHANNEL + sourceType, String.valueOf(productId));
        StatisticalProduct statisticalData = null;
        if(productData == null){
            //缓存中无该商品  新增商品数据
            statisticalData = new StatisticalProduct(productId, 0L, 0L, 0L, 0L, sourceType);
        }else{
            statisticalData = JSONUtil.toBean(productData, StatisticalProduct.class);
        }
        Boolean result = countOperate(memberId, productId, type, sourceType, statisticalData);
        return result;
    }



    public Boolean countOperate(Long memberId,Long productId ,Integer type,Integer sourceType,StatisticalProduct statisticalProduct){
        if(DataTypeEnum.CLICK.getValue() == type){
            //判断用户是否在该渠道有数据
            Boolean isExist = Boolean.FALSE;
            if(memberId != null){
                isExist = redisOperator.hasHashKey(MallCountConstant.ECARX_MALL_BASE + MallCountConstant.PRODUCT_MEMBER + sourceType + "_" + productId, String.valueOf(memberId));
            }
            //判断是否增加uv
            if(memberId != null && !isExist){
                statisticalProduct.setUv(statisticalProduct.getUv()+1);
            }
            statisticalProduct.setPv(statisticalProduct.getPv()+1);
            //不为空并且不存在  用户id加缓存
            if(memberId != null && !isExist){
                redisOperator.setHash(MallCountConstant.ECARX_MALL_BASE+MallCountConstant.PRODUCT_MEMBER + sourceType+"_"+productId,String.valueOf(memberId),"");
            }
        }else if(DataTypeEnum.SUBMIT.getValue() == type){
            statisticalProduct.setExchange(statisticalProduct.getExchange()+1);
        }else if(DataTypeEnum.SUCCESS.getValue() == type){
            statisticalProduct.setSuccess(statisticalProduct.getSuccess()+1);
        }
        redisOperator.setHash(MallCountConstant.ECARX_MALL_BASE+MallCountConstant.PRODUCT_CHANNEL + sourceType,String.valueOf(productId), JSONUtil.toJsonStr(statisticalProduct));
        return true;
    }





}
