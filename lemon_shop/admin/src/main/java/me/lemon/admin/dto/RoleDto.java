package me.lemon.admin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoleDto implements Serializable {
    private Long roleId;

    private String name;

    private Integer level;

    private String dataScope;

    private Integer total;

    /**
     * 创建者
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * 更新者
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * 创建日期
     *
     * @mbggenerated
     */
    private Date createTime;
}
