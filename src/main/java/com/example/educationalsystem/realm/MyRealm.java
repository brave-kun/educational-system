package com.example.educationalsystem.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.educationalsystem.entity.User;
import com.example.educationalsystem.service.inter.AdminService;
import com.example.educationalsystem.service.inter.StudentService;
import com.example.educationalsystem.service.inter.TeacherService;
import com.example.educationalsystem.service.inter.UserService;
import com.example.educationalsystem.token.JwtToken;
import com.example.educationalsystem.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 功能：
 * 作者： bravekun
 * 日期： 2023/11/18 22:06
 */

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 授权模块，获取用户角色和权限
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //System.out.println(principals.toString());
        User loginUser = (User) principals.getPrimaryPrincipal();

        String userId = ((User) principals.getPrimaryPrincipal()).getUserId();
        System.out.println(userId);
        String userId2 = JwtUtils.getUserId(principals.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户角色集
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));

        //Set<String> roleSet = user.getRole().toString();
        //simpleAuthorizationInfo.setRoles(roleSet);
        simpleAuthorizationInfo.addRole(user.getRole());
        // 获取用户权限集
        //Set<String> permissionSet = userService.getUserPermissions(username);
        //simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }




    /**
     * 用户认证
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JwtFilter 的 executeLogin 方法传递过来的，已经经过了解密
        System.out.println(authenticationToken);
        //authenticationToken.getPrincipal()
        String token = (String) authenticationToken.getPrincipal();
        String userId = JwtUtils.getUserId(token);

        if (StringUtils.isBlank(userId)) {
            throw new AuthenticationException("token校验不通过");
        }
        // 如果要实现登出逻辑需要将用户和token存储起来（redis、memcache等）这里校验token是否有效

        // 通过用户名查询用户信息，也可改为接口验证用户名是否存在（即通过登录中心验证的）
        //SysUser user = userService.getUser(username);
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
        if (user == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        /*
         * 注意这里的校验
         * token
         * username 用户名
         * secret 用户的密码
         *
         * 这里要注意secret这个字段，如果本地系统没有用户存储用户密码（即通过登录中心验证的）
         * 可以把这个值写成一个固定值，当然这样有一定的风险，或者根据一定的规则生成假的密码来验证。
         *
         */
        if (!JwtUtils.verifyBool(token)) {
            throw new AuthenticationException("token校验不通过");
        }
        return new SimpleAuthenticationInfo(user, user.getUserPassword(), this.getName());
    }
}
