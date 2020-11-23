package me.lemon.database.model;

import java.io.Serializable;

public class UsersAndJobs implements Serializable {
    /**
     * 用户ID
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 岗位ID
     *
     * @mbggenerated
     */
    private Long jobId;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", jobId=").append(jobId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}