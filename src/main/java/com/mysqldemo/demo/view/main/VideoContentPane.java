package com.mysqldemo.demo.view.main;

import com.mysqldemo.demo.view.effects.video.VideoAdjustPanel;
import com.mysqldemo.demo.view.image.ImagePane;

import javax.swing.*;
import java.awt.*;

import static com.mysqldemo.demo.Application.application;

public class VideoContentPane extends JPanel {

    private static final String NAME_DEFAULT = "default";

    private static final String NAME_VIDEO = "video";

    private final CardLayout cardLayout;

    VideoContentPane() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        add(new ImagePane(ImagePane.Mode.CENTER, getClass().getResource("/vlcj-logo.png"), 0.3f), NAME_DEFAULT);
        add(application().mediaPlayerComponent(), NAME_VIDEO);
//        add(new VideoAdjustPanel())
    }

    public void showDefault() {
        cardLayout.show(this, NAME_DEFAULT);
    }

    public void showVideo() {
        cardLayout.show(this, NAME_VIDEO);
    }
}
