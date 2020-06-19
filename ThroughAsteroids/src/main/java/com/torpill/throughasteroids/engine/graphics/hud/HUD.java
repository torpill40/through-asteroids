package com.torpill.throughasteroids.engine.graphics.hud;

import java.util.ArrayList;

import com.torpill.throughasteroids.engine.graphics.Renderer;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.hud.HUDObject;

public abstract class HUD {

	private final ArrayList<HUDObject> objects = new ArrayList<>();

	public void add(final HUDObject object) {

		this.objects.add(object);
	}

	public ArrayList<HUDObject> getAll() {

		return this.objects;
	}

	public void init() {

	}

	public void render(final Renderer renderer, final Camera camera) {

		for (final HUDObject object : this.objects) {

			renderer.renderHUD(object, camera);
		}
	}

	public void update() {

		for (final HUDObject object : this.objects) {

			object.update();
		}
	}
}
