package com.torpill.throughasteroids.objects.hud;

import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;

public class HUDObjects {

	public static final HUDObject CURSOR = new HUDObject(new Vector3f(0, 0, -1.0F), new Vector3f(0, 0, 0), new Vector2f(0.03F, 0.03F), "/hud/cursor.png", "CURSOR");

	public static void create() {

		create(CURSOR);
	}

	public static void create(final HUDObject block) {

		block.getMesh().create();
	}

	public static void destroy() {

		destroy(CURSOR);
	}

	public static void destroy(final HUDObject block) {

		block.getMesh().destroy();
	}
}
