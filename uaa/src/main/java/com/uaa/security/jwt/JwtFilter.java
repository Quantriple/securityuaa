package com.uaa.security.jwt;

import com.uaa.config.AppProperties;
import com.uaa.util.CollectionUtil;
import com.uaa.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (checkJwtToken(httpServletRequest)) {
            // TODO 验证token是否正常
            validateToken(httpServletRequest).filter((Claims claims) -> (claims.get("authorities") != null))
                    .ifPresent(claims -> {
                        setupSpringAuthentication(claims);
                    });
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void setupSpringAuthentication(Claims claims) {
        if (claims == null) {
            SecurityContextHolder.clearContext();
        } else {
            List<?> rawList = CollectionUtil.convertObjectToList(claims.get("authorities"));
            List<SimpleGrantedAuthority> authorities = rawList.stream().map(String::valueOf).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            //构建usernamepawordauth
            UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
            //把获取到的认证放到上下文对象中
            SecurityContextHolder.getContext().setAuthentication(upt);
        }
    }

    private Optional<Claims> validateToken(HttpServletRequest request) {
        //返回jwt
        String jwtToken = request.getHeader(appProperties.getJwt().getHeader()).replace(appProperties.getJwt().getPrefix(), "");
        return Optional.of(Jwts.parserBuilder().setSigningKey(jwtUtil.getKey()).build().parseClaimsJws(jwtToken).getBody());
    }

    //从request校验是否是我们自己定义的前缀
    private boolean checkJwtToken(HttpServletRequest request) {
        String header = request.getHeader(appProperties.getJwt().getHeader());
        return header != null && header.startsWith(appProperties.getJwt().getPrefix()) ? true : false;

    }

    /*private Claims checkClaims(){

    }*/
}
