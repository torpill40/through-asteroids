package com.torpill.throughasteroids.engine.graphics.guis;

import java.util.ArrayList;

import com.torpill.throughasteroids.engine.graphics.Renderer;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.guis.GUIButton;
import com.torpill.throughasteroids.objects.guis.GUIObject;

public class GUI {

	private final ArrayList<GUIObject> objects = new ArrayList<>();

	public void addButton(final String name, final Vector2f posOnScreen, final Vector2f size, final GUIButton model) {

		this.add(new GUIButton(posOnScreen, size, model));
	}

	public void add(final GUIObject object) {

		this.objects.add(object);
	}

	public ArrayList<? extends GUIObject> getAll() {

		return this.objects;
	}

	public void init() {

	}

	public void render(final Renderer renderer, final Camera camera) {

		for (final GUIObject object : this.objects) {

			renderer.renderGUI(object, camera);
		}
	}

	public void update() {

		for (final GUIObject object : this.objects) {

			object.update();
		}
	}
}
