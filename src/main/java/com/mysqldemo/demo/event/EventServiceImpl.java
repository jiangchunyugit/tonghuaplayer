package com.mysqldemo.demo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private ApplicationEventPublisher applicationEventPublisher;
    //上下文对象
    @Autowired
    public EventServiceImpl(ApplicationEventPublisher applicationEventPublisher) {

//        super(applicationEventPublisher);
        System.out.println(applicationEventPublisher);
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发布事件
     *
     * @param event
     * @return
     */
    @Override
    public String publish(MarginContractEvent event) {
        //TODO 事件追溯相关操作
        applicationEventPublisher.publishEvent(event);
        return "SUCCESS";
    }
}
