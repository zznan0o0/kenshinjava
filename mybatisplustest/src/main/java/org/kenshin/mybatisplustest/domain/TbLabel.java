package org.kenshin.mybatisplustest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签表
 * @TableName Tb_Label
 */
@TableName(value ="Tb_Label")
@Data
public class TbLabel implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名称
     */
    @TableField(value = "LableName")
    private String lableName;

    /**
     * 属性类型：0-基本属性，1-行为属性，2-交易属性
     */
    @TableField(value = "Attribute")
    private Integer attribute;

    /**
     * 客户类型：0-通用，1-贸易商，2-中小型
     */
    @TableField(value = "ClientType")
    private Integer clientType;

    /**
     * 添加人
     */
    @TableField(value = "UserId")
    private Integer userId;

    /**
     * 删除状态,删除：1,未删除：0
     */
    @TableField(value = "IsDel")
    private Integer isDel;

    /**
     * 创建时间
     */
    @TableField(value = "CreatedAt")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "UpdatedAt")
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}