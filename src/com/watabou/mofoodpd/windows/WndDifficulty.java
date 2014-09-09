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

import com.watabou.mofoodpd.scenes.PixelScene;
import com.watabou.mofoodpd.scenes.StartScene;
import com.watabou.mofoodpd.ui.RedButton;
import com.watabou.mofoodpd.ui.Window;
import com.watabou.noosa.BitmapTextMultiline;
//import com.watabou.noosa.Game;

public class WndDifficulty extends Window {

	private static final int WIDTH			= 120;
	private static final int MARGIN 		= 2;
	
	private static final int BTN_HEIGHT	= 20;
	private static final String TXT_CYCLE_UP			= "+";
	private static final String TXT_CYCLE_DOWN			= "-";
	private static String TXT_DIFFICULTY_DISPLAY	= "Normal";	
	
	private static final String TXT_EASY				= "Easy";
	private static final String TXT_NORMAL				= "Normal";
	private static final String TXT_HARD				= "Hard";
	private static final String TXT_INSANE				= "Insane";

	private static final String TXT_START = "Start New Game";
	private static final String TXT_CANCEL	= "Cancel, return to main menu";
	private static final String TXT_DIFFICULTY_SELECT	= "Select your difficulty:";
	private static final String TXT_DIFF_MESSAGE	= "Recommended difficulty for the average player is Easy.";
	
	private RedButton btnCycleDown;
	private RedButton btnCycleUp;
	
	RedButton btnDifficultyDisplay;
	
	public WndDifficulty( ) {
		super();
		
		BitmapTextMultiline tfTitle = PixelScene.createMultiline( TXT_DIFFICULTY_SELECT, 9 );
		tfTitle.hardlight( TITLE_COLOR );
		tfTitle.x = tfTitle.y = MARGIN;
		tfTitle.maxWidth = WIDTH - MARGIN * 2;
		tfTitle.measure();
		add( tfTitle );
		
		BitmapTextMultiline tfMesage = PixelScene.createMultiline( TXT_DIFF_MESSAGE, 8 );
		tfMesage.maxWidth = WIDTH - MARGIN * 2;
		tfMesage.measure();
		tfMesage.x = MARGIN;
		tfMesage.y = tfTitle.y + tfTitle.height() + MARGIN;
		add( tfMesage );
		
		
		float pos = tfMesage.y + tfMesage.height() + MARGIN;
		
		int w = BTN_HEIGHT;
		
		// Zoom out
		btnCycleDown = new RedButton( TXT_CYCLE_DOWN ) {
			@Override
			protected void onClick() {
				if (TXT_DIFFICULTY_DISPLAY == TXT_EASY){
					TXT_DIFFICULTY_DISPLAY = TXT_INSANE;
					btnDifficultyDisplay.text(TXT_INSANE);
					StartScene.difficulty = 4;
				} else if (TXT_DIFFICULTY_DISPLAY == TXT_NORMAL){
					TXT_DIFFICULTY_DISPLAY  = TXT_EASY;
					btnDifficultyDisplay.text(TXT_EASY);
					StartScene.difficulty = 1;
				} else if (TXT_DIFFICULTY_DISPLAY == TXT_HARD){
					TXT_DIFFICULTY_DISPLAY = TXT_NORMAL;
					btnDifficultyDisplay.text(TXT_NORMAL);
					StartScene.difficulty = 2;
				} else {
				TXT_DIFFICULTY_DISPLAY = TXT_HARD;
				btnDifficultyDisplay.text(TXT_HARD);
				StartScene.difficulty = 3;
				}
			}
		};
		add( btnCycleDown.setRect( MARGIN, pos, w, BTN_HEIGHT) );
		
		// Zoom in
		btnCycleUp = new RedButton( TXT_CYCLE_UP ) {
			@Override
			protected void onClick() {
				if (TXT_DIFFICULTY_DISPLAY == TXT_EASY){
					TXT_DIFFICULTY_DISPLAY = TXT_NORMAL;
					btnDifficultyDisplay.text(TXT_NORMAL);
					StartScene.difficulty = 2;
				} else if (TXT_DIFFICULTY_DISPLAY == TXT_NORMAL){
					TXT_DIFFICULTY_DISPLAY  = TXT_HARD;
					btnDifficultyDisplay.text(TXT_HARD);
					StartScene.difficulty = 3;
				} else if (TXT_DIFFICULTY_DISPLAY == TXT_HARD){
					TXT_DIFFICULTY_DISPLAY = TXT_INSANE;
					btnDifficultyDisplay.text(TXT_INSANE);
					StartScene.difficulty = 4;
				} else {
				TXT_DIFFICULTY_DISPLAY = TXT_EASY;
				btnDifficultyDisplay.text(TXT_EASY);
				StartScene.difficulty = 1;
				}
			}
		};
		add( btnCycleUp.setRect( WIDTH - MARGIN - w, pos, w, BTN_HEIGHT) );
		
		// Default zoom
		btnDifficultyDisplay = new RedButton( TXT_DIFFICULTY_DISPLAY ) {
			@Override
			protected void onClick() {
				//Game.switchScene( BadgesScene.class );
			}
		};
		add ( btnDifficultyDisplay.setRect( btnCycleDown.right(), pos, WIDTH - MARGIN - MARGIN - btnCycleUp.width() - btnCycleDown.width(), BTN_HEIGHT ) );		

		pos += BTN_HEIGHT + MARGIN;
		
		RedButton btnStartNewGame = new RedButton ( TXT_START ){
			@Override
			protected void onClick() {
				hide();
				onSelect ( 0 );
			}
		};
		btnStartNewGame.setRect ( MARGIN, pos, WIDTH - MARGIN * 2, BTN_HEIGHT);
		add (btnStartNewGame);

		
		pos += BTN_HEIGHT + MARGIN;
		
		RedButton btnCancel = new RedButton ( TXT_CANCEL ){
			@Override
			protected void onClick() {
				hide();
				onSelect ( 1 );
			}
		};
		btnCancel.setRect ( MARGIN, pos, WIDTH - MARGIN * 2, BTN_HEIGHT);
		add (btnCancel);
		
		pos += BTN_HEIGHT + MARGIN;

		resize( WIDTH, (int)pos );
	}
	
	protected void onSelect( int index ) {};
}
