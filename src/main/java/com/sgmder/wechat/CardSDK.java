package com.sgmder.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgmder.wechat.dto.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CardSDK {

    private static String app_id = "wxcbaa65768f2fbe7d";
    private static String app_secret = "23e08ce613739088ec1d617b60911fbf";
    private static CloseableHttpClient client = HttpClients.createDefault();

    public static void main1(String[] args) {
        // 获取access_token
        String accessToken = getAccessToken(app_id, app_secret);
        addTestWhiteList(accessToken, new String[]{"DBG_SGMD"});
    }

    public static void main(String[] args) {
        // 获取access_token
        String accessToken = getAccessToken(app_id, app_secret);
        // 上传卡券图片素材
        String remoteImgUrl = uploadCardImg(accessToken, new File("/Users/sgmder/Pictures/IMG_8018.JPG"));

        CashWechatCard card = new CashWechatCard(10000, 1000);
        WechatCardBaseInfo baseInfo = new WechatCardBaseInfo();
        baseInfo.setLogo_url(remoteImgUrl);
        baseInfo.setCode_type("CODE_TYPE_QRCODE");
        baseInfo.setBrand_name("测试代金券100-10");
        baseInfo.setTitle("测试标题");
        baseInfo.setColor("Color040");
        baseInfo.setNotice("这是提示");
        baseInfo.setDescription("这是描述");
        baseInfo.setCan_give_friend(false);
        baseInfo.setCenter_app_brand_user_name("gh_8f27a4af9ed4@app");
        baseInfo.setCenter_app_brand_pass("pages/clerkMain/index");
        baseInfo.setCenter_sub_title("满100元可用哦");
        baseInfo.setCenter_title("23333");
        baseInfo.setCenter_url("www.ifeixiu.com");
        baseInfo.setPromotion_url("www.ifeixiu.com");
        baseInfo.setPromotion_app_brand_pass("pages/clerkMain/index");
        baseInfo.setPromotion_app_brand_user_name("gh_8f27a4af9ed4@app");
        baseInfo.setPromotion_url_name("url_name");
        baseInfo.setPromotion_url_sub_title("sub_title");

        baseInfo.setCustom_url("www.ifeixiu.com");
        baseInfo.setCustom_url_name("飞修官网");

        WechatCardSku sku = new WechatCardSku();
        sku.setQuantity(1);
        baseInfo.setSku(sku);

        WechatCardDateInfo di = new WechatCardDateInfo();
        di.setType("DATE_TYPE_FIX_TIME_RANGE");
        di.setBegin_timestamp((int) (System.currentTimeMillis() / 1000L));
        di.setEnd_timestamp((int) (System.currentTimeMillis() / 1000L) + 3600 * 2);
        baseInfo.setDate_info(di);

        card.setBase_info(baseInfo);

        WechatCardAdvancedInfo advancedInfo = new WechatCardAdvancedInfo();

        advancedInfo.addUserCondition("任何人","CV大佬",true);

        List<String> stl1 = new ArrayList<>();
        stl1.add(remoteImgUrl);
        advancedInfo.addAbstract("测试文字2333",stl1);

        List<String> stl2 = new ArrayList<>();
        stl2.add("BIZ_SERVICE_FREE_PARK");
        stl2.add("BIZ_SERVICE_FREE_WIFI");
        advancedInfo.setBusiness_service(stl2);

        List<WechatCardTimeLimit> stl3 = new ArrayList<>();
        WechatCardTimeLimit t1 = new WechatCardTimeLimit();
        t1.setType("SATURDAY");
        t1.setBegin_hour(10);
        t1.setBegin_minute(0);
        t1.setEnd_hour(22);
        t1.setEnd_minute(59);
        stl3.add(t1);
        WechatCardTimeLimit t2 = new WechatCardTimeLimit();
        t2.setType("SUNDAY");
        t2.setBegin_hour(10);
        t2.setBegin_minute(0);
        t2.setEnd_hour(22);
        t2.setEnd_minute(59);
        stl3.add(t2);
        advancedInfo.setTime_limit(stl3);

        List<WechatCardTextImage> stl4 = new ArrayList<>();
        WechatCardTextImage t3 = new WechatCardTextImage();
        t3.setImage_url(remoteImgUrl);
        t3.setText("WechatCardTextImage1");
        WechatCardTextImage t4 = new WechatCardTextImage();
        t4.setImage_url(remoteImgUrl);
        t4.setText("WechatCardTextImage2");
        stl4.add(t3);
        stl4.add(t4);
        advancedInfo.setText_image_list(stl4);

        card.setAdvanced_info(advancedInfo);

        String cardId = createCard(accessToken, new CardDTO(card));

        getCardDetail(accessToken, cardId);

        CardQrCode qrCode = new CardQrCode();
        qrCode.setExpire_seconds("1800");
        qrCode.addCard(cardId);
        qrCode.addCard("pZ0W-0VbKRxW9l8-mUeeO12-1SUg");
        qrCode.addCard("pZ0W-0dYF_Nu_FaCHPOZnuLmMAUk");
        createCardQrcode(accessToken, qrCode);
    }

    // 获取access_token
    private static String getAccessToken(String appId, String appSecret) {
        String params = "appid=" + appId + "&secret=" + appSecret;
        HttpGet request = new HttpGet(WechatAPI.GET_ACCESS_TOKEN_API + params);
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject rst = JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
                return rst.getString("access_token");
            }
            throw new RuntimeException(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 上传卡券图片素材
    private static String uploadCardImg(String accessToken, File file) {
        HttpPost request = new HttpPost(WechatAPI.UPLOAD_CARD_IMG + accessToken);
        FileBody fileBody = new FileBody(file);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("buffer", fileBody);
        request.setEntity(builder.build());
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject rst = JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
                return rst.getString("url");
            }
            throw new RuntimeException(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 创建卡券
    private static String createCard(String accessToken, CardDTO card) {
        HttpPost request = new HttpPost(WechatAPI.CREATE_CARD + accessToken);
        System.out.println(JSON.toJSONString(card));
        request.setEntity(new StringEntity(JSON.toJSONString(card), Charset.forName("UTF-8")));
        request.setHeader("Content-Type", "application/json");
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject rst = JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
                if (rst.getInteger("errcode").equals(0)) {
                    return rst.getString("card_id");
                }
                throw new RuntimeException(rst.toString());
            }
            throw new RuntimeException(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 获取卡券详情
    private static void getCardDetail(String accessToken, String cardId) {
        HttpPost request = new HttpPost(WechatAPI.GET_CARD_DETAIL + accessToken);
        JSONObject obj = new JSONObject();
        obj.put("card_id", cardId);
        request.setEntity(new StringEntity(JSON.toJSONString(obj), Charset.forName("UTF-8")));
        request.setHeader("Content-Type", "application/json");
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject rst = JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
                if (rst.getInteger("errcode").equals(0)) {
                    System.out.println(rst);
//                    return rst.getString("card_id");
                    return;
                }
                throw new RuntimeException(rst.toString());
            }
            throw new RuntimeException(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 创建二维码接口
    private static void createCardQrcode(String accessToken, CardQrCode qrCode) {
        HttpPost request = new HttpPost(WechatAPI.CREATE_CARD_QRCODE + accessToken);
        System.out.println(JSON.toJSONString(qrCode));
        request.setEntity(new StringEntity(JSON.toJSONString(qrCode), Charset.forName("UTF-8")));
        request.setHeader("Content-Type", "application/json");
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject rst = JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
                if (rst.getInteger("errcode").equals(0)) {
                    System.out.println(rst);
//                    return rst.getString("card_id");
                    return;
                }
                throw new RuntimeException(rst.toString());
            }
            throw new RuntimeException(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 设置微信测试白名单
    private static void addTestWhiteList(String accessToken, String[] username) {
        HttpPost request = new HttpPost(WechatAPI.ADD_TEST_WHITE_LIST + accessToken);
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        request.setEntity(new StringEntity(JSON.toJSONString(obj), Charset.forName("UTF-8")));
        request.setHeader("Content-Type", "application/json");
        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject rst = JSON.parseObject(EntityUtils.toString(entity, "utf-8"));
                if (rst.getInteger("errcode").equals(0)) {
                    System.out.println(rst);
//                    return rst.getString("card_id");
                    return;
                }
                throw new RuntimeException(rst.toString());
            }
            throw new RuntimeException(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
