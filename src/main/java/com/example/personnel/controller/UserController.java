package com.example.personnel.controller;

import com.example.personnel.model.AdminModel;
import com.example.personnel.model.User;
import com.example.personnel.service.UserService;
import com.example.personnel.service.ValidateCodeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personnel.filter.JwtAuthenticationFilter.SECRET_KEY;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ValidateCodeService validateCodeService;

    // Secret key for signing JWT
    // 替换为你的密钥

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AdminModel request) {
        List<String> validateCode = validateCodeService.getAllCodes();
        if (!CollectionUtils.isEmpty(validateCode) && !validateCode.contains(request.getValidateCode())) {
            throw new RuntimeException("Invalid validateCode");
        }

        if (userService.findByUsername(request.getUsername()) != null) {
            return ResponseEntity.internalServerError().body("Username is already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // 登录成功，生成 JWT
            String token = generateToken(user);

            // 返回 token 和用户信息
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("token", token);

            return response;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    // 生成 JWT 的方法
    private String generateToken(User user) {
        long expirationTime = 1000 * 60 * 60 * 24; // 1 天的过期时间
        return Jwts.builder()
                .setSubject(user.getUsername()) // 设置 JWT 的主体
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用 HS256 签名算法和密钥签名
                .compact(); // 生成并压缩成 JWT 字符串
    }
}