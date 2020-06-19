package com.torpill.throughasteroids.engine.graphics.hud;

import com.torpill.throughasteroids.objects.hud.HUDObjects;

public class InGameHUD extends HUD {

	@Override
	public void init() {

		super.init();
		this.add(HUDObjects.CURSOR);
	}
}
