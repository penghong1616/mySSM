package com.wsk.tool;

public class Constant {
    public static String SECKILLKEY="secKill";
    public  static String SECKILLGOODID="goodId";
    public static String SECKILLUSERID="SECUSERID";
    public static String CACHE="cache-";
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101200666046";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChpDbrCgX2h6OUcXmZnX0S8Gk+bvKC+3nn/Ze9lyMq8ZvfxEPNaw2sQEQiKBD03Chvc3AsU2rTknP9SzsFfPs6kJEwpk9/6Y+3t1VEda8pXFC/b70QWvb3tvW8KzfGa5diaEbHjojXCoS6m80rQuKxjQ4aAPQ1vYr0secJSY0c6Rmqo6L5aQg5R7fzGrsBxAi6Bn+6M5Wg0ButWR+t2nfVraC4ZJlKqFz/DFianfgi/tpGLegnmNBMTi8a1CsNVQF6JoH6QNWiUJy4qe8yIBAJwLS+z6jPCvQ9bbJbsLdVad58wQO27uKMYXf4EcK3H5Pa3TVkORerS95TBY8ubbThAgMBAAECggEAOXKfghFh5Sh33r4ltY7fcOe4v9O2pQ6zWNwsHdmlShHskC602kD97wY4MNuEi0HjrRid+WL8SI14Y8FlDKJKt5nBwESbZJ6h0W17BIW+5zhECoWv8CwkMg34cBP8Eq0SdIsS/MGPqL24Roow9Pskjm3PYTaNlLv0stq3ffmCCY/MFjaUpvCv2X1/avzemB7SvoxKkoG/gJS/00JyE0b0apHBexNk/IWZ7cR9tsR2UbNiPZsWIkBVx9jaxoOu/tnoTvbjWd8l58kqYA+rd9B/6KRxi8toFMMF/mv+SJJP6cfuOLrm8uitMHym4oTLX5xuaEikKoDQllTc7DJE45SVAQKBgQDyf5Ndge9W68DBGAV1IW+cmg/fEAm4aM6gltLIqDkCfEr7IsQ4nPTbbrGVnXZGqn9ix+rhJd3y8rDXIIOA24ewhiuP1qp1JQCiFSICpdhYH936WZJQgMLuoZI69rqvwtsXcQO6BfMy5uP0CZ1YNfHpEx7oOnja3tdXOen80t7Z6QKBgQCqpCdonvCgneC6W0ccO1zgN7beOhjIwAbAgaOa4gJFBDQ4gG0ozT0V0OkT2Jjud+hdUvFvDcr0eRBHlhhUs5KXEL3EXVP+jOK/7xT24pS+5Cqg5Nr/7+a+gPlEFSkcJXxbU+pPYv7wN1dcoxrMJK5ezaUa91BcMrLVE+3LLqewOQKBgQC8TFXYsRFxFCUbixSJzEnHd1XFpsV1fWSdDCBmkUGbbXonoept72qujTi17Ywu+StrP8Nhyq6afNrWYTfbshud9cETsuOvjOj98pzzI2Znn28Ve8ICYsl61Nkgkf+KhSrs++Oh6gbbSrBNCb41BcZvblp8hxLbGVhrMdxAZlmHKQKBgAPfK6G9EXqjemjgD1H9qtLPkM+kA9HyQXT2vA6UmDaWrNSnXVKyrXMkRVWU8Ytfnx8W9bFuX6+CW7M0HaM2W9ofoB6ne3NoIPD37MLZS0S1ip4R80IhYFvsMzuvOzqPQy4TTlq0xVIP/2RzsvT01BwEkH13JRSiddpN1HRqv5VJAoGBANMJAX2qQ/e5eIKlclor0j8Roj7r6Clcy4lq/UBotUeJ/+wBeEkASaKen9vz5LvWXH4lmiHjqs/1rP7k0KBuZ4tXroeLqbnxbzX/Wl4r6z/vgxRtf6oeMTFmk37YlztYcqwtV7pfwaAYXsurOVoJ6YCsAfrOz6Ma4zpvsU5Z/Tx6";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoaQ26woF9oejlHF5mZ19EvBpPm7ygvt55/2XvZcjKvGb38RDzWsNrEBEIigQ9Nwob3NwLFNq05Jz/Us7BXz7OpCRMKZPf+mPt7dVRHWvKVxQv2+9EFr297b1vCs3xmuXYmhGx46I1wqEupvNK0LisY0OGgD0Nb2K9LHnCUmNHOkZqqOi+WkIOUe38xq7AcQIugZ/ujOVoNAbrVkfrdp31a2guGSZSqhc/wxYmp34Iv7aRi3oJ5jQTE4vGtQrDVUBeiaB+kDVolCcuKnvMiAQCcC0vs+ozwr0PW2yW7C3VWnefMEDtu7ijGF3+BHCtx+T2t01ZDkXq0veUwWPLm204QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://62.234.148.80:8080/mySSM/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://62.234.148.80:8080/mySSM/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "https://openapi.alipaydev.com/gateway.do";
}
