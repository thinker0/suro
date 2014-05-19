package com.netflix.suro.server;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * @author thinker0
 */
@Singleton
public class ZookeeperXDiscoveryService {
    static Logger log = LoggerFactory.getLogger(ZookeeperXDiscoveryService.class);
    private final List<Closeable> closeables = Lists.newArrayList();
    private ServiceDiscovery<String> discovery = null;
    private ServiceInstance<String> instance = null;
    private final Injector injector;
    private final ServerConfig config;

    @Inject
    public ZookeeperXDiscoveryService(ServerConfig config, Injector injector) {
        this.injector = injector;
        this.config = config;
    }

    public void start() {
        if (isPrefixZK()) {
            try {
                final URI configZookeeperUri = parseURI(config.getZookeeperUri());
                final CuratorFramework client = CuratorFrameworkFactory.newClient(configZookeeperUri.getHost(),
                        new RetryNTimes(10, 500));
                client.start();
                closeables.add(client);
                instance = ServiceInstance.<String>builder()
                        .payload("{}")
                        .name("suro")
                        .port(config.getPort())
                        .build();
                discovery = ServiceDiscoveryBuilder.builder(String.class)
                        .client(client)
                        .basePath(configZookeeperUri.getPath())
                        .thisInstance(instance)
                        .build();
                discovery.start();
                closeables.add(discovery);
            } catch (Exception e) {
                log.error("{} - {}.", e.getMessage(), config.getZookeeperUri(), e);
            }
        }
    }

    private boolean isPrefixZK() {
        return config.getZookeeperUri() != null && config.getZookeeperUri().startsWith("zk://");
    }

    private URI parseURI(String zookeeperUri) throws IllegalArgumentException {
        return URI.create(zookeeperUri);
    }

    public void shutdown() {
        if (isPrefixZK()) {
            try {
                this.discovery.unregisterService(this.instance);
            } catch (Exception e) {
                log.error("{} - {}.", e.getMessage(), this.instance, e);
            }
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    // log.trace(e.getMessage(), e);
                }
            }
        }
    }
}
