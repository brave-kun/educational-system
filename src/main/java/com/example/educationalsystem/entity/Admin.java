package com.example.educationalsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 17:21
 */
@Data
public class Admin {
    @TableId(value = "admin_id", type = IdType.INPUT)
    private String adminId;

    @TableField("admin_password")
    private String adminPassword;

}
