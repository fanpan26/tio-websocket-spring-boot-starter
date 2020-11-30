package com.github.fanpan26;

import org.springframework.stereotype.Component;
import org.tio.server.ServerTioConfig;

/**
 * @author fanyuepan
 */
@Component
public class MyApplicationListener implements TioWebSocketApplicationListener {

    @Override
    public void onConfiguration(ServerTioConfig serverTioConfig) {
        //这里可以补充开发者自定义的一些配置信息
    }

    @Override
    public void onCompleted(ServerTioConfig serverTioConfig) {
        //server启动完成
    }

    @Override
    public void onException(Throwable throwable) {
       //当启动过程中出现异常时触发此方法
    }
}
