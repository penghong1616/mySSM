package com.wsk.service.Impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wsk.tool.Constant;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;

@Service
public class PayService {
    private AlipayClient alipayClient = new DefaultAlipayClient(Constant.gatewayUrl, Constant.app_id, Constant.merchant_private_key, "json", Constant.charset, Constant.alipay_public_key, Constant.sign_type);
    /**
     *
     * @param out_trade_no 商品唯一ID
     * @param total_amount 商品价格总额
     * @param subject 商品题目
     * @param body 商品描述
     * @throws AlipayApiException
     */
    public String pay( String out_trade_no,String total_amount,String subject, String body) throws UnsupportedEncodingException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(Constant.gatewayUrl, Constant.app_id, Constant.merchant_private_key, "json", Constant.charset, Constant.alipay_public_key, Constant.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(Constant.return_url);
        alipayRequest.setNotifyUrl(Constant.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
        //		+ "\"total_amount\":\""+ total_amount +"\"," 
        //		+ "\"subject\":\""+ subject +"\"," 
        //		+ "\"body\":\""+ body +"\"," 
        //		+ "\"timeout_express\":\"10m\"," 
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        return result;
    }

    /**
     *
     * @param order_trade_no 订单ID
     * @param trade_no 支付宝交易号
     */
    public void  query(String order_trade_no,String trade_no ) throws AlipayApiException {
//        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ order_trade_no +"\","+"\"trade_no\":\""+ trade_no +"\"}");
//        String result = alipayClient.execute(alipayTradePagePayRequest).getBody();
//        System.out.println("result:"+result);
    }
}
