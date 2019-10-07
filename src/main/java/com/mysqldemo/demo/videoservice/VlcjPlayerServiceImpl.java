/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2015 Caprica Software Limited.
 */

package com.mysqldemo.demo.videoservice;

import com.mysqldemo.demo.VlcjPlayerFullScreenStrategy;
import com.mysqldemo.demo.event.EventService;
import com.mysqldemo.demo.event.ShutdownEvent;
import com.mysqldemo.demo.view.debug.DebugFrame;
import com.mysqldemo.demo.view.effects.EffectsFrame;
import com.mysqldemo.demo.view.main.MainFrame;
import com.mysqldemo.demo.view.messages.NativeLogFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.log.NativeLog;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import static com.mysqldemo.demo.Application.application;

/**
 * Application entry-point.
 */
@Service
public class VlcjPlayerServiceImpl implements VlcjPlayerService{

    @Value("${first.slaveServerUrl}")
    String slaveServerUrl;

    @Value("${first.slaveServerUrl1}")
    String slaveServerUrl1;

    @Value("${videoAdress}")
    String videoAdress;

    @Value("${corn}")
    String corn;

    private  MediaListPlayer mediaListPlayer = null;

    private static final Long timeLeft = Long.valueOf(2000);
    private static final Long timeRight = Long.valueOf(2000);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future1;

    private ScheduledFuture<?> future2;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private  MainFrame mainFrame;

    @SuppressWarnings("unused")
    private  JFrame messagesFrame;

    @SuppressWarnings("unused")
    private  JFrame effectsFrame;

    @SuppressWarnings("unused")
    private  JFrame debugFrame;

    private  NativeLog nativeLog;

    @Autowired
    EventService eventService;

    @Override
    public void smainV(){

        mediaListPlayer  = mainFrame.getMediaPlayer().getMediaPlayerFactory().newMediaListPlayer();
        mediaListPlayer.setMediaPlayer(mainFrame.getMediaPlayer().getMediaPlayer());
        MediaList mediaList = mainFrame.getMediaPlayer().getMediaPlayerFactory().newMediaList();
        mediaList.addMedia(videoAdress);
        mediaListPlayer.setMediaList(mediaList);
        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
        mediaListPlayer.play();
        testflag = false;
        // 拉伸
//        mainFrame.getMediaPlayer().getMediaPlayer().setScale(0);
//        mainFrame.getMediaPlayer().getMediaPlayer().setAspectRatio(1920+":"+1080);
//        this.fullC();

        // 获取屏幕宽高
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println("The width and the height of the screen are " + screenSize.getWidth() + " x " + screenSize.getHeight());
        if (StringUtils.isNotBlank(slaveServerUrl)) {

            invokeRemoteMethod(slaveServerUrl+"/StartVideoLoop");
            this.StartK("MODSET=1");
            startTimeTask();
        }

        // 从机2
//        if (StringUtils.isNotBlank(slaveServerUrl1)) {
//
//            invokeRemoteMethod(slaveServerUrl1+"/StartVideoLoop");
//        }
    }

    @Override
    public void smainVD() {

//        MarginContractEvent marginContractEvent = new MarginContractEvent("dd");
//        eventService.publish(marginContractEvent);

        this.stopTimeTask();

        if (StringUtils.isNotBlank(slaveServerUrl)) {

            invokeRemoteMethod(slaveServerUrl+"/StartVideo");
            invokeRemoteMethod(slaveServerUrl1+"/StartVideo");
//            this.StartK("MODSET=1");
            mainFrame.getMediaPlayer().getMediaPlayer().playMedia(videoAdress);
            startTimeTask();
        }
    }

    @Override
    public void smainVDWithNotRabit() {

            invokeRemoteMethod(slaveServerUrl+"/StartVideo");
            invokeRemoteMethod(slaveServerUrl1+"/StartVideo");
    }

    @Override
    public void playC() {
        mainFrame.getMediaPlayer().getMediaPlayer().play();
        invokeRemoteMethod(slaveServerUrl+"/play");
        invokeRemoteMethod(slaveServerUrl1+"/play");
        this.startTimeTask();
//        this.StartK("MODSET=CTR_MEDIA:0");
    }

    @Override
    public void mini() {

        mainFrame.setExtendedState(JFrame.ICONIFIED);
    }

    @Override
    public void pausedC() {
//        mainFrame.getMouseMovementDetector().stop();
//        application().post(PausedEvent.INSTANCE);

        this.stopTimeTask();
        mainFrame.getMediaPlayer().getMediaPlayer().pause();
        invokeRemoteMethod(slaveServerUrl+"/paused");
        invokeRemoteMethod(slaveServerUrl1+"/paused");
//        this.StartK("MODSET=CTR_MEDIA:1");

}

    @Override
    public void stopC() {

        mainFrame.getMediaPlayer().getMediaPlayer().stop();
        invokeRemoteMethod(slaveServerUrl+"/stop");
        invokeRemoteMethod(slaveServerUrl1+"/stop");
        this.stopTimeTask();
    }

    @Override
    public void fullC() {
        mainFrame.getMediaPlayer().getMediaPlayer().toggleFullScreen();
//        mainFrame.getMediaPlayer().getMediaPlayer().setScale(0);
//        mainFrame.getMediaPlayer().getMediaPlayer().stra;
    }

    @Override
    public long positionC() {
       return mainFrame.getMediaPlayer().getMediaPlayer().getTime();

    }

    @Override
    public void leftC() {
        Long timeProcess = mainFrame.getMediaPlayer().getMediaPlayer().getTime();
        mainFrame.getMediaPlayer().getMediaPlayer().setTime(timeProcess+timeLeft);
    }

