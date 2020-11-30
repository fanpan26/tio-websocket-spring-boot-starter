package com.github.fanpan26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.tio.core.DefaultTioUuid;
import org.tio.core.intf.GroupListener;
import org.tio.core.intf.TioUuid;
import org.tio.core.ssl.SslConfig;
import org.tio.core.stat.IpStatListener;
import org.tio.server.ServerTioConfig;
import org.tio.server.TioServer;
import org.tio.utils.Threads;
import org.tio.websocket.server.WsServerConfig;
import org.tio.websocket.server.WsServerStarter;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author fanyuepan
 */
public class TioWebSocketServerBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(TioWebSocketServerBootstrap.class);

    private AtomicBoolean started = new AtomicBoolean(false);

    private ApplicationContext applicationContext;

    private TioUuid uuid;

    private IWsMsgHandler msgHandler;
    private GroupListener groupListener;
    private IpStatListener ipStatListener;

    private WsServerConfig config;
    private WsServerStarter serverStarter;
    private TioWebSocketApplicationListener applicationListener;
    private TioWebSocketServerProperties properties;

    private ServerTioConfig tioConfig;

    public final ServerTioConfig getServerTioConfig() {
        return tioConfig;
    }

    public TioWebSocketServerBootstrap(TioWebSocketServerProperties properties,ApplicationContext context) {
        this.properties = properties;
        this.applicationContext = context;
    }

    public final void start() {
        if (started.get() == false) {
            try {
                doInit();
                doStart();
                started.compareAndSet(false,true);
            } catch (Exception e) {
                applicationListener.onException(e);
            }
        }
    }

    private void doInit() throws Exception {
        initWsConfig();

        initOptionalBeans();
        initRequiredBeans();

        initServerStarter();
        initServerTioConfig();
    }

    private void initWsConfig() {
        config = new WsServerConfig(properties.getPort());
        config.setBindIp(properties.getIp());
    }

    private void initServerStarter() throws IOException {

        serverStarter = new WsServerStarter(config,
                msgHandler,
                uuid,
                Threads.getTioExecutor(),
                Threads.getGroupExecutor());

    }

    private void initOptionalBeans() {
        uuid = getBean(TioUuid.class);
        groupListener = getBean(GroupListener.class);
        ipStatListener = getBean(IpStatListener.class);
        applicationListener = getBean(TioWebSocketApplicationListener.class);

        if (uuid == null){
            uuid = new DefaultTioUuid();
        }
        if (applicationListener == null) {
            applicationListener = new TioWebSocketApplicationListener() {
                @Override
                public void onConfiguration(ServerTioConfig serverTioConfig) {

                }

                @Override
                public void onCompleted(ServerTioConfig serverTioConfig) {

                }

                @Override
                public void onException(Throwable throwable) {
                    logger.error("Error occurred while initializing TioWebSocketServer", throwable);
                }
            };
        }
    }

    private void initRequiredBeans() {
        msgHandler = getBean(IWsMsgHandler.class);
        if (msgHandler == null) {
            throw new TioWsMsgHandlerNotFoundException();
        }
    }

    private <T> T getBean(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            logger.info("No bean of type [" + clazz.getName() + "] registered");
            return null;
        }
    }

    private void initServerTioConfig() throws Exception{
        tioConfig = serverStarter.getServerTioConfig();
        tioConfig.setName(properties.getName());
        tioConfig.setHeartbeatTimeout(properties.getHeartbeatTimeout());
        tioConfig.setGroupListener(groupListener);
        if (ipStatListener != null) {
            tioConfig.ipStats.addDuration(60L);
            tioConfig.setIpStatListener(ipStatListener);
        }
        if (properties.useSSL()) {
            TioWebSocketServerProperties.SslProperties ssl = properties.getSsl();
            tioConfig.setSslConfig(SslConfig.forServer(ssl.getKeyStore(), ssl.getTrustStore(), ssl.getPassword()));
        }

        applicationListener.onConfiguration(tioConfig);
    }

    private void doStart() throws IOException {
        closeCheckVersion();
        serverStarter.start();
        applicationListener.onCompleted(tioConfig);
    }

    /**
     * 用户使用时间接依赖版本，所以不做检查
     * */
    private void closeCheckVersion() {
        try {
            TioServer server = serverStarter.getTioServer();
            Field field = server.getClass().getDeclaredField("checkLastVersion");
            field.setAccessible(true);
            field.set(server, false);
        } catch (Exception e) { }
    }

    public boolean isStarted() {
        return started.get();
    }
}
