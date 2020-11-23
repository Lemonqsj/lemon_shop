package me.lemon.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lemon.common.base.BaseDto;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeptDto extends BaseDto implements Serializable {


    private List<DeptDto> children;

    private Integer total;

    /**
     * ID
     *
     * @mbggenerated
     */
    private Long deptId;

    /**
     * 上级部门
     *
     * @mbggenerated
     */
    private Long pid;

    /**
     * 子部门数目
     *
     * @mbggenerated
     */
    private Integer subCount;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer deptSort;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private Boolean enabled;


}
