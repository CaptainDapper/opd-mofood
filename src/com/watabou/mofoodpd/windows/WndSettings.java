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
package com.watabou.mofoodpd.windows;

import com.watabou.mofoodpd.Assets;
import com.watabou.mofoodpd.PixelDungeon;
import com.watabou.mofoodpd.scenes.PixelScene;
import com.watabou.mofoodpd.scenes.StartScene;
import com.watabou.mofoodpd.ui.CheckBox;
import com.watabou.mofoodpd.ui.RedButton;
import com.watabou.mofoodpd.ui.Window;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;

public class WndSettings extends Window {
	
	private static final String TXT_ZOOM_IN			= "+";
	private static final String TXT_ZOOM_OUT		= "-";
	private static final String TXT_ZOOM_DEFAULT	= "Default Zoom";

	private static final String TXT_SCALE_UP		= "Scale up UI";
	
	private static final String TXT_MUSIC	= "Music";
	
	private static final String TXT_SOUND	= "Sound FX";
	
	private static final String TXT_BRIGHTNESS	= "Brightness";
	
	private static final String TXT_SWITCH_PORT	= "Switch to portrait";
	private static final String TXT_SWITCH_LAND	= "Switch to landscape";
	
	private static String TXT_NIGHT_DISPLAY			= "Night Mode Default";	
	private static final String TXT_NIGHT_ON			= "Night Mode On";
	private static final String TXT_NIGHT_OFF			= "Night Mode Off";
	private static final String TXT_NIGHT_DEFAULT		= "Night Mode Default";
	private RedButton btnCycleDownNight;
	private RedButton btnCycleUpNight;
	RedButton btnNightDisplay;
	private static final String TXT_CYCLE_UP			= "+";
	private static final String TXT_CYCLE_DOWN			= "-";
	
	private static final int WIDTH		= 112;
	private static final int BTN_HEIGHT	= 20;
	private static final int GAP 		= 2;
	
	private RedButton btnZoomOut;
	private RedButton btnZoomIn;
	
