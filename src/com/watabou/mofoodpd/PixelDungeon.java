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

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.watabou.mofoodpd.scenes.GameScene;
import com.watabou.mofoodpd.scenes.TitleScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;

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
Firebloom doesnÕt appear more often
Wand of firebolt reduced to 5
Stomachache damages 3
Slightly higher chance of poison 
Starving hurts by depth + 1
2 food per level
Ring of Satiety and Ring of Mending do not appear
*/

public class PixelDungeon extends Game {

	public PixelDungeon() {
		super(TitleScene.class);

		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.scrolls.ScrollOfUpgrade.class,
				"com.watabou.pixeldungeon.items.scrolls.ScrollOfEnhancement");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.actors.blobs.WaterOfHealth.class,
				"com.watabou.pixeldungeon.actors.blobs.Light");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.rings.RingOfMending.class,
				"com.watabou.pixeldungeon.items.rings.RingOfRejuvenation");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.wands.WandOfTelekinesis.class,
				"com.watabou.pixeldungeon.items.wands.WandOfTelekenesis");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.actors.blobs.Foliage.class,
				"com.watabou.pixeldungeon.actors.blobs.Blooming");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.actors.buffs.Shadows.class,
				"com.watabou.pixeldungeon.actors.buffs.Rejuvenation");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.scrolls.ScrollOfPsionicBlast.class,
				"com.watabou.pixeldungeon.items.scrolls.ScrollOfNuclearBlast");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.actors.hero.Hero.class,
				"com.watabou.pixeldungeon.actors.Hero");
		// com.watabou.utils.Bundle.addAlias(
		// com.watabou.pixeldungeon.items.weapon.missiles.Javelin.class,
		// "com.watabou.pixeldungeon.items.weapon.missiles.Boomerang" );
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.actors.mobs.npcs.Shopkeeper.class,
				"com.watabou.pixeldungeon.actors.mobs.Shopkeeper");
		// 1.6.1
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.quest.DriedRose.class,
				"com.watabou.pixeldungeon.items.DriedRose");
		com.watabou.utils.Bundle
				.addAlias(
						com.watabou.mofoodpd.actors.mobs.npcs.MirrorImage.class,
						"com.watabou.pixeldungeon.items.scrolls.ScrollOfMirrorImage.MirrorImage");
		// 1.6.4
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.rings.RingOfElements.class,
				"com.watabou.pixeldungeon.items.rings.RingOfCleansing");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.rings.RingOfElements.class,
				"com.watabou.pixeldungeon.items.rings.RingOfResistance");
		com.watabou.utils.Bundle
				.addAlias(
						com.watabou.mofoodpd.items.weapon.missiles.Boomerang.class,
						"com.watabou.pixeldungeon.items.weapon.missiles.RangersBoomerang");
		com.watabou.utils.Bundle.addAlias(
				com.watabou.mofoodpd.items.rings.RingOfPower.class,
				"com.watabou.pixeldungeon.items.rings.RingOfEnergy");
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * if (android.os.Build.VERSION.SDK_INT >= 19) {
		 * getWindow().getDecorView().setSystemUiVisibility(
		 * View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
		 * View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
		 * View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
		 * View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
		 * | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ); }
		 */

		Display display = instance.getWindowManager().getDefaultDisplay();
		boolean landscape = display.getWidth() > display.getHeight();

		if (Preferences.INSTANCE.getBoolean(Preferences.KEY_LANDSCAPE, false) != landscape) {
			landscape(!landscape);
		}

		Music.INSTANCE.enable(music());
		Sample.INSTANCE.enable(soundFx());
	}

	/*
	 * ---> Prefernces
	 */

	public static void landscape(boolean value) {
		Game.instance
				.setRequestedOrientation(value ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
						: ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Preferences.INSTANCE.put(Preferences.KEY_LANDSCAPE, value);
	}

	public static boolean landscape() {
		return width > height;
	}

	public static void scaleUp(boolean value) {
		Preferences.INSTANCE.put(Preferences.KEY_SCALE_UP, value);
		switchScene(TitleScene.class);
	}

	public static boolean scaleUp() {
		return Preferences.INSTANCE.getBoolean(Preferences.KEY_SCALE_UP, true);
	}

	public static void zoom(int value) {
		Preferences.INSTANCE.put(Preferences.KEY_ZOOM, value);
	}

	public static int zoom() {
		return Preferences.INSTANCE.getInt(Preferences.KEY_ZOOM, 0);
	}

	public static void music(boolean value) {
		Music.INSTANCE.enable(value);
		Preferences.INSTANCE.put(Preferences.KEY_MUSIC, value);
	}

	public static boolean music() {
		return Preferences.INSTANCE.getBoolean(Preferences.KEY_MUSIC, true);
	}

	public static void soundFx(boolean value) {
		Sample.INSTANCE.enable(value);
		Preferences.INSTANCE.put(Preferences.KEY_SOUND_FX, value);
	}

	public static boolean soundFx() {
		return Preferences.INSTANCE.getBoolean(Preferences.KEY_SOUND_FX, true);
	}

	public static void brightness(boolean value) {
		Preferences.INSTANCE.put(Preferences.KEY_BRIGHTNESS, value);
		if (scene() instanceof GameScene) {
			((GameScene) scene()).brightness(value);
		}
	}

	public static boolean brightness() {
		return Preferences.INSTANCE.getBoolean(Preferences.KEY_BRIGHTNESS,
				false);
	}

	public static void donated(String value) {
		Preferences.INSTANCE.put(Preferences.KEY_DONATED, value);
	}

	public static String donated() {
		return Preferences.INSTANCE.getString(Preferences.KEY_DONATED, "");
	}

	public static void lastClass(int value) {
		Preferences.INSTANCE.put(Preferences.KEY_LAST_CLASS, value);
	}

	public static int lastClass() {
		return Preferences.INSTANCE.getInt(Preferences.KEY_LAST_CLASS, 0);
	}

	public static void intro(boolean value) {
		Preferences.INSTANCE.put(Preferences.KEY_INTRO, value);
	}

	public static boolean intro() {
		return Preferences.INSTANCE.getBoolean(Preferences.KEY_INTRO, true);
	}

	/*
	 * <--- Preferences
	 */

	public static void reportException(Exception e) {
		Log.e("PD", Log.getStackTraceString(e));
	}
}