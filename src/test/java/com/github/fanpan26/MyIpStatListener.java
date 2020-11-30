package com.github.fanpan26;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.intf.Packet;
import org.tio.core.stat.IpStat;
import org.tio.core.stat.IpStatListener;

/**
 * @author fanyuepan
 */
@Component
public class MyIpStatListener implements IpStatListener {

    @Autowired
    private TioWebSocketServerBootstrap bootstrap;

    @Override
    public void onExpired(TioConfig tioConfig, IpStat ipStat) {
        System.out.println("onExpired");
    }

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect, IpStat ipStat) throws Exception {
        System.out.println("onAfterConnected");
    }

    @Override
    public void onDecodeError(ChannelContext channelContext, IpStat ipStat) {
        System.out.println("onDecodeError");
    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess, IpStat ipStat) throws Exception {
        System.out.println("onAfterSent");
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize, IpStat ipStat) throws Exception {
        System.out.println("onAfterDecoded");
    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes, IpStat ipStat) throws Exception {
        System.out.println("onAfterReceivedBytes");
    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, IpStat ipStat, long cost) throws Exception {
        System.out.println("onAfterHandled");
    }
}
