package com.wsk.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wsk.pojo.Order;
import com.wsk.pojo.OrderDetail;
import com.wsk.pojo.UserInformation;
import com.wsk.service.GoodsCarService;
import com.wsk.service.Impl.PayService;
import com.wsk.service.OrderDetailService;
import com.wsk.service.OrderService;
import com.wsk.service.ShopCarService;
import com.wsk.tool.Constant;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsCarService goodsCarService;
    @Autowired
    private OrderDetailService orderDetailService;
    @RequestMapping("pay_index")
    public String pay(){
        return "pay/pay";
    }
    @RequestMapping(value = "goAlipay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipay(String total_amount, HttpServletRequest request) throws Exception {
        UserInformation userInformation=(UserInformation)request.getSession().getAttribute("userInformation") ;
        String[]shop_array=request.getParameterValues("shop_array");
        String[]shop_num=request.getParameterValues("shop_num");
        System.out.println("商品id："+ Arrays.toString(shop_array));
        System.out.println("商品数量："+ Arrays.toString(shop_num));
        Integer userId=userInformation.getId();
        System.out.println("userid"+userId);
        String out_trade_no= UUID.randomUUID().toString();
        String subject="订单支付";
        System.out.println("响应支付");
        Order order=new Order();
        order.setNumber(out_trade_no);
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setOrderName(subject);
        orderService.insertOrder(order);
        System.out.println("orderID:"+order.getId());
        int index=0;
        for (String shopId:shop_array){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setShopInfoId(Integer.valueOf(shopId));
            orderDetail.setShopInfoNum(Integer.valueOf(shop_num[index++]));
            goodsCarService.delete(userId,Integer.valueOf(shopId));
            orderDetailService.insert(orderDetail);
        }
        String result=payService.pay(out_trade_no,total_amount,subject,null);
        return result;
    }
    //同步验证
    @RequestMapping("alipayReturnNotice")
    @ResponseBody
    public String alipayReturnNotice(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        boolean signVerified=checkSign(request);
        if ((signVerified)){
//            //商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//            //付款金额
//            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            return "<p>支付成功，<a href=\"/\">返回首页</a></p>";
        }
       return "<p>支付失败，<a href=\"/\">返回首页</a></p>";
    }
    @RequestMapping(value = "alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
        boolean signVerified=checkSign(request);
        if (signVerified){
            //支付订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            if (trade_status.equals("TRADE_SUCCESS")) {
                //修改订单状态
                orderService.update(Integer.valueOf(out_trade_no),1);
            }
        }
        return "success";
    }
        public boolean checkSign(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,Constant.alipay_public_key, Constant.charset, Constant.sign_type); //调用SDK验证签名
        return signVerified;
    }
}
