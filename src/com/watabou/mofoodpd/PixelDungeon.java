/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.mofoodpd;

import com.opd.opdlib.OPDGame;
import com.watabou.mofoodpd.scenes.TitleScene;

/*Info on Difficulty

Easy Mode - PD without the changes
Regeneration at 8
Increased firebloom
Firebolt at normal amount
No stomachache damage
Slightly higher chance of no effect
Starving always 1, slightly less chance of starving
4 food per level

Normal Mode - PD with some changes
Regeneration at 9
Increased fire bloom
Normal firebolt amount
Stomachache damages 1
Slightly higher chance of stomachache
Starving always 1
50% chance of 3 food, 50% chance of 4 food per level

Hard Mode - Current MFM
Regeneration at 10
Wand of firebolt reduced to 10
Stomach damages 2
Slightly higher chance of stuffed
Starving hurts by depth
3 food per level

Insane Mode - Impossible
Regeneration at 11
Firebloom doesn’t appear more often
Wand of firebolt reduced to 5
Stomachache damages 3
Slightly higher chance of poison 
Starving hurts by depth + 1
2 food per level
Ring of Satiety and Ring of Mending do not appear
*/

public class PixelDungeon extends OPDGame {

	public PixelDungeon() {
		super(TitleScene.class);
	}
}