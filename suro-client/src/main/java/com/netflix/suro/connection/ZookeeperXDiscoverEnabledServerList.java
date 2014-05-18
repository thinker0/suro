package com.netflix.suro.connection;

import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.loadbalancer.AbstractServerList;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.details.ServiceDiscoveryImpl;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
* Created by thinker0 on 14. 5. 18..
*/
public class ZookeeperXDiscoverEnabledServerList extends AbstractServerList<DiscoveryEnabledServer> {

    private final List<Closeable>     closeables = Lists.newArrayList();
    private IClientConfig clientConfig = null;
    private ServiceDiscovery<String> discovery = null;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        this.clientConfig = clientConfig;

        final String serverList = (String) clientConfig.getProperty(CommonClientConfigKey.PrioritizeVipAddressBasedServers);
        final CuratorFramework client = CuratorFrameworkFactory.newClient(serverList, new RetryOneTime(5));
        closeables.add(client);
        client.start();

        ServiceInstance<String> instance = null;
        try {
            instance = ServiceInstance.<String>builder().payload("thing").name("test").port(10064).build();
            discovery = new ServiceDiscoveryImpl<String>(client, "/test", new JsonInstanceSerializer<String>(String.class), instance);
            closeables.add(discovery);
            discovery.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiscoveryEnabledServer> getInitialListOfServers() {
        return obtainServersViaDiscovery();
    }

    @Override
    public List<DiscoveryEnabledServer> getUpdatedListOfServers() {
        return obtainServersViaDiscovery();
    }


    private List<DiscoveryEnabledServer> obtainServersViaDiscovery() {
        final List<DiscoveryEnabledServer> serverList = new ArrayList<DiscoveryEnabledServer>();

        try {
            final Collection<ServiceInstance<String>> instances = discovery.queryForInstances("test");
            if (instances == null || instances.size() == 0) {
                return new ArrayList<DiscoveryEnabledServer>();
            }
            for (ServiceInstance<String> instance : instances) {
                // TODO
//                List<InstanceInfo> listOfinstanceInfo = discoveryClient.getInstancesByVipAddress(vipAddress, isSecure, targetRegion);
//                for (InstanceInfo ii : listOfinstanceInfo) {
//                    if (ii.getStatus().equals(InstanceInfo.InstanceStatus.UP)) {
//                        DiscoveryEnabledServer des = new DiscoveryEnabledServer(ii, isSecure);
//                        des.setZone(DiscoveryClient.getZone(ii));
//                        serverList.add(des);
//                    }
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverList;
    }
}
