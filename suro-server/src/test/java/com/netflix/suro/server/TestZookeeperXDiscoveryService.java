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

import com.google.inject.Injector;
import com.netflix.governator.lifecycle.LifecycleManager;
import com.netflix.suro.*;
import com.netflix.suro.routing.RoutingPlugin;
import com.netflix.suro.routing.TestMessageRouter;
import com.netflix.suro.sink.ServerSinkPlugin;
import org.junit.*;
import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author thinker0
 */
public class TestZookeeperXDiscoveryService extends ExternalResource {

    private int statusPort;
    private int serverPort;
    private AtomicReference<Injector> injector;

    @Before
    public void before() {

        try {
            statusPort = TestUtils.pickPort();
            serverPort = TestUtils.pickPort();

            Properties props = new Properties();
            props.put("SuroServer.statusServerPort", Integer.toString(statusPort));
            props.put("SuroServer.port", Integer.toString(serverPort));

            SuroServer.create(injector, props,
                    new RoutingPlugin(),
                    new ServerSinkPlugin(),
                    new SuroDynamicPropertyModule(),
                    new SuroModule());
            injector.get().getInstance(LifecycleManager.class).start();
            injector.get().getInstance(StatusServer.class).waitUntilStarted();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void startTest() {
        ZookeeperXDiscoveryService xDiscoveryService = injector.get().getInstance(ZookeeperXDiscoveryService.class);
        injector.get().getInstance(ZookeeperXDiscoveryService.class).shutdown();

        injector.get().getInstance(ZookeeperXDiscoveryService.class).start();

    }

    @After
    public void after() {
        try {
            new SuroControl().start(statusPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
