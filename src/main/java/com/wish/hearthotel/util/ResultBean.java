package com.wish.hearthotel.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;      //未登录

    public static final int SUCCESS = 0;        //成功

    public static final int FAIL = 1;           //失败

    public static final int NO_PERMISSION = 2;  //没有权限

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }
//传给前台数据
    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
    //设置状态码
    public ResultBean(Integer flag) {
    	if(flag==2) {
    		this.code=NO_PERMISSION;
    	}else if(flag==-1) {
    		this.code=NO_LOGIN;
    	}else if(flag==0){
    		this.code=SUCCESS;
    	}else {
    		this.code=FAIL;
    	}
    	
    }
}
