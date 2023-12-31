package com.tanhua.sso.controller;

import com.tanhua.sso.pojo.User;
import com.tanhua.sso.service.UserService;
import com.tanhua.sso.vo.ErrorResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("loginVerification")
    public ResponseEntity login(@RequestBody Map<String, String> param) {
        try {
            String mobile = param.get("phone");
            String code = param.get("verificationCode");
            String token = userService.login(mobile, code);

            if (StringUtils.isNotEmpty(token)) {
                String[] split = StringUtils.split(token, '|');
                Boolean isNew = Boolean.valueOf(split[0]);
                String tokenStr = split[1];

                Map<String, Object> map = new HashMap<>();
                map.put("isNew", isNew);
                map.put("token", tokenStr);

                return ResponseEntity.ok(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ErrorResult.ErrorResultBuilder builder = ErrorResult.builder().errCode("000000").errMessage("登录失败");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(builder.build());
    }

    /**
     * 根据token查询用户数据
     *
     * @param token
     * @return
     */
    @GetMapping("{token}")
    public User queryUserByToken(@PathVariable("token") String token) {
        return this.userService.queryUserByToken(token);
    }
}
