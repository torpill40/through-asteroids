package com.torpill.throughasteroids.objects.guis;

import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.objects.GameObject;

public class GUIObject extends GameObject {

	private Vector2f posOnScreen, sizeOnScreen;
	public static final float RATIO = 1.0F / 128.0F;

	public GUIObject(final GUIMesh mesh, final String name) {

		this(null, null, mesh, name);
	}

	public GUIObject(final Vector2f posOnScreen, final Vector2f size, final GUIObject object) {

		this(new Vector3f(posOnScreen.getX() * 0.0185F + 0.0155F, posOnScreen.getY() * 0.0597F - 0.0465F, -1.5825F), new Vector3f(size.getX() * RATIO * 0.9234F, size.getY() * RATIO * 0.9572F, 1.0F), (GUIMesh) object.getMesh().clone(), object.getName());

		this.posOnScreen = new Vector2f(posOnScreen.getX() * RATIO, posOnScreen.getY() * RATIO);
		this.sizeOnScreen = new Vector2f(size.getX() * RATIO, size.getY() * RATIO);
	}

	public GUIObject(final Vector3f position, final Vector3f scale, final GUIMesh mesh, final String name) {

		super(position, new Vector3f(0, 0, 0), scale, mesh, name);
	}

	public void onMouseHover() {

	}

	public void onMouseClicked() {

	}

	public Vector2f getPosOnScreen() {

		return this.posOnScreen;
	}

	public Vector2f getSizeOnScreen() {

		return this.sizeOnScreen;
	}
}
