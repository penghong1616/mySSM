package com.wsk.response;

import com.wsk.handle.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class BaseResponse {
    public static BaseResponse NOT_LOGIN=new BaseResponse(-1,"未登录");
    public static  BaseResponse REPLEAT_SECKILL=new BaseResponse(-1,"不能重复秒杀");
    private int result;

    private String msg;

    public BaseResponse(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public BaseResponse(int result) {
        this.result = result;
    }

    public static BaseResponse fail() {
        return new BaseResponse(0, GlobalExceptionHandler.DEFAULT_ERROR_MESSAGE);
    }

    public static BaseResponse fail(String msg) {
        return new BaseResponse(0, msg);
    }

    public static BaseResponse fail(int result) {
        return new BaseResponse(result);
    }

    public static BaseResponse success() {
        return new BaseResponse(1, "success");
    }

    public static BaseResponse success(String msg) {
        return new BaseResponse(1, msg);
    }
}
