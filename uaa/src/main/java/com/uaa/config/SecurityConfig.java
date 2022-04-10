package com.uaa.config;

import org.apache.http.HttpStatus;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //super.configure(http);
        //   http.
        // http.formLogin(Customizer.withDefaults()).authorizeRequests(req-> req.mvcMatchers("/api/greeting").authenticated());
        http.authorizeRequests(req -> req.anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form.loginPage("/login").
                        successHandler((HttpServletRequest request, HttpServletResponse response, Authentication auth) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            //auth.
                        })
                        .permitAll())

        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // super.configure(web);
        //  web.ignoring().mvcMatchers();
        web.ignoring().mvcMatchers("/public/**","/api/greeting")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
