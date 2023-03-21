package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;






@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // private final PasswordEncoder passwordEncoder;

    // public SecurityConfig(PasswordEncoder passwordEncoder){
    //     this.passwordEncoder=passwordEncoder;
    // }

    @Autowired
    UserDetailsService kevinUserDetailsService;
    
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser(kevinUserDetailsService.loadUserByUsername("wfewf@fewfwe.com"));
        System.out.println("開始認證");
        auth.userDetailsService(kevinUserDetailsService);
    }

    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 詳細解說:http://cloudtu.github.io/blog/2021/03/spring-security-auth-memo.html
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("home");
        http
        //取消CSRF防護說明: https://blog.csdn.net/yjclsx/article/details/80349906
        .csrf(csrf->csrf.disable())
        .requestCache((cache) -> cache.requestCache(requestCache))
        .formLogin(login -> login.loginPage("/login")
        // 設定login頁面傳送過來的帳號密碼的參數
        .usernameParameter("username")
        .passwordParameter("pass")
        .defaultSuccessUrl("/home"))
        .authorizeHttpRequests(requests -> requests
                .requestMatchers("/login").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/vendor/**").permitAll()
                .requestMatchers("/images/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/bootstrap/**").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService(){

    //     // 此方法為spring boot啟動後，直接建立的user
    //     System.out.println("開始建立");
    //     UserDetails veraUser= User.builder()
    //     .username("vera")
    //     .password(passwordEncoder.encode("1234"))
    //     .roles("STUDENT") //ROLE_STUDENT
    //     .build();
    //     UserDetails mingUser=User.builder()
    //     .username("ming")
    //     .password(passwordEncoder.encode("1234"))
    //     .roles("admin")
    //     .build();
    //     System.out.println("我是使用者:"+veraUser);
    //     System.out.println("我是密碼:"+veraUser.getPassword());


    //     return new InMemoryUserDetailsManager(
    //         veraUser,mingUser
    //     );
    // }
}
