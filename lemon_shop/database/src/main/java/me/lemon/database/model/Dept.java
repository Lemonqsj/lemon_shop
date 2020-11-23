package me.lemon.database.model;

import java.io.Serializable;
import java.util.Date;

public class Dept implements Serializable {
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

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDeptSort() {
        return deptSort;
    }

    public void setDeptSort(Integer deptSort) {
        this.deptSort = deptSort;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deptId=").append(deptId);
        sb.append(", pid=").append(pid);
        sb.append(", subCount=").append(subCount);
        sb.append(", name=").append(name);
        sb.append(", deptSort=").append(deptSort);
        sb.append(", enabled=").append(enabled);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}