/*
 * Copyright 2013 Netflix, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.netflix.suro.input;

import com.netflix.suro.ClientConfig;
import com.netflix.suro.SuroServer4Test;
import com.netflix.suro.client.SuroClient;
import com.netflix.suro.connection.TestConnectionPool;
import com.netflix.suro.message.Message;
import com.netflix.suro.queue.TestFileBlockingQueue;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TestLog4jAppender {
    private Log4jAppender appender = new Log4jAppender();
    private List<SuroServer4Test> collectors;

    @Before
    public void setup() throws Exception {
        collectors = TestConnectionPool.startServers(1, 8500);
    }

    @After
    public void clean() {
        TestConnectionPool.shutdownServers(collectors);
    }

    @Test
    public void testMemory() throws Exception {
        TestFileBlockingQueue.clean();

        appender.setLoadBalancerType("static");
        appender.setLoadBalancerServer("localhost:8500");
        appender.activateOptions();

        LoggingEvent event = mock(LoggingEvent.class);
        when(event.getMessage()).thenReturn(createEventMap());
        when(event.getLevel()).thenReturn(Level.INFO);

        appender.append(event);

        // Make sure client has enough time to drain the intermediary message queue
        Thread.sleep(5000);

        assertEquals(appender.getSentMessageCount(), 1); // it should be successful


        appender.close();
    }

    @Test
    public void testFile() throws Exception {
        TestFileBlockingQueue.clean();

        appender.setAsyncQueueType("file");
        appender.setAsyncFileQueuePath(System.getProperty("java.io.tmpdir"));
        appender.setLoadBalancerType("static");
        appender.setLoadBalancerServer("localhost:8500");
        appender.activateOptions();

        LoggingEvent event = mock(LoggingEvent.class);
        when(event.getMessage()).thenReturn(createEventMap());
        when(event.getLevel()).thenReturn(Level.INFO);

        appender.append(event);

        // Make sure client has enough time to drain the intermediary message queue
        Thread.sleep(5000);

        for (int i = 0; i < 10 && appender.getSentMessageCount() == 0; ++i) {
            Thread.sleep(1000);
        }
        assertEquals(appender.getSentMessageCount(), 1);

        appender.close();
    }

    @Test
    public void testLog4jFormatter() {
        TestFileBlockingQueue.clean();

        appender.setFormatterClass("com.netflix.suro.input.StaticLog4jFormatter");

        appender.setLoadBalancerType("static");
        appender.setLoadBalancerServer("localhost:8500");
        appender.setClientType("sync");
        appender.setRoutingKey("testRoutingKey");
        appender.activateOptions();

        appender.client = mock(SuroClient.class);
        doNothing().when(appender.client).send(any(Message.class));

        LoggingEvent event = mock(LoggingEvent.class);
        when(event.getMessage()).thenReturn("string log");
        when(event.getLevel()).thenReturn(Level.INFO);
        appender.append(event);

        ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
        verify(appender.client).send(argument.capture());
        assertEquals(argument.getValue().getRoutingKey(), "testRoutingKey");
        String[] v0 = new String(argument.getValue().getPayload()).split("\t");
        String[] v1 = new StaticLog4jFormatter(new ClientConfig()).format(event).split("\t");

        assertEquals(v0.length, v1.length);

        for (int i = 0; i < v0.length; ++i) {
            if (i == 0) {
                assertEquals(v0[0].split(":")[0], v1[0].split(":")[0]);
            } else {
                assertEquals(v0[i], v1[i]);
            }
        }
    }

    private Map<String, String> createEventMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("routingKey", "routingKey");

        return map;
    }
}
