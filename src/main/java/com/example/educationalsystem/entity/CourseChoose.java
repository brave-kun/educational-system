package com.example.educationalsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 17:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("coursechoose")
public class CourseChoose {

    @TableId(value = "choose_id", type = IdType.AUTO)
    private Integer chooseId;

    @TableField("student_id")
    private String studentId;

    @TableField("course_id")
    private String courseId;
}
