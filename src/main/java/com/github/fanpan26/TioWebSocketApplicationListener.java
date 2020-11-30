package com.github.fanpan26;

import org.tio.server.ServerTioConfig;

/**
 * @author fanyuepan
 */
public interface TioWebSocketApplicationListener {

    /**
     * 配置回调
     * @param serverTioConfig 服务配置
     * */
    void onConfiguration(ServerTioConfig serverTioConfig);

    /**
     * 启动完成回调
     * @param serverTioConfig 配置信息
     * */
    void onCompleted(ServerTioConfig serverTioConfig);

    /**
     * 启动异常回调
     * @param throwable 异常
     * */
    void onException(Throwable throwable);
}
