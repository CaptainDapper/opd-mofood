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
import com.watabou.mofoodpd.sprites.ItemSpriteSheet;

public class Pizza extends Food {

	{
		name = "pizza";
		image = ItemSpriteSheet.PIZZA;
		energy = Hunger.HUNGRY;
	}
	
	@Override
	public String info() {
		return "This slice of cheese pizza is still hot, despite being in the dungeon for a long time.";
	}
	
	@Override
	public int price() {
		return 10 * quantity;
	}
}
