package com.github.fanpan26;

/**
 * @author fanyuepan
 */
public class TioWsMsgHandlerNotFoundException extends RuntimeException {

    public TioWsMsgHandlerNotFoundException() {
        super("IMsgHandler bean not found");
    }
}
