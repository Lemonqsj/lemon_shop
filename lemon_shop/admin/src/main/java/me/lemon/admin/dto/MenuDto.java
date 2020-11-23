package me.lemon.admin.dto;

import lombok.Data;
import me.lemon.common.base.BaseDto;
import me.lemon.database.model.Menu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class MenuDto extends BaseDto implements Serializable {


    private Long menuId;

    private List<MenuDto> children;

    private Long pid;


    private Integer subCount;


    private Integer type;


    private String title;


    private String name;


    private String component;


    private Integer menuSort;


    private String icon;


    private String path;


    private Boolean iFrame;


    private Boolean cache;


    private Boolean hidden;


    private String permission;


    private String createBy;


    private String updateBy;


    private Date createTime;


    private Date updateTime;





}
