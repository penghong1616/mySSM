package com.wsk.service.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.wsk.tool.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com.wsk")
public class AlipayConfig {
    static {
        System.out.println("component--");
    }
}
