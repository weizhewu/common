package com.waltz.bean.result;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author MrWei
 * @version 1.0.0
 * @date 2023/4/25 22:13
 * @description 统一返回实体类
 */
@Data
public class ResponseResult<T> {
    private String code;
    private T data;
    private String message;
    private String systemTime;

    private ResponseResult(){
        this.systemTime = new Timestamp(System.currentTimeMillis()).toString();
    }

    public static <T>ResponseResult<T> success(T data){
        ResponseResult<T> result = new ResponseResult<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static<T> ResponseResult<T> success(){
        ResponseResult<T> result = new ResponseResult<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(null);
        return result;
    }
    public static <T>ResponseResult<T> failure(ResultCode resultCode,T data){
        ResponseResult<T> result = new ResponseResult<>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static boolean ifSuccess(ResultCode resultCode){
        if (Objects.isNull(resultCode)) {
            return false;
        }
        return resultCode.code().equals(ResultCode.SUCCESS.code());
    }

    public static boolean ifFailure(ResultCode resultCode){
        if (Objects.isNull(resultCode)) {
            return false;
        }
        return !resultCode.code().equals(ResultCode.SUCCESS.code());
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }


}
