package com.tulingxueyuan.sentinel_demo.controller;

/*** @author wkx * @date 2024年11月21日 10:01 */

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreaker;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wkx
 * @Description // 熔断降级规则
 **/
@RequestMapping("/degrade")
@RestController
@Slf4j
public class DegradeRuleController {

    private static final String DEGRADE_SOURCE_RULE = "degrade";

    @RequestMapping("/demo")
    @SentinelResource(value = DEGRADE_SOURCE_RULE,blockHandler = "blockHandler")
    public String degradeDemo(){
        throw new RuntimeException();
//        return "degrade demo";
    }


    public String blockHandler(BlockException exception){
        log.info("熔断");
        return "熔断降级";
    }

    /**
     * @Author wkx
     * @Description //注册 状态切换 监视器
     * @Date 10:20 2024/11/21
     **/
    @PostConstruct
    private void registerStateChangeObserver() {
        EventObserverRegistry.getInstance().addStateChangeObserver(DEGRADE_SOURCE_RULE,
                (prevState, newState, rule, snapshotValue) -> {
                    if (newState == CircuitBreaker.State.OPEN) {
                        System.err.println(String.format("%s -> OPEN at %d, snapshotValue=%.2f", prevState.name(),
                                TimeUtil.currentTimeMillis(), snapshotValue));
                    } else {
                        System.err.println(String.format("%s -> %s at %d", prevState.name(), newState.name(),
                                TimeUtil.currentTimeMillis()));
                    }
                });
    }


    /* 熔断降级规则 */
    @PostConstruct
    private void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource(DEGRADE_SOURCE_RULE);

        /*	熔断策略，支持慢调用比例/异常比例/异常数策略*/
        // DEGRADE_GRADE_RT   慢调用比例
        // DEGRADE_GRADE_EXCEPTION_RATIO 异常比例
        // DEGRADE_GRADE_EXCEPTION_COUNT 异常数
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        /* 触发熔断异常数 */
        rule.setCount(2);
        /* 出发熔断的最小请求数 */
        rule.setMinRequestAmount(2);
        /* 统计时长 默认1s  */
        rule.setStatIntervalMs(60*1000); // 时间太短不好测

        /* 熔断持续时常 单位 s ,在此时间单位内，请求会直接调用降级方法
        *   持续时常过后-半开状态，恢复接口的请求调用，若第一次请求就异常的情况下，会再次熔断，不会根据原设置的条件
        * */
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }


}
