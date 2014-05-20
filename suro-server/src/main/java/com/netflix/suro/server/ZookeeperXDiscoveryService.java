/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.suro.server;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.netflix.suro.jackson.DefaultObjectMapper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
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
    private static Logger log = LoggerFactory.getLogger(ZookeeperXDiscoveryService.class);
    private final List<Closeable> closeables;
    private final Injector injector;
    private final ServerConfig config;
    private ServiceDiscovery<String> serviceDiscovery = null;
    private ServiceInstance<String> thisInstance = null;

    @Inject
    public ZookeeperXDiscoveryService(ServerConfig config, Injector injector) {
        this.config = config;
        this.injector = injector;
        this.closeables = Lists.newArrayList();
    }

    public void start() {
        if (isPrefixZK()) {
            try {
                final URI configZookeeperUri = parseURI(config.getZookeeperUri());
                final CuratorFramework client = CuratorFrameworkFactory.newClient(configZookeeperUri.getHost(),
                        new RetryNTimes(10, 500));
                client.start();
                closeables.add(client);

                final UriSpec uriSpec = new UriSpec("{scheme}://{address}:{port}");
                final DefaultObjectMapper objectMapper = injector.getInstance(DefaultObjectMapper.class);
                thisInstance = ServiceInstance.<String>builder()
                        .name("suro")
                        .payload(objectMapper.writeValueAsString(config))
                        .port(config.getPort())
                        .uriSpec(uriSpec)
                        .build();
                serviceDiscovery = ServiceDiscoveryBuilder.<String>builder(String.class)
                        .client(client)
                        .basePath(configZookeeperUri.getPath())
                        .thisInstance(thisInstance)
                        .build();
                serviceDiscovery.start();
                closeables.add(serviceDiscovery);
            } catch (Exception e) {
                log.error("{} - {} {}", e.getMessage(), config.getZookeeperUri());
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
                this.serviceDiscovery.unregisterService(this.thisInstance);
            } catch (Exception e) {
                log.error("{} - {}.", e.getMessage(), this.thisInstance);
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
