package com.mysqldemo.demo;

import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;
import com.mysqldemo.demo.event.AfterExitFullScreenEvent;
import com.mysqldemo.demo.event.BeforeEnterFullScreenEvent;

import java.awt.*;

import static com.mysqldemo.demo.Application.application;

public class VlcjPlayerFullScreenStrategy extends DefaultAdaptiveRuntimeFullScreenStrategy {

    public VlcjPlayerFullScreenStrategy(Window window) {
        super(window);
    }

    @Override
    public void beforeEnterFullScreen() {
        application().post(BeforeEnterFullScreenEvent.INSTANCE);
    }

    @Override
     public void afterExitFullScreen() {
        application().post(AfterExitFullScreenEvent.INSTANCE);
    }
}
