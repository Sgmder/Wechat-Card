package com.sgmder.wechat.dto;

public class WechatAPI {

    public final static String GET_ACCESS_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
    public final static String UPLOAD_CARD_IMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=";
    public final static String CREATE_CARD = "https://api.weixin.qq.com/card/create?access_token=";
    public final static String GET_CARD_DETAIL = "https://api.weixin.qq.com/card/get?access_token=";
    public final static String CREATE_CARD_QRCODE = "https://api.weixin.qq.com/card/qrcode/create?access_token=";

    public final static String ADD_TEST_WHITE_LIST = "https://api.weixin.qq.com/card/testwhitelist/set?access_token=";
}
