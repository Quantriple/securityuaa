package com.uaa.jwt;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TripleQ
 * @description  继承OncePerRequestFilter 而不继承Filter的原因是 serlvet版本的不同会略有差异，所以使用spring的话 用这个比较好
 * @date 2022/5/28 16:57:36
 * @VERSION 1.0
 **/
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    }
}
