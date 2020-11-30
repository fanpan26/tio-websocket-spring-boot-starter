package com.github.fanpan26;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author fanyuepan
 */
@Configuration
@Import(TioWebSocketServerInitializerConfiguration.class)
@ConditionalOnBean(TioWebSocketServerMarkerConfiguration.Marker.class)
@EnableConfigurationProperties({ TioWebSocketServerProperties.class})
public class TioWebSocketServerAutoConfiguration {

    @Autowired
    private TioWebSocketServerProperties serverProperties;

    @Bean
    public TioWebSocketServerBootstrap tioWebSocketServerBootstrap(ApplicationContext applicationContext) {
        return new TioWebSocketServerBootstrap(serverProperties, applicationContext);
    }
}
