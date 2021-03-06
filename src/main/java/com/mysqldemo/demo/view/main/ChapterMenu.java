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

package com.mysqldemo.demo.view.main;

import uk.co.caprica.vlcj.player.MediaPlayer;
import com.mysqldemo.demo.view.OnDemandMenu;
import com.mysqldemo.demo.view.action.mediaplayer.ChapterAction;

import javax.swing.*;
import java.util.List;

import static com.mysqldemo.demo.Application.application;
import static com.mysqldemo.demo.view.action.Resource.resource;

// FIXME there's no reason this couldn't be another radiobutton menu... and show the current chapter - probably more useful that way even if not the same as VLC

final class ChapterMenu extends OnDemandMenu {

    ChapterMenu() {
        super(resource("menu.playback.item.chapter"), true);
    }

    @Override
    protected void onPrepareMenu(JMenu menu) {
        MediaPlayer mediaPlayer = application().mediaPlayerComponent().getMediaPlayer();
        List<String> chapters = mediaPlayer.getChapterDescriptions();
        if (chapters != null && !chapters.isEmpty()) {
            int i = 0;
            for (String chapter : chapters) {
                JMenuItem menuItem = new JMenuItem(new ChapterAction(chapter, mediaPlayer, i++));
                menu.add(menuItem);
            }
        }
    }
}
