package com.example.educationalsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 17:18
 */
@Data
public class Teacher {
    @TableId(value = "teacher_id", type = IdType.INPUT)
    private String teacherId;

    @TableField("teacher_name")
    private String teacherName;

    @TableField("teacher_sex")
    private String teacherSex;

    @TableField("teacher_password")
    private String teacherPassword;
}
