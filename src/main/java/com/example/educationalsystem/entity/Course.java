package com.example.educationalsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 17:24
 */
@Data
public class Course {
    @TableId(value = "course_id", type = IdType.INPUT)
    private String courseId;

    @TableField("course_name")
    private String courseName;

    @TableField("teacher_id")
    private String teacherId;

    @TableField("teacher_name")
    private String teacherName;
}
