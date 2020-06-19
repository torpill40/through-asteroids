package com.torpill.throughasteroids.engine.io;

import java.util.ArrayList;

import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.engine.maths.Vector4f;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.GameObject;

public class MousePicker extends CameraPicker {

	private final Matrix4f projection;

	public MousePicker(final Camera camera, final Matrix4f projection) {

		super(camera);
		this.projection = projection;
	}

	private Vector3f calculateMouseRay() {

		final float mouseX = (float) Input.getMouseX();
		final float mouseY = (float) Input.getMouseY();

		final Vector2f normalizedCoords = this.getNormalizedDeviceCoords(mouseX, mouseY);
		final Vector4f clipCoords = new Vector4f(normalizedCoords.getX(), normalizedCoords.getY(), -1F, 1F);
		final Vector4f eyeCoords = this.toEyeCoords(clipCoords);
		final Vector3f worldRay = this.toWorldCoords(eyeCoords);
		return worldRay;
	}

	private Vector4f toEyeCoords(final Vector4f clipCoords) {

		final Matrix4f invertedProjection = Matrix4f.invert(this.projection);
		final Vector4f eyeCoords = Matrix4f.transform(invertedProjection, clipCoords);

		return new Vector4f(eyeCoords.getX(), eyeCoords.getY(), -1F, 0F);
	}

	private Vector3f toWorldCoords(final Vector4f eyeCoords) {

		final Matrix4f invertedView = Matrix4f.invert(this.view);
		final Vector4f rayWorld = Matrix4f.transform(invertedView, eyeCoords);
		final Vector3f mouseRay = new Vector3f(rayWorld.getX(), rayWorld.getY(), rayWorld.getZ());
		return mouseRay;
	}

	@Override
	public void update(final ArrayList<? extends GameObject> objects) {

		this.calculateView();
		this.currentRay.set(this.calculateMouseRay());

		this.selectGameObject(40.0F, objects);
	}
}
