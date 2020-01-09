package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2019-12-16 18:03:17
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -91167502803690801L;

    private Integer id;

    private String username;

    private String password;


}