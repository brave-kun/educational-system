package com.example.educationalsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.educationalsystem.common.Result;
import com.example.educationalsystem.entity.Course;
import com.example.educationalsystem.entity.CourseChoose;
import com.example.educationalsystem.entity.Grade;
import com.example.educationalsystem.service.inter.CourseChooseService;
import com.example.educationalsystem.service.inter.CourseService;
import com.example.educationalsystem.service.inter.GradeService;
import com.example.educationalsystem.service.inter.StudentService;
import com.example.educationalsystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 21:08
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseChooseService courseChooseService;

    @Autowired
    private GradeService gradeService;

    /**
     * 查询所有开设课程
     *
     * @return
     */
    @GetMapping("/allCourse")
    public Result selectAllCourse(@RequestHeader(name = "token",required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("0")){
            return Result.error("你不是学生哦");
        }
        List<Course> courseList;
        try {
            courseList = courseService.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }
        return Result.success(courseList);
    }


    /**
     * 学生选课操作
     *
     * @param courseId
     * @param token
     * @return
     */
    @PostMapping("/{courseId}")
    public Result addCourseById(@PathVariable String courseId, @RequestHeader(name = "token", required = true) String token) {

//        Subject subject = SecurityUtils.getSubject();
//        String username = (String) subject.getPrincipal();
//
//        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
//        selectedCourseCustom.setCourseid(id);
//        selectedCourseCustom.setStudentid(Integer.parseInt(username));
//
//        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);
//
//        if (s == null) {
//            selectedCourseService.save(selectedCourseCustom);
//        } else {
//            throw new CustomException("该门课程你已经选了，不能再选");
//        }
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("0")){
            return Result.error("你不是学生哦");
        }

        try {
            //从token中获取学生学号
            String studentId = JwtUtils.getTokenInfo(token).getClaim("studentId").asString();

            CourseChoose courseChoose = new CourseChoose();
            courseChoose.setStudentId(studentId);
            courseChoose.setCourseId(courseId);
            //CourseChoose courseChoose1 = courseChooseService.getOne(new QueryWrapper<CourseChoose>().eq("student_id", studentId));
            courseChooseService.save(courseChoose);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }
        return Result.success("选课成功");
    }

    /**
     * 查询已选课程
     * @return
     */
    @GetMapping("/selectedCourse")
    public Result selectMyCourse(@RequestHeader(name = "token", required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("0")){
            return Result.error("你不是学生哦");
        }
        List<Course> MyCourseList = new ArrayList<>();

        try {
            String studentId = JwtUtils.getTokenInfo(token).getClaim("studentId").asString();
            List<CourseChoose> courseChooseList = courseChooseService.list(new QueryWrapper<CourseChoose>().eq("student_id", studentId));
            for (CourseChoose courseChoose : courseChooseList) {
                String courseId = courseChoose.getCourseId().toString();
                Course course = courseService.getOne(new QueryWrapper<Course>().eq("course_id", courseId));
                MyCourseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }

        return Result.success(MyCourseList);
    }

    /**
     * 学生退课操作
     * @param courseId
     * @return
     */
    @DeleteMapping("/deleteTheCourse/{courseId}")
    public Result deleteTheCourse(@PathVariable String courseId, @RequestHeader(name = "token",required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("0")){
            return Result.error("你不是学生哦");
        }
        try {
            String studentId = JwtUtils.getTokenInfo(token).getClaim("studentId").asString();
            courseChooseService.remove(new QueryWrapper<CourseChoose>().eq("course_id", courseId));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }
        return Result.success("退课成功");
    }

    /**
     * 学生查询所选全部课程成绩
     * @return
     */
    @GetMapping("/grades")
    public Result selectGrades(@RequestHeader(name = "token",required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("0")){
            return Result.error("你不是学生哦");
        }
        try {
            String studentId = JwtUtils.getTokenInfo(token).getClaim("studentId").asString();
            List<Grade> gradeList = gradeService.list(new QueryWrapper<Grade>().eq("student_id", studentId));
            return Result.success(gradeList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请保证正确输入");
        }

    }

}
