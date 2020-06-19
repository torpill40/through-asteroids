package com.torpill.throughasteroids.objects.guis;

import com.torpill.throughasteroids.engine.graphics.materials.GUIButtonMaterial;
import com.torpill.throughasteroids.engine.maths.Vector2f;

public class GUIButton extends GUIObject {

	private final GUIButtonMaterial material;

	public GUIButton(final int type, final int color, final String classifier) {

		super(new GUIMesh(new GUIButtonMaterial(type, color)), classifier + "_BUTTON");
		this.material = (GUIButtonMaterial) this.getMesh().getMaterial();
	}

	public GUIButton(final Vector2f posOnScreen, final Vector2f size, final GUIButton model) {

		super(posOnScreen, size, model);
		this.material = (GUIButtonMaterial) this.getMesh().getMaterial();
	}

	@Override
	public void update() {

		super.update();

		this.material.setState(0);
	}

	@Override
	public void onMouseHover() {

		super.onMouseHover();

		this.material.setState(1);
	}

	@Override
	public void onMouseClicked() {

		super.onMouseClicked();

		this.material.setState(2);
	}
}
