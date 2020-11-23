package me.lemon.database.model;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {
    /**
     * ID
     *
     * @mbggenerated
     */
    private Long menuId;

    /**
     * 上级菜单ID
     *
     * @mbggenerated
     */
    private Long pid;

    /**
     * 子菜单数目
     *
     * @mbggenerated
     */
    private Integer subCount;

    /**
     * 菜单类型
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 菜单标题
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 组件名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 组件
     *
     * @mbggenerated
     */
    private String component;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer menuSort;

    /**
     * 图标
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 链接地址
     *
     * @mbggenerated
     */
    private String path;

    /**
     * 是否外链
     *
     * @mbggenerated
     */
    private Boolean iFrame;

    /**
     * 缓存
     *
     * @mbggenerated
     */
    private Boolean cache;

    /**
     * 隐藏
     *
     * @mbggenerated
     */
    private Boolean hidden;

    /**
     * 权限
     *
     * @mbggenerated
     */
    private String permission;

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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Boolean getiFrame() {
        return iFrame;
    }

    public void setiFrame(Boolean iFrame) {
        this.iFrame = iFrame;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
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
        sb.append(", menuId=").append(menuId);
        sb.append(", pid=").append(pid);
        sb.append(", subCount=").append(subCount);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", name=").append(name);
        sb.append(", component=").append(component);
        sb.append(", menuSort=").append(menuSort);
        sb.append(", icon=").append(icon);
        sb.append(", path=").append(path);
        sb.append(", iFrame=").append(iFrame);
        sb.append(", cache=").append(cache);
        sb.append(", hidden=").append(hidden);
        sb.append(", permission=").append(permission);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}