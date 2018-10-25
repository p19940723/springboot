package com.seven.authority.common.utils.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author seven
 */
@Component
public class Consumer {
    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     *
     * @param text 发送的报文
     */
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:" + text);
    }
    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     *
     * @param text 发送的报文
     */
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue1(String text) {
        System.out.println("Consumer1收到的报文为:" + text);
    }
}