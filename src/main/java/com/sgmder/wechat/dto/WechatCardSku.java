package com.sgmder.wechat.dto;

public class WechatCardSku {

    /**
     * 卡券库存的数量，上限为100000000。
     */
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
