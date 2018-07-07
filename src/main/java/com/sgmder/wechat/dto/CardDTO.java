package com.sgmder.wechat.dto;

public class CardDTO {

    private WechatCard card;

    public CardDTO(WechatCard card) {
        this.card = card;
    }

    public WechatCard getCard() {
        return card;
    }
}
