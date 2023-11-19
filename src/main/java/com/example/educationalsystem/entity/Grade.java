package com.example.educationalsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 17:29
 */
@Data
public class Grade {

    @TableId(value = "grade_id", type = IdType.AUTO)
    private Integer gradeId;

    @TableField("score")
    private String score;

    @TableField("course_id")
    private String courseId;

    @TableField("student_id")
    private String studentId;

}
