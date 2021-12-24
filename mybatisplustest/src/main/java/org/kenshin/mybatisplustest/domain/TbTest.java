package org.kenshin.mybatisplustest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_test
 */
@TableName(value ="tb_test")
@Data
public class TbTest implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "a")
    private Integer a;

    /**
     * 
     */
    @TableField(value = "AA")
    private Integer aa;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "phone")
    private String phone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}