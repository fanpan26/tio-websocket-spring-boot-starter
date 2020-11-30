package com.github.fanpan26;

import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.intf.GroupListener;

/**
 * @author fanyuepan
 */
@Component
public class MyGroupListener implements GroupListener {
    @Override
    public void onAfterBind(ChannelContext channelContext, String group) throws Exception {

    }

    @Override
    public void onAfterUnbind(ChannelContext channelContext, String group) throws Exception {

    }
}
