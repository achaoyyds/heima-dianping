package com.atguigu;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
     * 全局统一返回结果类
     *
     */
    @Data
    @ApiModel(value = "全局统一返回结果")
    @AllArgsConstructor
    @NoArgsConstructor
    public class R<T> {

        @ApiModelProperty(value = "返回码")
        private Integer code;

        @ApiModelProperty(value = "返回消息")
        private String message;

        @ApiModelProperty(value = "返回数据")
        private T data;

        public static <T> R<T> ok(T data) {
            R<T> result = new R<T>();
            if (data != null) {
                result.setData(data);
            }
            result.setCode(20000);
            result.setMessage("成功");
            return result;
        }


    public static<T> R<T> fail(String message){
        R<T> result = new R<>();
        result.setCode(20001);
        result.setMessage(message);
        return result;
    }

    public R<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    }

