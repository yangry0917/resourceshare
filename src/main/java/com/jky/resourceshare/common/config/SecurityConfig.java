package com.jky.resourceshare.common.config;

import com.jky.resourceshare.common.util.security.CustomAuthenticationFilter;
import com.jky.resourceshare.common.util.security.CustomAuthenticationProvider;
import com.jky.resourceshare.common.util.MyPasswordEncoder;
import com.jky.resourceshare.service.impl.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    CustomUserServiceImpl customUserService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailHander;

    /*@Autowired
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserServiceImpl();
    }*/

    /**
     * 加密方式
     * @Author yangry
     * @CreateTime 2020/5/18 17:56
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*后续如果有其他的provider可以继续添加，如：mobileCodeAuthenticationProvider等自定义的provider*/
        //auth.authenticationProvider(authenticationProvider());
        //auth.authenticationProvider(mobileCodeAuthenticationProvider());
        auth.userDetailsService(customUserService);
        auth.authenticationProvider(customAuthenticationProvider());
    }

    //解决静态资源被拦截的问题
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/plugins/**");
        web.ignoring().antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin()
                .and()
                .csrf().disable() //关闭CSRF,开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误;否则vue前端请求不过来
                .cors().disable()//开启跨域访问
                .formLogin()//.loginPage("/login/findUserByName")
                .loginProcessingUrl("/login/findUserByName")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailHander)
                //.loginProcessingUrl("/hello")
                //.defaultSuccessUrl("/index") //成功登陆后跳转页面
                //.failureUrl("/loginError")
                .permitAll();
                //.successHandler(loginSuccessHandler);
        http.addFilterBefore(CustomAuthenticationFilter(), CustomAuthenticationFilter.class);
    }

    /*@Bean
    public AuthenticationProvider authenticationProvider() {
        //DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        *//*authenticationProvider.setUserDetailsService(customUserService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);*//*
        //return authenticationProvider;
        return new CustomAuthenticationProvider();
    }*/

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    /*@Bean
    public MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider() {
        return new MobileCodeAuthenticationProvider();
    }*/

    /*@Bean
    public MobileCodeAuthenticationProcessingFilter mobileCodeAuthenticationProcessingFilter() {
        MobileCodeAuthenticationProcessingFilter filter = new MobileCodeAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }*/

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomAuthenticationFilter CustomAuthenticationFilter() {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
