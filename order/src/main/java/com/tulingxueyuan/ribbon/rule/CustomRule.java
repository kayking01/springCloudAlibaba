package com.tulingxueyuan.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/*** @author wkx * @date 2024年11月18日 15:29 */
public class CustomRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = getLoadBalancer();
        List<Server> allServers = loadBalancer.getAllServers();
        int index = ThreadLocalRandom.current().nextInt(allServers.size());
        return allServers.get(index);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
