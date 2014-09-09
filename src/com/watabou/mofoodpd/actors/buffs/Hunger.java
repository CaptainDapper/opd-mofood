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
package com.watabou.mofoodpd.actors.buffs;

import com.watabou.mofoodpd.Badges;
import com.watabou.mofoodpd.Dungeon;
import com.watabou.mofoodpd.ResultDescriptions;
import com.watabou.mofoodpd.actors.hero.Hero;
import com.watabou.mofoodpd.actors.hero.HeroClass;
import com.watabou.mofoodpd.items.rings.RingOfSatiety;
import com.watabou.mofoodpd.ui.BuffIndicator;
import com.watabou.mofoodpd.utils.GLog;
import com.watabou.mofoodpd.utils.Utils;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Hunger extends Buff implements Hero.Doom {

	public static final float HUNGRY = 70f;
	public static final float STARVING = 100f;
	public static final float STEP = 2f;

		
	private static final String TXT_HUNGRY = "You are hungry.";
	private static final String TXT_STARVING = "You are starving!";
	private static final String TXT_DEATH = "You starved to death...";

	private float level;

	private static final String LEVEL = "level";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(LEVEL, level);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		level = bundle.getFloat(LEVEL);
	}

	@Override
	public boolean act() {
		if (target.isAlive()) {

			Hero hero = (Hero) target;

			if (isStarving()) {

				if (Random.Float() < 0.3f
						&& (target.HP > 1 || !target.paralysed)) {

					int StarvingDamage = ((5 + (Dungeon.depth)) / 5);
					if (Dungeon.difficulty == 1){
						StarvingDamage = 1;
					} else if (Dungeon.difficulty == 2){
						StarvingDamage = 1;
					} else if (Dungeon.difficulty == 3){
						//no change;
					} else {
						StarvingDamage++;
					}
					
					if (Dungeon.difficulty == 1) {
						if (Random.Float() < 0.15f){
							//no starvation
						} else {
							//starvation
							GLog.n(TXT_STARVING);
							hero.damage(StarvingDamage, this);
						}
					} else {
						//not on easy - starve
						GLog.n(TXT_STARVING);
						hero.damage(StarvingDamage, this);
					}
					
					// gets health as percentage, only interrupts when < 25%
					float health = (float) Dungeon.hero.HP / Dungeon.hero.HT;

					if (health < 0.25f) {
						hero.interrupt();
					}
				}
			} else {

				int bonus = 0;
				for (Buff buff : target.buffs(RingOfSatiety.Satiety.class)) {
					bonus += ((RingOfSatiety.Satiety) buff).level;
				}

				float newLevel = level + STEP - bonus;
				boolean statusUpdated = false;
				if (newLevel >= STARVING) {

					GLog.n(TXT_STARVING);
					statusUpdated = true;

					hero.interrupt();

				} else if (newLevel >= HUNGRY && level < HUNGRY) {

					GLog.w(TXT_HUNGRY);
					statusUpdated = true;

				}
				level = newLevel;

				if (statusUpdated) {
					BuffIndicator.refreshHero();
				}

			}

			float step = ((Hero) target).heroClass == HeroClass.ROGUE ? STEP * 1.2f
					: STEP;
			spend(target.buff(Shadows.class) == null ? step : step * 1.5f);

		} else {

			diactivate();

		}

		return true;
	}

	public void satisfy(float energy) {
		level -= energy;
		if (level < 0) {
			level = 0;
		} else if (level > STARVING) {
			level = STARVING;
		}

		BuffIndicator.refreshHero();
	}

	public boolean isStarving() {
		return level >= STARVING;
	}

	@Override
	public int icon() {
		if (level < HUNGRY) {
			return BuffIndicator.NONE;
		} else if (level < STARVING) {
			return BuffIndicator.HUNGER;
		} else {
			return BuffIndicator.STARVATION;
		}
	}

	@Override
	public String toString() {
		if (level < STARVING) {
			return "Hungry";
		} else {
			return "Starving";
		}
	}

	@Override
	public void onDeath() {

		Badges.validateDeathFromHunger();

		Dungeon.fail(Utils.format(ResultDescriptions.HUNGER, Dungeon.depth));
		GLog.n(TXT_DEATH);
	}
}
