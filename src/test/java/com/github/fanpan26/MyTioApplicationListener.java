package com.github.fanpan26;

import org.springframework.stereotype.Component;
import org.tio.server.ServerTioConfig;

/**
 * @author fanyuepan
 */
@Component
public class MyTioApplicationListener implements TioWebSocketApplicationListener {

    @Override
    public void onConfiguration(ServerTioConfig serverTioConfig) {
        System.out.println("onConfiguration....");
    }

    @Override
    public void onCompleted(ServerTioConfig serverTioConfig) {
        System.out.println("onCompleted....");
    }

    @Override
    public void onException(Throwable throwable) {
        throw new RuntimeException(throwable);
    }
}
