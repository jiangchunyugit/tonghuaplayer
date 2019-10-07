package com.mysqldemo.demo.event.listener;

import com.mysqldemo.demo.event.MarginContractEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author jiangchunyu(后台)
 * @date 20181109
 * @Description远程端同步数据监听器
 */
@Component
public class MarginContractListener  {


    /**
     * 同步合同数据
     */
    @EventListener
    @Async
    public void marginContract(MarginContractEvent marginContractEvent){

        while (true) {

            System.out.println("ceshi");
        }

//        String[] contractID = marginContractEvent.getSource().split("@");
//        System.out.print("yibuma");
//        RestTemplate t = new RestTemplate();


    }
}
