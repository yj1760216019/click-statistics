package com.carlinx.club.service;

/**
 * @Author yj
 * @Create 2019/11/18 13:37
 */

public interface ProductCountService {

    Boolean count(Long memberId,Long productId,Integer type,Integer sourceType);

}
