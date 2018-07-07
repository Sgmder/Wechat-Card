package com.sgmder.wechat.dto;

public class WechatCardDateInfo {
    /**
     * 使用时间的类型，旧文档采用的1和2依然生效。
     *
     * DATE_TYPE_FIX_TIME_RANGE 表示固定日期区间，DATE_TYPE_FIX_TERM 表示固定时长 （自领取后按天算。
     *
     * string
     */
    private String type;

    /**
     * type为DATE_TYPE_FIX_TIME_RANGE时专用，
     * 表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间,UTC+8，单位为秒）
     */
    private Integer begin_timestamp;

    /**
     * 	表示结束时间 ， 建议设置为截止日期的23:59:59过期 。 （ 东八区时间,UTC+8，单位为秒 ）
     */
    private Integer end_timestamp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBegin_timestamp() {
        return begin_timestamp;
    }

    public void setBegin_timestamp(Integer begin_timestamp) {
        this.begin_timestamp = begin_timestamp;
    }

    public Integer getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(Integer end_timestamp) {
        this.end_timestamp = end_timestamp;
    }
}
