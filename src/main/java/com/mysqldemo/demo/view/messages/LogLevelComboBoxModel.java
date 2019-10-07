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

package com.mysqldemo.demo.view.messages;

import uk.co.caprica.vlcj.binding.internal.libvlc_log_level_e;

import javax.swing.*;

@SuppressWarnings("serial")
final class LogLevelComboBoxModel extends DefaultComboBoxModel<libvlc_log_level_e> {

    LogLevelComboBoxModel() {
        for(libvlc_log_level_e value : libvlc_log_level_e.values()) {
            addElement(value);
        }
    }
}
