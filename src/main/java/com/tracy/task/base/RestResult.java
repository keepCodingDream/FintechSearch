package com.tracy.task.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResult {
    
    public static final RestResult SUCCESS = new RestResult("0", "OK");

    public static RestResult defaultFailure(String msg){
        return new RestResult("-1", msg);
    }
    public static SuccessValue successValue(Object value){
        return new SuccessValue(value);
    }

    private String code;
    private String msg;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SuccessValue{
        private Object value;
    }

}
