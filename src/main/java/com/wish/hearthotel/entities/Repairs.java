package com.wish.hearthotel.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Repairs {
    /*
    维修类
     */
    //id属性
    private int id;
    //报修情况
    private String dormitorys;
    //维修人的电话
    private String tel;
    //需要维修的宿舍
    private String dorm;
    //是否已维修
    private String whether;
    //维修好的时间
    private String time;

}
