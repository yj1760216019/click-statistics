package com.carlinx.club.enums;

import javax.xml.bind.annotation.XmlType;

/**
 * @Author yj
 * @Create 2019/11/18 13:42
 */

public enum DataTypeEnum {
    CLICK(1,"点击商品"),
    SUBMIT(2,"点击兑换"),
    SUCCESS(3,"兑换成功"),
    NULL(null,"NULL");

    private Integer value;
    private String text;


    public static DataTypeEnum valueOf(Integer value){
        if(value == null) return NULL;
        switch (value){
            case 1:
                return CLICK;
            case 2:
                return SUBMIT;
            case 3:
                return SUCCESS;
            default:
                return NULL;
        }
    }


    DataTypeEnum() {
    }

    DataTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
