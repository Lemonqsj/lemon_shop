package me.lemon.database.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * ID
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 部门名称
     *
     * @mbggenerated
     */
    private Long deptId;

    /**
     * 用户名
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 昵称
     *
     * @mbggenerated
     */
    private String nickName;

    /**
     * 性别
     *
     * @mbggenerated
     */
    private String gender;

    /**
     * 手机号码
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 头像地址
     *
     * @mbggenerated
     */
    private String avatarName;

    /**
     * 头像真实路径
     *
     * @mbggenerated
     */
    private String avatarPath;

    /**
     * 密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 是否为admin账号
     *
     * @mbggenerated
     */
    private Boolean isAdmin;

    /**
     * 状态：1启用、0禁用
     *
     * @mbggenerated
     */
    private Long enabled;

    /**
     * 创建者
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * 更新着
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * 修改密码的时间
     *
     * @mbggenerated
     */
    private Date pwdResetTime;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName == null ? null : avatarName.trim();
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath == null ? null : avatarPath.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
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

    public Date getPwdResetTime() {
        return pwdResetTime;
    }

    public void setPwdResetTime(Date pwdResetTime) {
        this.pwdResetTime = pwdResetTime;
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
        sb.append(", userId=").append(userId);
        sb.append(", deptId=").append(deptId);
        sb.append(", username=").append(username);
        sb.append(", nickName=").append(nickName);
        sb.append(", gender=").append(gender);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", avatarName=").append(avatarName);
        sb.append(", avatarPath=").append(avatarPath);
        sb.append(", password=").append(password);
        sb.append(", isAdmin=").append(isAdmin);
        sb.append(", enabled=").append(enabled);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", pwdResetTime=").append(pwdResetTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}