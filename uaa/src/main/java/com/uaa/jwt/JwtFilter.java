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
 * @description �̳�OncePerRequestFilter �����̳�Filter��ԭ���� serlvet�汾�Ĳ�ͬ�����в��죬����ʹ��spring�Ļ� ������ȽϺ�
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
            // TODO ��֤token�Ƿ�����

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    //��requestУ���Ƿ��������Լ������ǰ׺
    private boolean checkJwtToken(HttpServletRequest request) {
        String header = request.getHeader(appProperties.getJwt().getHeader());
        return header != null && header.startsWith(appProperties.getJwt().getPrefix()) ? true : false;

    }

    private Claims
}
