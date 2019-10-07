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

import com.mysqldemo.demo.view.OnDemandMenu;
import com.mysqldemo.demo.view.action.Resource;
import com.mysqldemo.demo.view.action.StandardAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static com.mysqldemo.demo.Application.application;
import static com.mysqldemo.demo.view.action.Resource.resource;

final class RecentMediaMenu extends OnDemandMenu {

    RecentMediaMenu(Resource resource) {
        super(resource, true);
    }

    @Override
    protected final void onPrepareMenu(JMenu menu) {
        List<String> mrls = application().recentMedia();
        if (!mrls.isEmpty()) {
            int i = 1;
            for (String mrl : mrls) {
                menu.add(new PlayRecentAction(i++, mrl));
            }
            menu.add(new JSeparator());
        }
        menu.add(new ClearRecentMediaAction());
    }

    private class PlayRecentAction extends AbstractAction {

        private final String mrl;

        public PlayRecentAction(int number, String mrl) {
            super(String.format("%d: %s", number, mrl));
            putValue(Action.MNEMONIC_KEY, number < 10 ? number : 0);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(String.format("control %d", number < 10 ? number : 0)));
            this.mrl = mrl;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            application().mediaPlayerComponent().getMediaPlayer().playMedia(mrl);
        }
    }

    private class ClearRecentMediaAction extends StandardAction {

        public ClearRecentMediaAction() {
            super(resource("menu.media.item.recent.item.clear"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            application().clearRecentMedia();
        }
    }
}
