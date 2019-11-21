package com.carlinx.club.entity;

import java.io.Serializable;

/**
 * @Author yj
 * @Create 2019/11/19 9:43
 */

public class StatisticalBanner implements Serializable {

    private Long bannerId;
    private Long pv;
    private Long uv;
    private Integer channel;

    public StatisticalBanner() {
    }

    public StatisticalBanner(Long bannerId, Long pv, Long uv, Integer channel) {
        this.bannerId = bannerId;
        this.pv = pv;
        this.uv = uv;
        this.channel = channel;
    }

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
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

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "StatisticalBanner{" +
                "bannerId=" + bannerId +
                ", pv=" + pv +
                ", uv=" + uv +
                ", channel=" + channel +
                '}';
    }
}
