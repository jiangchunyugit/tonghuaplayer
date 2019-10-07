package com.mysqldemo.demo;

import com.mysqldemo.demo.videoservice.VlcjPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    VlcjPlayerService vlcjPlayerService;

    @GetMapping("/test")
    public String ts() {
        vlcjPlayerService.smainVD();
        return "ok";
    }
}
