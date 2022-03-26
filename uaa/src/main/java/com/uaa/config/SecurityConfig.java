package com.uaa.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //   http.
        // http.formLogin(Customizer.withDefaults()).authorizeRequests(req-> req.mvcMatchers("/api/greeting").authenticated());
        http.csrf(csrf -> csrf.disable()).
                httpBasic(Customizer.withDefaults()).
                formLogin(form -> form.loginPage("/")).
                authorizeRequests(req -> req.mvcMatchers("/api/**").authenticated());
    }
}
