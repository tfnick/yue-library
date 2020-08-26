package com.sample.module.common;

public interface Const {
    interface REDIS_LOCK{
        String WX_ACCESS_TOKEN = "wx_access_token";
    }

    interface REDIS_MESSAGE{
        String TOPIC = "MSG_TOPIC_TEST";
        String CONSUMER_GROUP = "CONSUMER_GROUP_TEST";
    }
}
