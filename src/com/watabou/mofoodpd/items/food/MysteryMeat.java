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

import com.watabou.mofoodpd.Dungeon;
import com.watabou.mofoodpd.ResultDescriptions;
import com.watabou.mofoodpd.actors.buffs.Buff;
import com.watabou.mofoodpd.actors.buffs.Burning;
import com.watabou.mofoodpd.actors.buffs.Hunger;
import com.watabou.mofoodpd.actors.buffs.Paralysis;
import com.watabou.mofoodpd.actors.buffs.Poison;
import com.watabou.mofoodpd.actors.buffs.Roots;
import com.watabou.mofoodpd.actors.buffs.Slow;
import com.watabou.mofoodpd.actors.hero.Hero;
import com.watabou.mofoodpd.sprites.ItemSpriteSheet;
import com.watabou.mofoodpd.ui.BuffIndicator;
import com.watabou.mofoodpd.utils.GLog;
import com.watabou.mofoodpd.utils.Utils;
import com.watabou.utils.Random;

public class MysteryMeat extends Food {

	{
		name = "mystery meat";
		image = ItemSpriteSheet.MEAT;
		energy = Hunger.STARVING - Hunger.HUNGRY;
		message = "That food tasted... strange.";
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		int damage = 2;
		if (Dungeon.depth <=5) {
			damage = 2 + (Dungeon.difficulty - 1);
		} else if (Dungeon.depth <= 10){
			damage = 4 + (Dungeon.difficulty - 1);
		} else if (Dungeon.depth <= 15) {
			damage = 6 + (Dungeon.difficulty - 1);
		} else if (Dungeon.depth <= 20) {
			damage = 8 + (Dungeon.difficulty - 1);
		} else if (Dungeon.depth <= 25) {
			damage = 10 + (Dungeon.difficulty - 1);
		} else {
			damage = 10 + (Dungeon.difficulty - 1);
		}
		super.execute( hero, action );
		
		if (action.equals( AC_EAT )) {
			
			int theswitch = Random.Int(9);
			
			if ( theswitch == 5 ){
				theswitch = 1;
			} else if ( theswitch == 6 ){
				theswitch = 3;
			} else if ( theswitch == 7 ){
				theswitch = 4;
			} else if ( theswitch == 8 ){
				theswitch = 5;
			} else if ( theswitch == 9 ){
				theswitch = 5 - (Dungeon.difficulty + 1 );
			}
			
			switch ( theswitch ) {
			case 0:
				GLog.w( "Oh it's hot!" );
				Buff.affect( hero, Burning.class ).reignite( hero );
				break;
			case 1:
				GLog.w( "You can't feel your legs!" );
				Buff.prolong( hero, Roots.class, Paralysis.duration( hero ) );
				break;
			case 2:
				GLog.w( "You are not feeling well." );
				Buff.affect( hero, Poison.class ).set( Poison.durationFactor( hero ) * hero.HT / 2 / Poison.DOT );
				break;
			case 3:
				GLog.w( "You are stuffed." );
				Buff.prolong( hero, Slow.class, Slow.duration( hero ) );
				break;
			case 4:
				GLog.w("You have a stomach ache!");
				hero.damage ( damage , this );
				
				if (!hero.isAlive()) {
					Dungeon.fail( Utils.format( ResultDescriptions.STOMACHACHE, Dungeon.depth ) );
					GLog.n( "Your stomach ache killed you..." );
				}
				break;
			case 5:
				GLog.w("You eat the mystery meat, but it doesn't improve your hunger.");
				Hunger hunger = hero.buff( Hunger.class );
				if (hunger != null && !hunger.isStarving()) {
					hunger.satisfy( -(Hunger.STARVING-Hunger.HUNGRY) );
					BuffIndicator.refreshHero();
				}
				break;
			}
		}
	}
	
	@Override
	public String info() {
		return "Eat at your own risk!";
	}
	
	public int price() {
		return 5 * quantity;
	};
}
