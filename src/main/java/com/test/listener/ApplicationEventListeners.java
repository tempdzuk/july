package com.test.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationEventListeners {

    @Value("{application.roles}")
    private String[] roles;

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {


    }
}
