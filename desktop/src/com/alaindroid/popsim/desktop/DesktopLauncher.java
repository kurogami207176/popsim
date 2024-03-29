package com.alaindroid.popsim.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alaindroid.popsim.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Wanderer";
		config.useGL30 = true;
		config.height = 900;
		config.width = 1280;
		new LwjglApplication(new MainGame(), config);
	}
}
