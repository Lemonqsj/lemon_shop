package me.lemon.admin.dto;

import lombok.Data;
import me.lemon.database.model.Dept;
import me.lemon.database.model.Job;
import me.lemon.database.model.Role;
import me.lemon.database.model.User;

import java.util.List;

@Data
public class UserDto {

    private User user;

    private List<JobDto> jobs;

    private List<RoleDto> rols;

    private DeptDto dept;

    private Integer total;


}
