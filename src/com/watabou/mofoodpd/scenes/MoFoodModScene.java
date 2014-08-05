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
package com.watabou.mofoodpd.scenes;

import com.watabou.mofoodpd.ui.Icons;
import com.watabou.mofoodpd.windows.WndTitledMessage;
import com.watabou.noosa.Game;

public class MoFoodModScene extends PixelScene {

	private static final String TEXT = 	
		"Welcome to the Mo' Food Mod for Pixel Dungeon! \n\n" +
		"This mod includes many changes related to food in the game. There are new foods, " +
		"more food spawns, almost all monsters drop mystery meat, starving is buffed, and more.Ê\n\n" +
		"For more information and a full list of changes, view the README on Github. \n\n" +
		"Thank you for playing the Mo' Food Mod, and I hope you enjoy playing it as much as I did " +
		"making it. \n\n" +
		"-roastedlasagna";
	private static final String TITLE = "Mo' Food Mod";
	
	@Override
	public void create() {
		super.create();
		
		add( new WndTitledMessage( Icons.INFO.get(), TITLE, TEXT ) {
			@Override
			public void hide() {
				super.hide();
				Game.switchScene( InterlevelScene.class );
			}
		} );
		
		fadeIn();
	}
}
