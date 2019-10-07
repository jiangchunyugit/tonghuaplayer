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

package com.mysqldemo.demo;

import com.sun.jna.NativeLibrary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.log.NativeLog;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.streams.NativeStreams;
import com.mysqldemo.demo.event.ShutdownEvent;
import com.mysqldemo.demo.view.debug.DebugFrame;
import com.mysqldemo.demo.view.effects.EffectsFrame;
import com.mysqldemo.demo.view.main.MainFrame;
import com.mysqldemo.demo.view.messages.NativeLogFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.mysqldemo.demo.Application.application;

/**
 * Application entry-point.
 */

@RestController
public class VlcjPlayer {

    private static VlcjPlayer app;

    private static final NativeStreams nativeStreams;

    private  MediaListPlayer mediaListPlayer = null;

    private static final Long timeLeft = Long.valueOf(2000);
    private static final Long timeRight = Long.valueOf(2000);

    // Redirect the native output streams to files, useful since VLC can generate a lot of noisy native logs we don't care about
    // (on the other hand, if we don't look at the logs we might won't see errors)
    static {

        String local = "D:\\soft\\vlc-3.0.7.1-win64\\vlc-3.0.7.1";
        String master = "C:\\jiangchunyu\\vlc";
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), local);
//        if (RuntimeUtil.isNix()) {
//            nativeStreams = new NativeStreams("stdout.log", "stderr.log");
//        }
//        else {
            nativeStreams = null;
//        }
    }

    private  MainFrame mainFrame;

    @SuppressWarnings("unused")
    private final JFrame messagesFrame;

    @SuppressWarnings("unused")
    private final JFrame effectsFrame;

    @SuppressWarnings("unused")
    private final JFrame debugFrame;

    private final NativeLog nativeLog;

//    @PostMapping("/StartCommand")
//    public void smain() throws InterruptedException {
//        // This will locate LibVLC for the vast majority of cases
//        new NativeDiscovery().discover();
//
//        setLookAndFeel();
//
//        app = new VlcjPlayer();
////        app.start();
//
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                app.start();
//            }
//        });
//    }
//
//    @PostMapping("/StartVideo")
//    public void smainV(@RequestParam(value = "id") int id) throws InterruptedException {
////        mainFrame.getMediaPlayer().getMediaPlayer().playMedia("D:\\learning_materials\\test.mp4");
////        mainFrame.getMediaPlayer().set
////        mainFrame.getMediaPlayer().getMediaPlayer().play();
//        this.smainV1();
//        mediaListPlayer = mainFrame.getMediaPlayer().getMediaPlayerFactory().newMediaListPlayer();
//        mediaListPlayer.setMediaPlayer(mainFrame.getMediaPlayer().getMediaPlayer());
//        MediaList mediaList = mainFrame.getMediaPlayer().getMediaPlayerFactory().newMediaList();
//        mediaList.addMedia("D:\\learning_materials\\test.mp4");
//        mediaListPlayer.setMediaList(mediaList);
//        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
//        mediaListPlayer.play();
//    }
//    @PostMapping("/StartVideo1")
//    public void smainV1() throws InterruptedException {
//////        mainFrame.getMediaPlayer().getMediaPlayer().playMedia("D:\\learning_materials\\test.mp4");
//////        mainFrame.getMediaPlayer().set
////        mediaListPlayer = mainFrame.getMediaPlayer().getMediaPlayerFactory().newMediaListPlayer();
////        mediaListPlayer.setMediaPlayer(mainFrame.getMediaPlayer().getMediaPlayer());
////        MediaList mediaList = mainFrame.getMediaPlayer().getMediaPlayerFactory().newMediaList();
////        mediaList.addMedia("D:\\learning_materials\\test.mp4");
////        mediaListPlayer.setMediaList(mediaList);
////        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
////        mediaListPlayer.play();
//        mainFrame.getMediaPlayer().getMediaPlayer().playMedia("D:\\learning_materials\\test.mp4");
//    }
//
//
//    @PostMapping("/play")
//    public void playC() throws InterruptedException {
////        mainFrame.getVideoContentPane().showVideo();
////        mainFrame.getMouseMovementDetector().start();
////        application().post(PlayingEvent.INSTANCE);
//        mainFrame.getMediaPlayer().getMediaPlayer().play();
//    }
//
//    @PostMapping("/paused")
//    public void pausedC() throws InterruptedException {
////        mainFrame.getMouseMovementDetector().stop();
////        application().post(PausedEvent.INSTANCE);
//
//        mainFrame.getMediaPlayer().getMediaPlayer().pause();
//    }
//
//    @PostMapping("/stop")
//    public void stopC() throws InterruptedException {
//
////        mainFrame.getMouseMovementDetector().stop();
////        mainFrame.getVideoContentPane().showDefault();
////        application().post(StoppedEvent.INSTANCE);
////        mainFrame.getMediaPlayer().getMediaPlayer().stop();
//        mediaListPlayer.stop();
//    }
//
//    @PostMapping("/full")
//    public void fullC() throws InterruptedException {
//        mainFrame.getMediaPlayer().getMediaPlayer().toggleFullScreen();
//    }
//
//    @PostMapping("/position")
//    public long positionC() throws InterruptedException {
//       return mainFrame.getMediaPlayer().getMediaPlayer().getTime();
//
//    }
//
//    @PostMapping("/left")
//    public void leftC() throws InterruptedException {
//        Long timeProcess = mainFrame.getMediaPlayer().getMediaPlayer().getTime();
//        mainFrame.getMediaPlayer().getMediaPlayer().setTime(timeProcess+timeLeft);
//    }
//
//    @PostMapping("/right")
//    public void rightC() throws InterruptedException {
//        Long timeProcess = mainFrame.getMediaPlayer().getMediaPlayer().getTime();
//        if (timeProcess > timeRight) {
//            mainFrame.getMediaPlayer().getMediaPlayer().setTime(timeProcess-timeRight);
//        }
//    }
//
//    @PostMapping("/next")
//    public void nextC() throws InterruptedException {
//        mediaListPlayer.playNext();
//    }



    private static void setLookAndFeel() {
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

    public VlcjPlayer() {
        EmbeddedMediaPlayerComponent mediaPlayerComponent = application().mediaPlayerComponent();

        mainFrame = new MainFrame();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.getMediaPlayer().stop();
                mediaPlayerComponent.release();
                if (nativeStreams != null) {
                    nativeStreams.release();
                }
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
    }

    public void start() {
        this.mainFrame.setVisible(true);
    }
}
