package com.yejunyu.seckill.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yejunyu
 * @Date: 2018/10/11
 * @Email: yyyejunyu@gmail.com
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;

    private T data;

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0, data);
    }

    public static <T> Result<T> failed(T data) {
        return new Result<>(1, data);
    }
}
