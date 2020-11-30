package com.github.fanpan26;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fanyuepan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TioWebSocketApplication.class)
public class TioWebSocketServerTest {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TioWebSocketServerBootstrap bootstrap;

    /**
     * 添加 @EnableTioWebSocketServer 注解才会有这个bean
     */
    @Test
    public void testTioWebSocketServerMarkerConfigurationMark() {
        TioWebSocketServerMarkerConfiguration.Marker markerBean = null;
        try {
            markerBean = applicationContext.getBean(TioWebSocketServerMarkerConfiguration.Marker.class);
        } catch (Exception e) {
        }

        Assert.assertNotNull(markerBean);
    }

    @Test
    public void testTioWebSocketServerProperties() {
        TioWebSocketServerProperties properties = applicationContext.getBean(TioWebSocketServerProperties.class);

        Assert.assertNotNull(properties);

        Assert.assertEquals(6789, properties.getPort());
        Assert.assertEquals("127.0.0.1", properties.getIp());
        Assert.assertEquals(60000, properties.getHeartbeatTimeout());
    }

    @Test
    public void testTioWebSocketServerStarted() {
        Assert.assertTrue(bootstrap.isStarted());
    }

    @Test
    public void testServerTioConfig() {
        Assert.assertNotNull(bootstrap.getServerTioConfig());
    }
}
