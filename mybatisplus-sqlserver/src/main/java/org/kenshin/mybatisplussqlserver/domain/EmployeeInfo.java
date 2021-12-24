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
 * 租户员工档案
 * @TableName EmployeeInfo
 */
@TableName(value ="EmployeeInfo")
@Data
public class EmployeeInfo implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "Id")
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "TenantId")
    private Long tenantid;

    /**
     * 创建时间
     */
    @TableField(value = "CreateTime")
    private Date createtime;

    /**
     * 登录名
     */
    @TableField(value = "LoginName")
    private String loginname;

    /**
     * 密码
     */
    @TableField(value = "UserPassword")
    private String userpassword;

    /**
     * 姓名
     */
    @TableField(value = "UserName")
    private String username;

    /**
     * 性别(-1:全部;1:男;2:女)
     */
    @TableField(value = "Sex")
    private Integer sex;

    /**
     * 部门编号
     */
    @TableField(value = "DepartmentId")
    private Long departmentid;

    /**
     * 联系电话
     */
    @TableField(value = "Phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "Email")
    private String email;

    /**
     * 地址
     */
    @TableField(value = "Address")
    private String address;

    /**
     * 职位
     */
    @TableField(value = "PostionId")
    private Long postionid;

    /**
     * 数据状态 （-1：全部；1：正常；2：作废）
     */
    @TableField(value = "DataState")
    private Integer datastate;

    /**
     * 角色编号
     */
    @TableField(value = "RoleId")
    private Long roleid;

    /**
     * 登录IP
     */
    @TableField(value = "IP")
    private String ip;

    /**
     * 上次登录时间
     */
    @TableField(value = "LastTime")
    private Date lasttime;

    /**
     * 
     */
    @TableField(value = "EmployeeType")
    private Integer employeetype;

    /**
     * 
     */
    @TableField(value = "Coefficient")
    private BigDecimal coefficient;

    /**
     * 
     */
    @TableField(value = "WechatOpenId")
    private String wechatopenid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}