package com.netflix.suro.connection;

import com.google.inject.Inject;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.governator.guice.lazy.LazySingleton;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.suro.ClientConfig;

/**
 *
 * @author thinker0
 */
@LazySingleton
public class ZookeeperLoadBalancer extends DynamicServerListLoadBalancer {

    /**
     * @param config contains vipAddress
     */
    @Inject
    public ZookeeperLoadBalancer(ClientConfig config) {
        String[] serverList = config.getLoadBalancerServer().split(",");
        if (serverList.length > 0) {
            throw new IllegalArgumentException(String.format(
                    "ZookeeperLoadBalancer server should be formatted vipAddress:port ('%s')",
                    config.getLoadBalancerServer()));
        }

        IClientConfig loadBalancerConfig = new DefaultClientConfigImpl();
        loadBalancerConfig.loadProperties("suroClient");
        loadBalancerConfig.setProperty(CommonClientConfigKey.ConnectTimeout, config.getConnectionTimeout());
        loadBalancerConfig.setProperty(CommonClientConfigKey.PrioritizeVipAddressBasedServers, config.getLoadBalancerServer());
        loadBalancerConfig.setProperty(CommonClientConfigKey.NIWSServerListClassName, ZookeeperXDiscoverEnabledServerList.class.getName());
        super.initWithNiwsConfig(loadBalancerConfig);
    }

}
