package com.wish.hearthotel.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Housemaster implements Serializable {
//宿舍管理员
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    //宿管id
    private int id;
    //宿管姓名
    private String name;
    //宿管密码
    private String pass;
    //宿管电话
    private String tel;
    //宿管年龄
    private Integer age;
    //宿管籍贯
    private String address;
    //宿管工号
    private String workno;


}
