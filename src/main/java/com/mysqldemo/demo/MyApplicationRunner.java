package com.mysqldemo.demo;

import com.mysqldemo.demo.videoservice.VlcjPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyApplicationRunner  implements CommandLineRunner {

    @Autowired
    VlcjPlayerService vlcjPlayerService;

    @Override
    public void run (String... args) throws Exception
    {
        vlcjPlayerService.startVideoFrame();
    }
}
