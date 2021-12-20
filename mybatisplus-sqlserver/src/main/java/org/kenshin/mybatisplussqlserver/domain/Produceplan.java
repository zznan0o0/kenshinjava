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
 * 生产计划
 * @TableName ProducePlan
 */
@TableName(value ="ProducePlan")
@Data
public class Produceplan implements Serializable {
    /**
     * 
     */
    @TableId(value = "Id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "FactoryNo")
    private String factoryNo;

    /**
     * 
     */
    @TableField(value = "SalePredicateId")
    private Long salePredicateId;

    /**
     * 客户
     */
    @TableField(value = "CustomerNo")
    private String customerNo;

    /**
     * 型号
     */
    @TableField(value = "ProductModel")
    private String productModel;

    /**
     * 物料编号
     */
    @TableField(value = "MaterialCode")
    private String materialCode;

    /**
     * 物料描述
     */
    @TableField(value = "MaterialDesc")
    private String materialDesc;

    /**
     * 数量
     */
    @TableField(value = "Num")
    private BigDecimal num;

    /**
     * 
     */
    @TableField(value = "Length")
    private BigDecimal length;

    /**
     * 
     */
    @TableField(value = "Width")
    private BigDecimal width;

    /**
     * 
     */
    @TableField(value = "Thickness")
    private BigDecimal thickness;

    /**
     * 
     */
    @TableField(value = "Weight")
    private BigDecimal weight;

    /**
     * 进度
     */
    @TableField(value = "Progress")
    private BigDecimal progress;

    /**
     * 客户出货日期
     */
    @TableField(value = "PlanDeliveryTime")
    private Date planDeliveryTime;

    /**
     * 客户交货地点
     */
    @TableField(value = "PlanDeliveryAddress")
    private String planDeliveryAddress;

    /**
     * 开始生产日期
     */
    @TableField(value = "ProduceBeginTime")
    private Date produceBeginTime;

    /**
     * 结束生产日期
     */
    @TableField(value = "ProduceEndTime")
    private Date produceEndTime;

    /**
     * 状态
     */
    @TableField(value = "Status")
    private Integer status;

    /**
     * 
     */
    @TableField(value = "Remark")
    private String remark;

    /**
     * 
     */
    @TableField(value = "TenantId")
    private Long tenantId;

    /**
     * 
     */
    @TableField(value = "CreateTime")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "PriorityLevel")
    private Integer priorityLevel;

    /**
     * 
     */
    @TableField(value = "AlgorithmTaskId")
    private Long algorithmTaskId;

    /**
     * 
     */
    @TableField(value = "FinishedNum")
    private BigDecimal finishedNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}