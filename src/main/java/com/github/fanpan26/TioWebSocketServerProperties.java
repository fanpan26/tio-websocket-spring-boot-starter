package com.github.fanpan26;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fanyuepan
 */
@ConfigurationProperties(prefix = "tio.websocket")
public class TioWebSocketServerProperties {

    /**
     * 服务名称
     * */
    private String name = "Tio-WebSocket-SpringBoot-Starter";
    /**
     * 服务绑定的 IP 地址，默认不绑定
     */
    private String ip = null;
    /**
     * 服务绑定的端口
     */
    private int port = 6789;

    /**
     * 心跳超时时间，超时会自动关闭连接 单位：毫秒
     */
    private int heartbeatTimeout = 5000;

    private SslProperties ssl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHeartbeatTimeout() {
        return heartbeatTimeout;
    }

    public void setHeartbeatTimeout(int heartbeatTimeout) {
        this.heartbeatTimeout = heartbeatTimeout;
    }

    public SslProperties getSsl() {
        return ssl;
    }

    public void setSsl(SslProperties ssl) {
        this.ssl = ssl;
    }

    public boolean useSSL() {
        return ssl != null && ssl.keyStore != null && ssl.trustStore != null;
    }

    /**
     * ssl配置
     * */
    public static class SslProperties {
        private String keyStore;
        private String trustStore;
        private String password;

        public String getKeyStore() {
            return keyStore;
        }

        public void setKeyStore(String keyStore) {
            this.keyStore = keyStore;
        }

        public String getTrustStore() {
            return trustStore;
        }

        public void setTrustStore(String trustStore) {
            this.trustStore = trustStore;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
