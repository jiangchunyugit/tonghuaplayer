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

package com.mysqldemo.demo.view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class BorderedStandardLabel extends StandardLabel {

    private static final Border STANDARD_BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createBevelBorder(BevelBorder.LOWERED),
        BorderFactory.createEmptyBorder(1, 2, 1, 2)
    );

    public BorderedStandardLabel() {
        setBorder(STANDARD_BORDER);
    }

    public BorderedStandardLabel(String template) {
        super(template);
    }
}
