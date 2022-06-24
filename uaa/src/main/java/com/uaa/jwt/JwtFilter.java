package com.uaa.jwt;

import com.uaa.config.AppProperties;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Stack;

/**
 * @author TripleQ
 * @description 继承OncePerRequestFilter 而不继承Filter的原因是 serlvet版本的不同会略有差异，所以使用spring的话 用这个比较好
 * @date 2022/5/28 16:57:36
 * @VERSION 1.0
 **/
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final AppProperties appProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (checkJwtToken(httpServletRequest)) {
            // TODO 验证token是否正常

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    //从request校验是否是我们自己定义的前缀
    private boolean checkJwtToken(HttpServletRequest request) {
        String header = request.getHeader(appProperties.getJwt().getHeader());
        return header != null && header.startsWith(appProperties.getJwt().getPrefix()) ? true : false;

    }

    private Claims
}
