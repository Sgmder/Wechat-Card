package com.sgmder.wechat.dto;

import java.util.List;

public class WechatCardAdvancedInfo {

    private WechatCardUseCondition use_condition;
    private WechatCardAbstract abstract1;
    private List<WechatCardTextImage> text_image_list;
    private List<WechatCardTimeLimit> time_limit;
    private List<String> business_service;

    public void addUserCondition(String accept_category, String reject_category, boolean can_use_with_other_discount) {
        use_condition = new WechatCardUseCondition(accept_category, reject_category, can_use_with_other_discount);
    }

    public void addAbstract(String str, List<String> list) {
        abstract1 = new WechatCardAbstract(str, list);
    }

    public List<WechatCardTextImage> getText_image_list() {
        return text_image_list;
    }

    public void setText_image_list(List<WechatCardTextImage> text_image_list) {
        this.text_image_list = text_image_list;
    }

    public List<WechatCardTimeLimit> getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(List<WechatCardTimeLimit> time_limit) {
        this.time_limit = time_limit;
    }

    public List<String> getBusiness_service() {
        return business_service;
    }

    public void setBusiness_service(List<String> business_service) {
        this.business_service = business_service;
    }

    class WechatCardUseCondition {
        private String accept_category;
        private String reject_category;
        private boolean can_use_with_other_discount;

        public WechatCardUseCondition(String accept_category, String reject_category, boolean can_use_with_other_discount) {
            this.accept_category = accept_category;
            this.reject_category = reject_category;
            this.can_use_with_other_discount = can_use_with_other_discount;
        }

        public String getAccept_category() {
            return accept_category;
        }

        public String getReject_category() {
            return reject_category;
        }

        public Boolean getCan_use_with_other_discount() {
            return can_use_with_other_discount;
        }
    }

    class WechatCardAbstract {
        private String abstract1;
        private List<String> icon_url_list;

        public WechatCardAbstract(String abstract1, List<String> icon_url_list) {
            this.abstract1 = abstract1;
            this.icon_url_list = icon_url_list;
        }

        public String getAbstract() {
            return abstract1;
        }

        public List<String> getIcon_url_list() {
            return icon_url_list;
        }

    }

    public WechatCardUseCondition getUse_condition() {
        return use_condition;
    }

    public WechatCardAbstract getAbstract() {
        return abstract1;
    }
}
