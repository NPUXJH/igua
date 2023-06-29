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
public class Student implements Serializable {
//学生
    private static final long serialVersionUID=1L;
//个人信息（id、姓名、宿舍、学号、年龄、电话、入学日、毕业日）
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String name;

    private String pass;

    private String dorm;

    private String studentno;

    private Integer age;

    private String tel;

    private String admission;

    private String graduation;


}
