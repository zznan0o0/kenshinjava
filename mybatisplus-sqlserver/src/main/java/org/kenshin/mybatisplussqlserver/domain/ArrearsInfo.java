package org.kenshin.mybatisplussqlserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ArrearsInfo
 */
@TableName(value ="ArrearsInfo")
@Data
public class ArrearsInfo implements Serializable {
    /**
     *  编号
     */
    @TableId(value = "Id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "ArrearsNo")
    private String arrearsno;

    /**
     * 创建人
     */
    @TableField(value = "CreatorName")
    private String creatorname;

    /**
     * 创建时间
     */
    @TableField(value = "CreateTime")
    private Date createtime;

    /**
     * 修改人
     */
    @TableField(value = "ModifierName")
    private String modifiername;

    /**
     * 修改时间
     */
    @TableField(value = "ModifyTime")
    private Date modifytime;

    /**
     * 租户ID
     */
    @TableField(value = "TenantId")
    private Long tenantid;

    /**
     * 客户编号
     */
    @TableField(value = "CustomerId")
    private Long customerid;

    /**
     * 
     */
    @TableField(value = "Remark")
    private String remark;

    /**
     * 
     */
    @TableField(value = "Money")
    private BigDecimal money;

    /**
     * 
     */
    @TableField(value = "ArrearsTime")
    private Date arrearstime;

    /**
     * 数据状态 （-1：全部；1：正常；2：作废）
     */
    @TableField(value = "DataState")
    private Integer datastate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}