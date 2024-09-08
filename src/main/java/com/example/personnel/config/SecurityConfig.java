package com.example.personnel.config;

import com.example.personnel.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 无状态，不创建 session
                .and()
                .authorizeHttpRequests((authz) -> authz
                        // 允许所有的 GET 请求不需要认证
                        .requestMatchers("GET", "/api/personnel/**").permitAll()
                        // 其他请求方式（POST、PUT、DELETE等）需要认证
                        .requestMatchers("/api/personnel/**").authenticated()
                        .anyRequest().permitAll()
                );

        // 添加 JWT 过滤器，在 UsernamePasswordAuthenticationFilter 之前执行
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build(); // 返回配置完成的 SecurityFilterChain
    }

    // 定义 AuthenticationManager Bean，用于处理认证
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }
}