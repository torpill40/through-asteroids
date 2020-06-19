package com.torpill.throughasteroids.engine.graphics.guis;

import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.objects.guis.GUIObjects;

public class MainTitleGUI extends GUI {

	@Override
	public void init() {

		super.init();

		final float buttonHeight = 16F;
		final float buttonWidth = buttonHeight * (10F / 3F);

		this.addButton("Play", new Vector2f(0, -buttonHeight * 0F), new Vector2f(buttonWidth, buttonHeight), GUIObjects.CLASSIC_TOP_BUTTON);
		this.addButton("Settings", new Vector2f(0, -buttonHeight * 2F), new Vector2f(buttonWidth, buttonHeight), GUIObjects.CLASSIC_BUTTON);
		this.addButton("Close", new Vector2f(0, -buttonHeight * 4F), new Vector2f(buttonWidth, buttonHeight), GUIObjects.GREY_BOTTOM_BUTTON);
	}
}
