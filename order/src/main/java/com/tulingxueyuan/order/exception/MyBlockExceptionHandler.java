package com.tulingxueyuan.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tulingxueyuan.order.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*** @author wkx
 * * @date 2024年11月21日 17:00
 *   自定义 BlockException 同一异常处理
 * */

@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, BlockException e) throws Exception {
        Logger log = LoggerFactory.getLogger(this.getClass());
        // getRule() 资源规则的详细信息
        log.info("BlockExceptionHandler BlockException================"+e.getRule());

        Result r = null;

        if (e instanceof FlowException) {
            r = Result.error(100,"接口限流了");

        } else if (e instanceof DegradeException) {
            r = Result.error(101,"服务降级了");

        } else if (e instanceof ParamFlowException) {
            r = Result.error(102,"热点参数限流了");

        } else if (e instanceof SystemBlockException) {
            r = Result.error(103,"触发系统保护规则了");

        } else if (e instanceof AuthorityException) {
            r = Result.error(104,"授权规则不通过");
        }

        //返回json数据
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), r);
    }
}
