package com.sgmder.wechat.dto;

public class WechatCardTimeLimit{
    private String type;
    private Integer  begin_hour;
    private Integer end_hour;
    private Integer  begin_minute;
    private Integer end_minute;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBegin_hour() {
        return begin_hour;
    }

    public void setBegin_hour(Integer begin_hour) {
        this.begin_hour = begin_hour;
    }

    public Integer getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(Integer end_hour) {
        this.end_hour = end_hour;
    }

    public Integer getBegin_minute() {
        return begin_minute;
    }

    public void setBegin_minute(Integer begin_minute) {
        this.begin_minute = begin_minute;
    }

    public Integer getEnd_minute() {
        return end_minute;
    }

    public void setEnd_minute(Integer end_minute) {
        this.end_minute = end_minute;
    }
}