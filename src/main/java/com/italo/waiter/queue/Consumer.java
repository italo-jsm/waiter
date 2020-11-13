package com.italo.waiter.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class Consumer {

    @JmsListener(destination = "queue.sample")
    public void onReceiverQueue(String message) {
        System.out.println(message);
    }

}
