package me.lemon.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lemon.common.base.BaseDto;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class JobDto extends BaseDto implements Serializable {


    private Long id;

    private Integer jobSort;

    private String name;

    private Boolean enabled;

    private Integer total;

    public JobDto(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
