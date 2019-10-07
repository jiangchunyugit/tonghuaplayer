package com.mysqldemo.demo.event;


/**
 * 事件服务
 */
public interface  EventService  {
    /**
     * 发布事件
     * @param event
     * @return
     */
    String publish( MarginContractEvent event);

}
