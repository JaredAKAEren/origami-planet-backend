package com.shixuran.origami.controller;

import com.shixuran.origami.pojo.Profile;
import com.shixuran.origami.pojo.User;
import com.shixuran.origami.result.Result;
import com.shixuran.origami.result.ResultFactory;
import com.shixuran.origami.service.AdminService;
import com.shixuran.origami.service.ProfileService;
import com.shixuran.origami.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;

@RestController
@Api(value = "登录验证")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @Autowired
    AdminService adminService;

    @PostMapping(value = "api/login")
    @ApiOperation(value = "验证登录信息是否正确")
    public Result login(@RequestBody User requestUser) {
        //对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);

        User result = userService.isExist(username);
        if (result == null) {
            String message = "账号密码错误";
            return ResultFactory.buildFailResult(message);
        } else {
            try {
                subject.login(usernamePasswordToken);
                return ResultFactory.buildSuccessResult(result);
            } catch (AuthenticationException e) {
                String message = "账号密码错误";
                return ResultFactory.buildFailResult(message);
            }
        }
    }

    @PostMapping(value = "api/register")
    @ApiOperation(value = "注册")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        User result = userService.isExist(username);
        if (result != null) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.add(user);

        return ResultFactory.buildSuccessResult("注册成功");
    }

    @GetMapping("/api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("成功登出");
    }

    @GetMapping("/api/authentication")
    public String authentication() {
        return "身份认证成功";
    }

    @PostMapping("/api/user/profile")
    public Result saveProfile(@RequestBody @Valid Profile profile) {
        int profileId = profileService.saveOrUpdate(profile);
        if (profileId != 0) {
            return ResultFactory.buildSuccessResult(profileId);
        } else {
            return ResultFactory.buildFailResult("保存失败");
        }
    }

    @GetMapping("/api/user/profile/{id}")
    public Result getProfile(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(profileService.findByUserId(id));
    }

    @GetMapping("api/who")
    public Result getNowUser() {
        String o = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByUserName(o);
        return ResultFactory.buildSuccessResult(user.getId());
    }

    @GetMapping("api/isadmin")
    public Result isAdmin() {
        String o = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByUserName(o);
        if (adminService.isExit(user.getId())) {
            return ResultFactory.buildSuccessResult("是管理员");
        } else {
            return ResultFactory.buildFailResult("不是管理员");
        }
    }
}
