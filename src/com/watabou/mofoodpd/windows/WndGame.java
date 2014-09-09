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

import java.io.IOException;

import com.watabou.mofoodpd.Dungeon;
import com.watabou.mofoodpd.scenes.GameScene;
import com.watabou.mofoodpd.scenes.InterlevelScene;
import com.watabou.mofoodpd.scenes.RankingsScene;
import com.watabou.mofoodpd.scenes.StartScene;
import com.watabou.mofoodpd.scenes.TitleScene;
import com.watabou.mofoodpd.ui.Icons;
import com.watabou.mofoodpd.ui.RedButton;
import com.watabou.mofoodpd.ui.Window;
import com.watabou.noosa.Game;

public class WndGame extends Window {
	
	private static final String TXT_SETTINGS	= "Settings";
	private static final String TXT_RANKINGS	= "Rankings";
	private static final String TXT_START		= "Start New Game";
	private static final String TXT_MENU		= "Main Menu";
	private static final String TXT_EXIT		= "Exit Pixel Dungeon";
	private static final String TXT_RETURN		= "Return to Game";
	
	private static final int WIDTH		= 120;
	private static final int BTN_HEIGHT	= 20;
	private static final int GAP		= 2;
	
	
	private int pos;
	
	public WndGame() {
		
		super();
		
		addButton( new RedButton( TXT_SETTINGS ) {
			@Override
			protected void onClick() {
				hide();
				GameScene.show( new WndSettings( true ) );
			}
		} );
		
		// Restart
		if (!Dungeon.hero.isAlive()) {
			
			RedButton btnStart;
			addButton( btnStart = new RedButton( TXT_START ) {
				@Override
				protected void onClick() {
					
					GameScene.show( new WndDifficulty( ) {
						@Override
						protected void onSelect( int index ) {
							if (index == 0) {
								if (StartScene.difficulty == 0){
									StartScene.difficulty = 2;
								}
								Dungeon.hero = null;
								InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
								InterlevelScene.noStory = true;
								Game.switchScene( InterlevelScene.class );
							} else if (index == 1){
								//do nothing
							}
						}	
				} );
				}
			} );
			btnStart.icon( Icons.get( Dungeon.hero.heroClass ) );
			
			addButton( new RedButton( TXT_RANKINGS ) {
				@Override
				protected void onClick() {
					InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
					Game.switchScene( RankingsScene.class );
				}
			} );
		}
				
		// Main menu
		addButton( new RedButton( TXT_MENU ) {
			@Override
			protected void onClick() {
				try {
					Dungeon.saveAll();
				} catch (IOException e) {
					//
				}
				Game.switchScene( TitleScene.class );
			}
		} );
		
		// Exit
		addButton( new RedButton( TXT_EXIT ) {
			@Override
			protected void onClick() {
				Game.instance.finish();
			}
		} );
		
		// Cancel
		addButton( new RedButton( TXT_RETURN ) {
			@Override
			protected void onClick() {
				hide();
			}
		} );
		
		resize( WIDTH, pos );
	}
	
	private void addButton( RedButton btn ) {
		add( btn );
		btn.setRect( 0, pos > 0 ? pos += GAP : 0, WIDTH, BTN_HEIGHT );
		pos += BTN_HEIGHT;
	}
}
