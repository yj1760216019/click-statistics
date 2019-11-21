package com.carlinx.club.controller;

import com.carlinx.club.entity.StatisticalBanner;
import com.carlinx.club.entity.StatisticalProduct;
import com.carlinx.club.service.BannerCountService;
import com.carlinx.club.service.ProductCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yj
 * @Create 2019/11/19 16:32
 */

@RestController
@RequestMapping("/api/data")
@Api("数据统计")
public class DataController {

    //商品统计数据
    @Autowired
    private ProductCountService productCountService;

    //Banner统计数据
    @Autowired
    private BannerCountService bannerCountService;



    @ApiOperation("商品统计")
    @PutMapping("/product/count")
    public String product(@ApiParam("用户id")@RequestParam(name = "memberId") Long memberId,
                          @ApiParam("商品id")@RequestParam(name = "productId")Long productId,
                          @ApiParam("统计类型")@RequestParam(name = "type")Integer type,
                          @ApiParam("渠道来源")@RequestParam(name = "sourceType")Integer sourceType){
        Boolean count = productCountService.count(memberId, productId, type, sourceType);
        return "success";
    }



    @ApiOperation("Banner统计")
    @PutMapping("/banner/count")
    public String Banner(@ApiParam("用户id")@RequestParam(name = "memberId") Long memberId,
                          @ApiParam("Banner id")@RequestParam(name = "bannerId")Long bannerId,
                          @ApiParam("渠道来源")@RequestParam(name = "sourceType")Integer sourceType){
        bannerCountService.count(memberId,bannerId,sourceType);
        return "success";
    }



}
