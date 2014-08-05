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
package com.watabou.mofoodpd.items.food;

import com.watabou.mofoodpd.actors.buffs.Hunger;
import com.watabou.mofoodpd.actors.hero.Hero;
import com.watabou.mofoodpd.items.Bowl;
import com.watabou.mofoodpd.sprites.ItemSpriteSheet;
import com.watabou.mofoodpd.utils.GLog;

public class Fruit extends Food {

	{
		name = "bowl of fruit";
		image = ItemSpriteSheet.FRUIT;
		energy = Hunger.STARVING - Hunger.HUNGRY;
		message = "That food tasted ok.";
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		super.execute(hero, action);
		new Bowl().collect();
		GLog.i("You now have bowl.");
	}
	@Override
	public String info() {
		return "This bowl of fruit has a small assortment of pears, grapes, and apples.";
	}
	
	@Override
	public int price() {
		return 10 * quantity;
	}
}
