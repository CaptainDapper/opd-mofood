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

import java.util.ArrayList;

import com.watabou.mofoodpd.Assets;
import com.watabou.mofoodpd.Badges;
import com.watabou.mofoodpd.Statistics;
import com.watabou.mofoodpd.actors.buffs.Hunger;
import com.watabou.mofoodpd.actors.hero.Hero;
import com.watabou.mofoodpd.effects.SpellSprite;
import com.watabou.mofoodpd.items.Item;
import com.watabou.mofoodpd.sprites.ItemSpriteSheet;
import com.watabou.mofoodpd.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class Food extends Item {

	public float TIME_TO_EAT 	= 3f;
	
	public static final String AC_EAT	= "EAT";
	
	public float energy = Hunger.HUNGRY;

	public String message = "That food tasted good.";
	
	{
		stackable = true;
		name = "ration of food";
		image = ItemSpriteSheet.RATION;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_EAT );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		if (action.equals( AC_EAT )) {
			
			detach( hero.belongings.backpack );
			
			((Hunger)hero.buff( Hunger.class )).satisfy( energy );
			GLog.i( message );
			
			hero.sprite.operate( hero.pos );
			hero.busy();
			SpellSprite.show( hero, SpellSprite.FOOD );
			Sample.INSTANCE.play( Assets.SND_EAT );
			
			hero.spend( TIME_TO_EAT );
			
			Statistics.foodEaten++;
			Badges.validateFoodEaten();
			
		} else {
		
			super.execute( hero, action );
			
		}
	}
	
	@Override
	public String info() {
		return 
			"Nothing fancy here: dried meat, some biscuits - things like that.";
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public int price() {
		return 10 * quantity;
	}
}
