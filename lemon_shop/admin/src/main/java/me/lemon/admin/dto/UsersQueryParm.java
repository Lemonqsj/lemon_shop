package me.lemon.admin.dto;

import lombok.Data;

@Data
public class UsersQueryParm {

    private int pageSize;
    private int pageNum;
    private int query;
}
