package com.github.fanpan26;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tio.core.Tio;
import org.tio.websocket.common.WsResponse;

/**
 * @author fanyuepan
 */
@RestController
@RequestMapping("/api/msg")
public class MessageApiController {

    @Autowired
    private TioWebSocketServerBootstrap bootstrap;

    @GetMapping("/send")
    public String sendMessageToUser(String uid,String message){
        //发送消息核心逻辑
        Tio.sendToUser(bootstrap.getServerTioConfig(),uid, WsResponse.fromText(message,"utf-8"));
        return "SUCCESS";
    }

}
