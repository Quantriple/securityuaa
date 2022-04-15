package com.uaa.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * @author TripleQ
 * @description 统一异常控制
 * @date 2022/4/15 15:04:10
 * @VERSION 1.0
 **/
@ControllerAdvice
public class ExceptionHandler implements ProblemHandling {


    //是否输出堆栈异常 生产上不建议输出
    @Override
    public boolean isCausalChainsEnabled() {
        return true;
    }
}
