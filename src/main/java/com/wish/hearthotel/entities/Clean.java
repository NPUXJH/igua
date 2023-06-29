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
public class Clean implements Serializable {
//卫生情况
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private int desk;

    private int floor;

    private int bed;

    private String dorm;

    private String time;


}
