package com.tulingxueyuan.sentinel_demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.tulingxueyuan.sentinel_demo.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wkx
 * @Description // 限流规则
 *
 * @SentinelResource 改善接口中资源定义和被流控降级后的处理方法
 * 怎么使用:    1.添如依赖  <artifactId>sentinel-annotation-aspectj</artifactId>
 *            2.配置 bean-SentinelResourceAspect
 *              value定义资源
 *              blockHandler 设置流控降级后的处理方法（默认该方法必须声明在同一个类）
 *              如果不想在同一个类中blockHandlerClass 但是方法必须是static
 *              fallback 当接口出现了异常，就可以交给fallback指定的方法进行处理
 *              如果不想在同一个类中fallbackClass 但是方法必须是static
 *              blockHandler 如果和fallback同时指定了，则blockHandler优先级更高
 *              exceptionsToIgnore 排除哪些异常不处理
 **/
@RequestMapping("/flowControl")
@RestController
@Slf4j
public class FlowControlController {

    private static final String NAME_SPACE = "prod";

    /*
     * @Description
     * //缺点：
                业务侵入性很强，需要在controller中写入非业务代码.
                配置不灵活 若需要添加新的受保护资源 需要手动添加 init方法来添加流控规则
     **/
//    @RequestMapping("/add")
//    public String add(){
//        // 1.5.0 版本开始可以利用 try-with-resources 特性（使用有限制）
//        // 资源名可使用任意有业务语义的字符串，比如方法名、接口名或其它可唯一标识的字符串。
//        try (Entry entry = SphU.entry(NAME_SPACE)) {
//            // 被保护的业务逻辑
//            log.info("正常逻辑");
//            return "add";
//            // do something here...
//        } catch (BlockException ex) {
//            log.info("限流逻辑");
//            return "限流了";
//            // 资源访问阻止，被限流或被降级
//            // 在此处进行相应的处理操作
//        }
//    }

    @RequestMapping("/add")
    @SentinelResource(value = NAME_SPACE,blockHandler  = "blockHandlerForAdd"
                        ,fallback = "fallBackHandForAdd")
    public String add(){
//        int i = 1/0;
        test();
        log.info("正常逻辑");
        return "add";
    }
    /**
     * @Author wkx
     * @Description  这些函数 返回值 要和源函数一致
     * **/
    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String blockHandlerForAdd(BlockException ex){
        log.info("限流逻辑");
        return "限流了";
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String fallBackHandForAdd(Throwable  ex){
        log.info("异常错误逻辑");
        return "限流了";
    }
    // 这里单独演示 blockHandlerClass 的配置. fallbackClass 同理
    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
    @SentinelResource(value = NAME_SPACE, blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class},fallback = "fallBackHandForAdd")
    @RequestMapping("/test")
    public void test() {
        System.out.println("Test");
    }

    // 硬编码方式定义
    /* 3. 设置 限流规则*/
    @PostConstruct
    private void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule(NAME_SPACE);
        // set limit qps to 20
        /* 匀速排队流控：count 代表 一秒匀速通过的请求数量
        * 比如count = 10，也就是每个请求平均间隔恒定为 1000 / 10 = 100 ms
        *  */
        rule.setCount(1);
        // FLOW_GRADE_THREAD  线程数
        // FLOW_GRADE_QPS     QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");

        /* QBS 模式下 的参数
        流控效果
        * 1. 直接拒绝：RuleConstant.CONTROL_BEHAVIOR_DEFAULT
        *
        * 2. Warm Up 预热/冷启动方式: RuleConstant.CONTROL_BEHAVIOR_WARM_UP
        *   warmUpPeriodSec 代表期待系统进入稳定状态的时间（即预热时长）
        *   当流量突然增大的时候，我们常常会希望系统从空闲状态到繁忙状态的切换的时间长一些。
        *   即如果系统在此之前长期处于空闲的状态，我们希望处理请求的数量是缓步的增多，
        *   经过预期的时间以后，到达系统处理请求个数的最大值。
        *
        * 3. 匀速排队: RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER
        *    严格控制请求通过的间隔时间，也即是让请求以均匀的速度通过，对应的是漏桶算法。
        *    注意：匀速排队模式暂时不支持 QPS > 1000 的场景。
        *    应用场景：如果系统使用 Apache RocketMQ 来收发消息，系统在某个时间突然收到大量消息。
        *       我们希望以固定的速率来处理消息，而不是一下子拒绝这些消息。
        *       这个时候可以使用匀速器，也就是给消息排队。
        * */
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);

        /* 预热/冷启动方式 模式下的参数, 代表 期待 系统进入 稳定状态的时间（即预热时长） */
//        rule.setWarmUpPeriodSec(10); // 默认值 10

        /* 匀速排队 模式下，的最长等待时间 */
        rule.setMaxQueueingTimeMs(20 * 1000); // 最大等待时长 20s

        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
