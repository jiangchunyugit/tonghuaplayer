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

import com.google.common.eventbus.EventBus;
import com.mysqldemo.demo.videoservice.VlcjPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import com.mysqldemo.demo.event.TickEvent;
import com.mysqldemo.demo.view.action.mediaplayer.MediaPlayerActions;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Global application state.
 */
public  class Application {

    @Autowired
    VlcjPlayerService vlcjPlayerService;
    private static final String RESOURCE_BUNDLE_BASE_NAME = "strings/vlcj-player";

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);

    private static final int MAX_RECENT_MEDIA_SIZE = 10;

    private final EventBus eventBus;

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    private final MediaPlayerActions mediaPlayerActions;

    private final ScheduledExecutorService tickService = Executors.newSingleThreadScheduledExecutor();

    private final Deque<String> recentMedia = new ArrayDeque<>(MAX_RECENT_MEDIA_SIZE);

    private static final class ApplicationHolder {
        private static final Application INSTANCE = new Application();
    }

    public static Application application() {
        return ApplicationHolder.INSTANCE;
    }

    public static ResourceBundle resources() {
        return resourceBundle;
    }

    private Application() {
        eventBus = new EventBus();
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {
            @Override
            protected String[] onGetMediaPlayerFactoryExtraArgs() {
                return new String[] {"--no-osd"};
            }
        };
        mediaPlayerActions = new MediaPlayerActions(mediaPlayerComponent.getMediaPlayer());
        tickService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                eventBus.post(TickEvent.INSTANCE);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public void subscribe(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void post(Object event)  {
        if (SwingUtilities.isEventDispatchThread()) {
            eventBus.post(event);
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    eventBus.post(event);
                }
            });
        }
    }

    public EmbeddedMediaPlayerComponent mediaPlayerComponent() {
        return mediaPlayerComponent;
    }

    public MediaPlayerActions mediaPlayerActions() {
        return mediaPlayerActions;
    }

    public void addRecentMedia(String mrl) {
        if (!recentMedia.contains(mrl)) {
            recentMedia.addFirst(mrl);
            while (recentMedia.size() > MAX_RECENT_MEDIA_SIZE) {
                recentMedia.pollLast();
            }
        }
    }

    public List<String> recentMedia() {
        return new ArrayList<>(recentMedia);
    }

    public void clearRecentMedia() {
        recentMedia.clear();
    }
}
