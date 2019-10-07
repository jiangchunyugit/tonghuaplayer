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

package com.mysqldemo.demo.view.action.mediaplayer;

import uk.co.caprica.vlcj.player.MediaPlayer;
import com.mysqldemo.demo.event.SnapshotImageEvent;
import com.mysqldemo.demo.view.action.Resource;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import static com.mysqldemo.demo.Application.application;

final class SnapshotAction extends MediaPlayerAction {

    SnapshotAction(Resource resource, MediaPlayer mediaPlayer) {
        super(resource, mediaPlayer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BufferedImage image = mediaPlayer.getSnapshot();
        if (image != null) {
            application().post(new SnapshotImageEvent(image));
        }
    }
}
