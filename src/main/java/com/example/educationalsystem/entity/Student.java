package com.example.educationalsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 16:39
 */

@Data
public class Student {
    @TableId(value = "student_id", type = IdType.INPUT)
    private String studentId;

    @TableField("student_name")
    private String studentName;

    @TableField("student_sex")
    private String studentSex;

    @TableField("student_password")
    private String studentPassword;

}
