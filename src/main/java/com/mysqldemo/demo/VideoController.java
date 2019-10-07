package com.mysqldemo.demo;

import com.mysqldemo.demo.videoservice.VlcjPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @Autowired
    VlcjPlayerService vlcjPlayerService;

    @GetMapping("/Start")
    public void smain() throws InterruptedException {

        vlcjPlayerService.startVideoFrame();
    }

    @GetMapping("/StartVideoLoop")
    public void startVideoLoop() throws InterruptedException {
        vlcjPlayerService.smainV();
    }

    @GetMapping("/StartVideo")
    public void startVideo() throws InterruptedException {
        vlcjPlayerService.smainVD();
    }

    @GetMapping("/play")
    public void playC() throws InterruptedException {
        vlcjPlayerService.playC();
    }

    @GetMapping("/paused")
    public void pausedC() throws InterruptedException {
        vlcjPlayerService.pausedC();
    }

    @GetMapping("/stop")
    public void stopC() throws InterruptedException {
        vlcjPlayerService.stopC();
    }

    @GetMapping("/full")
    public void fullC() throws InterruptedException {
        vlcjPlayerService.fullC();
    }

    @GetMapping("/position")
    public long positionC() throws InterruptedException {
        return vlcjPlayerService.positionC();
    }

    @GetMapping("/left")
    public void leftC() throws InterruptedException {
        vlcjPlayerService.leftC();
    }

    @GetMapping("/right")
    public void rightC() throws InterruptedException {
        vlcjPlayerService.rightC();
    }

    @PostMapping("/timeplay")
    public void timeC(@RequestParam(value = "time") int time) throws InterruptedException {
        vlcjPlayerService.timeC(time);
    }

    @GetMapping("/next")
    public void nextC() throws InterruptedException {
        vlcjPlayerService.nextC();
    }
    @GetMapping("/startk")
    public void startk(@RequestParam(value = "command") String command) throws InterruptedException {
        vlcjPlayerService.StartK(command);
    }

    @GetMapping("/mini")
    public void mini()  {
        vlcjPlayerService.mini();
    }
}
