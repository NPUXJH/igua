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
public class Administrator implements Serializable {
//系统管理员
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String name;

    private String pass;

    private String tel;

    private Integer age;
    private String address;
    private String adminno;


}
