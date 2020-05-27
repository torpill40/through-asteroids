package com.torpill.throughasteroids.objects;

import com.torpill.throughasteroids.engine.maths.Vector3f;

public class Camera {

	private final Vector3f position, rotation;

	public Camera(final Vector3f position, final Vector3f rotation) {

		this.position = position;
		this.rotation = rotation;
	}

	public Vector3f getPosition() {

		return this.position;
	}

	public Vector3f getRotation() {

		return this.rotation;
	}
}