    @Override
    public void rightC() {
        Long timeProcess = mainFrame.getMediaPlayer().getMediaPlayer().getTime();
        if (timeProcess > timeRight) {
            mainFrame.getMediaPlayer().getMediaPlayer().setTime(timeProcess-timeRight);
        }
    }

    @Override
    public void timeC(int time) {
//        Long timeProcess = mainFrame.getMediaPlayer().getMediaPlayer().getTime();
//        if (timeProcess > time) {
            mainFrame.getMediaPlayer().getMediaPlayer().setTime(time);
    }

    @Override
    public void nextC() {
        mediaListPlayer.playNext();
    }

    @Override
    public void vup() {
        invokeRemoteMethod(slaveServerUrl+"/vUp");
    }

    @Override
    public void vdown() {
        invokeRemoteMethod(slaveServerUrl+"/vDown");
    }

    @Override
    public void StartK(String command) {

        DatagramSocket ds = null;

        try {
            ds = new DatagramSocket(9999);  //指定自己的port
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }

        // 孔博测试
        String mes= command;
        byte[] buf = mes.getBytes();


        //1.获取ip地址
        InetAddress address = null;
        try {
            address = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket packet = new DatagramPacket(buf,buf.length, address, 10025);
        //4.发送数据
        try {
            ds.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //5.关闭socket
        ds.close();
    }


    private  boolean testflag = true;


    private void setLookAndFeel() {
        String lookAndFeelClassName;
        if (RuntimeUtil.isNix()) {
            lookAndFeelClassName = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        }
        else {
            lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        }
        try {
            UIManager.setLookAndFeel(lookAndFeelClassName);
        }
        catch(Exception e) {
            // Silently fail, it doesn't matter
        }
    }

    @Override
    public void startVideoFrame() {
        EmbeddedMediaPlayerComponent mediaPlayerComponent = application().mediaPlayerComponent();

        mainFrame = new MainFrame();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.getMediaPlayer().stop();
                mediaPlayerComponent.release();
//                if (nativeStreams != null) {
//                    nativeStreams.release();
//                }
                application().post(ShutdownEvent.INSTANCE);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }
        });
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();
        embeddedMediaPlayer.setFullScreenStrategy(new VlcjPlayerFullScreenStrategy(mainFrame));

        nativeLog = mediaPlayerComponent.getMediaPlayerFactory().newLog();
        messagesFrame = new NativeLogFrame(nativeLog);
        effectsFrame = new EffectsFrame();
        debugFrame = new DebugFrame();

        setLookAndFeel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame.setVisible(true);
            }
        });
    }

    private void invokeRemoteMethod(String url, MultiValueMap<String, Object> param) {
        RestTemplate template = new RestTemplate();
        template.postForObject(url, param, String.class);
    }
    private void invokeRemoteMethod(String url) {
        RestTemplate template = new RestTemplate();
        template.getForObject(url, String.class);
    }

    private MultiValueMap<String, Object> initParam() {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        return param;
    }

    private void startTimeTask () {

        future1 = threadPoolTaskScheduler.schedule(new MyRunnable1(),new Trigger(){
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext){
                return new CronTrigger(corn).nextExecutionTime(triggerContext);
            }
        });
//        System.out.println("DynamicTask.startCron1()");
    }

    private void startTimeTask2 () {

        future2 = threadPoolTaskScheduler.schedule(new MyRunnable1(),new Trigger(){
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext){
                return new CronTrigger(corn).nextExecutionTime(triggerContext);
            }
        });
//        System.out.println("DynamicTask.startCron2()");
    }

    private void stopTimeTask () {
        if (future1 != null) {
            future1.cancel(true);

            // todo
//            future2.cancel(true);
        }
        System.out.println("DynamicTask.stopCron1()");
    }

    class MyRunnable1 implements Runnable {
        @Override
        public void run() {
            Long timeProcess = mainFrame.getMediaPlayer().getMediaPlayer().getTime();
//            if (testflag) {
                if (timeProcess == -1) {

                    future1.cancel(true);
                }else {
                    MultiValueMap<String, Object> para = initParam();
                    para.add("time",timeProcess);

                    invokeRemoteMethod(slaveServerUrl+"/timeplay",para);

                    invokeRemoteMethod(slaveServerUrl1+"/timeplay",para);
//                    System.out.println("first DynamicTask");
                }
//            } else {
//                MultiValueMap<String, Object> para = initParam();
//                para.add("time",timeProcess);
//
//                invokeRemoteMethod(slaveServerUrl+"/timeplay",para);
////                invokeRemoteMethod(slaveServerUrl1+"/timeplay",para);
////                System.out.println("first DynamicTask");
//            }
        }
    }

    class MyRunnable2 implements Runnable {
        @Override
        public void run() {
            Long timeProcess = mainFrame.getMediaPlayer().getMediaPlayer().getTime();
            if (testflag) {
                if (timeProcess == -1) {

                    future2.cancel(true);
                }else {
                    MultiValueMap<String, Object> para = initParam();
                    para.add("time",timeProcess);

                    invokeRemoteMethod(slaveServerUrl1+"/timeplay",para);

//                    invokeRemoteMethod(slaveServerUrl1+"/timeplay",para);
//                    System.out.println("first DynamicTask");
                }
            } else {
                MultiValueMap<String, Object> para = initParam();
                para.add("time",timeProcess);

                invokeRemoteMethod(slaveServerUrl1+"/timeplay",para);
//                invokeRemoteMethod(slaveServerUrl1+"/timeplay",para);
//                System.out.println("first DynamicTask");
            }
        }
    }
}
