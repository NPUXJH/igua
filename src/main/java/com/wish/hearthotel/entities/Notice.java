package com.wish.hearthotel.entities;

import lombok.Data;

import java.util.Date;
@Data
public class Notice {
    /*
    公告类
     */
    private int id;
    //公告信息
    private String notices;
    private Date time;
}
