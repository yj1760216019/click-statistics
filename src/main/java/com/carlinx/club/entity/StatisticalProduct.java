package com.carlinx.club.entity;

import java.io.Serializable;

/**
 * @Author yj
 * @Create 2019/11/18 13:33
 */

public class StatisticalProduct implements Serializable {

    private Long productId;
    private Long pv;
    private Long uv;
    private Long exchange;
    private Long success;
    private Integer channel;


    public Long getProductId() {
        return productId;
    }


    public StatisticalProduct(Long productId, Long pv, Long uv, Long exchange, Long success, Integer channel) {
        this.productId = productId;
        this.pv = pv;
        this.uv = uv;
        this.exchange = exchange;
        this.success = success;
        this.channel = channel;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
    }

    public Long getExchange() {
        return exchange;
    }

    public void setExchange(Long exchange) {
        this.exchange = exchange;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "StatisticalProduct{" +
                "productId=" + productId +
                ", pv=" + pv +
                ", uv=" + uv +
                ", exchange=" + exchange +
                ", success=" + success +
                ", channel=" + channel +
                '}';
    }
}
