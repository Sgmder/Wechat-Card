package com.sgmder.wechat.dto;

import java.util.ArrayList;
import java.util.List;

public class CardQrCode {

    // QR_MULTIPLE_CARD QR_CARD
    private String action_name;
    private ActionInfo action_info;
    private String expire_seconds;

    public void addCard(String cardId) {
        if (action_info == null)
            action_info = new ActionInfo();
        action_info.addCard(new Card(cardId));
    }

    public void addCard(String cardId, String outerId, String outerStr, String openId, String isUniqueCode, String code) {
        if (action_info == null)
            action_info = new ActionInfo();
        action_info.addCard(new Card(cardId, outerId, outerStr, openId, isUniqueCode, code));
    }

    class ActionInfo {
        private int count = 0;
        private Card card;
        private CardM multiple_card;

        public Card getCard() {
            return card;
        }

        public CardM getMultiple_card() {
            return multiple_card;
        }

        private class CardM {
            private List<Card> card_list;

            public List<Card> getCard_list() {
                return card_list;
            }

            public void addCard(Card card) {
                if (card_list == null) {
                    card_list = new ArrayList<>();
                }
                card_list.add(card);
            }
        }

        void addCard(Card card) {
            if (count == 0) {
                this.card = card;
                action_name = "QR_CARD";
            } else {
                if (count == 1) {
                    multiple_card = new CardM();
                    multiple_card.addCard(this.card);
                    this.card = null;
                    multiple_card.addCard(card);
                    action_name = "QR_MULTIPLE_CARD";
                } else {
                    multiple_card.addCard(card);
                }
            }
            count++;
        }
    }

    class Card {
        private String code;
        private String card_id;
        private String openid;

        private String is_unique_code;
        private String outer_id;
        private String outer_str;

        Card(String card_id) {
            this.card_id = card_id;
        }

        Card(String card_id, String outer_id, String outer_str, String openid, String is_unique_code, String code) {
            this.code = code;
            this.card_id = card_id;
            this.openid = openid;
            this.is_unique_code = is_unique_code;
            this.outer_id = outer_id;
            this.outer_str = outer_str;
        }

        public String getCode() {
            return code;
        }


        public String getCard_id() {
            return card_id;
        }

        public String getOpenid() {
            return openid;
        }

        public String getIs_unique_code() {
            return is_unique_code;
        }

        public String getOuter_id() {
            return outer_id;
        }

        public String getOuter_str() {
            return outer_str;
        }
    }

    public String getAction_name() {
        return action_name;
    }

    public ActionInfo getAction_info() {
        return action_info;
    }

    public String getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(String expire_seconds) {
        this.expire_seconds = expire_seconds;
    }
}
