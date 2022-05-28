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
 * @description  �̳�OncePerRequestFilter �����̳�Filter��ԭ���� serlvet�汾�Ĳ�ͬ�����в��죬����ʹ��spring�Ļ� ������ȽϺ�
 * @date 2022/5/28 16:57:36
 * @VERSION 1.0
 **/
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    }
}
