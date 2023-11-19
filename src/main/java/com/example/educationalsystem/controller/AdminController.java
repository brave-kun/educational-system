package com.example.educationalsystem.controller;

import com.example.educationalsystem.common.Result;
import com.example.educationalsystem.entity.Student;
import com.example.educationalsystem.entity.Teacher;
import com.example.educationalsystem.service.inter.StudentService;
import com.example.educationalsystem.service.inter.TeacherService;
import com.example.educationalsystem.utils.JwtUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/8 19:46
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询全部学生
     * @return
     */
    @GetMapping("/selectStudent")
    //@RequiresRoles("2")
    public Result selectAllStudent(@RequestHeader(name = "token",required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        List<Student> studentList;
        try {
            studentList = studentService.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("查询失败");
        }
        return Result.success(studentList);
    }

    /**
     *新增一个学生
     * @param student
     * @return
     */
    @PostMapping("/addStudent")
    //@RequiresRoles("2")
    public Result addStudent(@RequestHeader(name = "token",required = true) String token, @RequestBody Student student) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        try {
            studentService.save(student);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 通过学生学号删除学生
     * @param studentId
     * @return
     */
    @DeleteMapping("/deleteStudent/{studentId}")
    //@RequiresRoles("2")
    public Result deleteStudentById(@RequestHeader(name = "token",required = true) String token, @PathVariable String studentId) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        try {
            studentService.removeById(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

    /**
     * 修改某个学生的信息
     * @param student
     * @return
     */
    @PutMapping("/updateStudent")
    //@RequiresRoles("admin")
    public Result updateStudent(@RequestHeader(name = "token",required = true) String token, @RequestBody Student student) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        try {
            studentService.updateById(student);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

    /**
     * 查询全部老师
     * @return
     */
    @GetMapping("/selectTeacher")
    //@RequiresRoles("admin")
    public Result selectAllTeacher(@RequestHeader(name = "token",required = true) String token) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        List<Teacher> teacherList;
        try {
            teacherList = teacherService.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("查询失败");
        }
        return Result.success(teacherList);
    }

    /**
     *新增一个老师
     * @param teacher
     * @return
     */
    @PostMapping("/addTeacher")
    //@RequiresRoles("admin")
    public Result addTeacher(@RequestHeader(name = "token",required = true) String token, @RequestBody Teacher teacher) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        try {
            teacherService.save(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 通过教师编号删除教师
     * @param teacherId
     * @return
     */
    @DeleteMapping("/deleteTeacher/{teacherId}")
    //@RequiresRoles("admin")
    public Result deleteTeacherById(@RequestHeader(name = "token",required = true) String token, @PathVariable String teacherId) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        try {
            teacherService.removeById(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

    /**
     * 修改某个教师的信息
     * @param teacher
     * @return
     */
    @PutMapping("/updateTeacher")
    //@RequiresRoles("admin")
    public Result updateTeacher(@RequestHeader(name = "token",required = true) String token, @RequestBody Teacher teacher) {
        String role = JwtUtils.getTokenInfo(token).getClaim("role").asString();
        if(!role.equals("2")){
            return Result.error("你不是管理员哦");
        }
        try {
            teacherService.updateById(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

}
