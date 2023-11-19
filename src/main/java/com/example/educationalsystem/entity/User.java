package com.example.educationalsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/9 15:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_role")
    private String role;

}
