package com.sgmder.wechat.dto;

public class CashWechatCard implements WechatCard{

    private String card_type;
    private CashWechatCardDetail cash;

    public CashWechatCard(int least_cost, int reduce_cost) {
        cash = new CashWechatCardDetail();
        card_type = "CASH";
        cash.least_cost = least_cost;
        cash.reduce_cost = reduce_cost;
    }

    public void setBase_info(WechatCardBaseInfo base_info) {
        cash.base_info = base_info;
    }

    public void setAdvanced_info(WechatCardAdvancedInfo advanced_info) {
        cash.advanced_info = advanced_info;
    }

    class CashWechatCardDetail{

        private WechatCardBaseInfo base_info;
        private WechatCardAdvancedInfo advanced_info;
        private int least_cost;
        private int reduce_cost;

        public WechatCardBaseInfo getBase_info() {
            return base_info;
        }
        public WechatCardAdvancedInfo getAdvanced_info() {
            return advanced_info;
        }
        public int getLeast_cost() {
            return least_cost;
        }
        public int getReduce_cost() {
            return reduce_cost;
        }

    }

    public String getCard_type() {
        return card_type;
    }

    public CashWechatCardDetail getCash() {
        return cash;
    }

}