	public WndSettings( boolean inGame ) {
		super();
		
		if (inGame) {
			int w = BTN_HEIGHT;
			
			// Zoom out
			btnZoomOut = new RedButton( TXT_ZOOM_OUT ) {
				@Override
				protected void onClick() {
					zoom( Camera.main.zoom - 1 );
				}
			};
			add( btnZoomOut.setRect( 0, 0, w, BTN_HEIGHT) );
			
			// Zoom in
			btnZoomIn = new RedButton( TXT_ZOOM_IN ) {
				@Override
				protected void onClick() {
					zoom( Camera.main.zoom + 1 );
				}
			};
			add( btnZoomIn.setRect( WIDTH - w, 0, w, BTN_HEIGHT) );
			
			// Default zoom
			add( new RedButton( TXT_ZOOM_DEFAULT ) {
				@Override
				protected void onClick() {
					zoom( PixelScene.defaultZoom );
				}
			}.setRect( btnZoomOut.right(), 0, WIDTH - btnZoomIn.width() - btnZoomOut.width(), BTN_HEIGHT ) );
			
		} else {
			
			CheckBox btnScaleUp = new CheckBox( TXT_SCALE_UP ) {
				@Override
				protected void onClick() {
					super.onClick();
					PixelDungeon.scaleUp( checked() );
				}
			};
			btnScaleUp.setRect( 0, 0, WIDTH, BTN_HEIGHT );
			btnScaleUp.checked( PixelDungeon.scaleUp() );
			add( btnScaleUp );
			
		}
		
		CheckBox btnMusic = new CheckBox( TXT_MUSIC ) {
			@Override
			protected void onClick() {
				super.onClick();
				PixelDungeon.music( checked() );
			}
		};
		btnMusic.setRect( 0, BTN_HEIGHT + GAP, WIDTH, BTN_HEIGHT );
		btnMusic.checked( PixelDungeon.music() );
		add( btnMusic );
		
		CheckBox btnSound = new CheckBox( TXT_SOUND ) {
			@Override
			protected void onClick() {
				super.onClick();
				PixelDungeon.soundFx( checked() );
				Sample.INSTANCE.play( Assets.SND_CLICK );
			}
		};
		btnSound.setRect( 0, btnMusic.bottom() + GAP, WIDTH, BTN_HEIGHT );
		btnSound.checked( PixelDungeon.soundFx() );
		add( btnSound );
		
		if (!inGame) {
			
			//begin night mode choice
			// Zoom out
			btnCycleDownNight = new RedButton( TXT_CYCLE_DOWN ) {
				@Override
				protected void onClick() {
					if (TXT_NIGHT_DISPLAY == TXT_NIGHT_ON){
						TXT_NIGHT_DISPLAY = TXT_NIGHT_DEFAULT;
						btnNightDisplay.text(TXT_NIGHT_DEFAULT);
						StartScene.manualNightMode = false;
					} else if (TXT_NIGHT_DISPLAY == TXT_NIGHT_OFF){
						TXT_NIGHT_DISPLAY  = TXT_NIGHT_ON;
						btnNightDisplay.text(TXT_NIGHT_ON);
						StartScene.manualNightMode = true;
						StartScene.nightModeChoice = true;
					} else {
						TXT_NIGHT_DISPLAY = TXT_NIGHT_OFF;
						btnNightDisplay.text(TXT_NIGHT_OFF);
						StartScene.manualNightMode = true;
						StartScene.manualNightMode = false;
					}
				}
			};
			add( btnCycleDownNight.setRect( 0, btnSound.bottom() + GAP, BTN_HEIGHT, BTN_HEIGHT) );
			
			// Zoom in
			btnCycleUpNight = new RedButton( TXT_CYCLE_UP ) {
				@Override
				protected void onClick() {
					if (TXT_NIGHT_DISPLAY == TXT_NIGHT_ON){
						TXT_NIGHT_DISPLAY = TXT_NIGHT_OFF;
						btnNightDisplay.text(TXT_NIGHT_OFF);
						StartScene.manualNightMode = true;
						StartScene.nightModeChoice = false;
					} else if (TXT_NIGHT_DISPLAY == TXT_NIGHT_OFF){
						TXT_NIGHT_DISPLAY  = TXT_NIGHT_DEFAULT;
						btnNightDisplay.text(TXT_NIGHT_DEFAULT);
						StartScene.manualNightMode = false;
					} else {
						TXT_NIGHT_DISPLAY = TXT_NIGHT_ON;
					btnNightDisplay.text(TXT_NIGHT_ON);
					StartScene.manualNightMode = true;
					StartScene.nightModeChoice = true;
					}
				}
			};
			add( btnCycleUpNight.setRect( WIDTH - BTN_HEIGHT, btnSound.bottom() + GAP, BTN_HEIGHT, BTN_HEIGHT) );
			
			// Default zoom
			btnNightDisplay = new RedButton( TXT_NIGHT_DISPLAY ) {
				@Override
				protected void onClick() {
					//Game.switchScene( BadgesScene.class );
				}
			};
			add ( btnNightDisplay.setRect( btnCycleDownNight.right(), btnSound.bottom() + GAP, WIDTH - btnCycleUpNight.width() - btnCycleDownNight.width(), BTN_HEIGHT ) );
			
			
			RedButton btnOrientation = new RedButton( orientationText() ) {
				@Override
				protected void onClick() {
					PixelDungeon.landscape( !PixelDungeon.landscape() );
				}
			};
			btnOrientation.setRect( 0, btnNightDisplay.bottom() + GAP, WIDTH, BTN_HEIGHT );
			add( btnOrientation );
			
			resize( WIDTH, (int)btnOrientation.bottom() );
			
		} else {
		
			CheckBox btnBrightness = new CheckBox( TXT_BRIGHTNESS ) {
				@Override
				protected void onClick() {
					super.onClick();
					PixelDungeon.brightness( checked() );
				}
			};
			btnBrightness.setRect( 0, btnSound.bottom() + GAP, WIDTH, BTN_HEIGHT );
			btnBrightness.checked( PixelDungeon.brightness() );
			add( btnBrightness );
			
			resize( WIDTH, (int)btnBrightness.bottom() );
			
		}
	}
	
	private void zoom( float value ) {

		Camera.main.zoom( value );
		PixelDungeon.zoom( (int)(value - PixelScene.defaultZoom) );

		updateEnabled();
	}
	
	private void updateEnabled() {
		float zoom = Camera.main.zoom;
		btnZoomIn.enable( zoom < PixelScene.maxZoom );
		btnZoomOut.enable( zoom > PixelScene.minZoom );
	}
	
	private String orientationText() {
		return PixelDungeon.landscape() ? TXT_SWITCH_PORT : TXT_SWITCH_LAND;
	}
}
