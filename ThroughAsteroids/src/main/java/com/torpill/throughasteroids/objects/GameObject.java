package com.torpill.throughasteroids.objects;

import com.torpill.throughasteroids.engine.graphics.Mesh;
import com.torpill.throughasteroids.engine.maths.Vector3f;

public class GameObject {

	private final Vector3f position, rotation, scale;
	private final Mesh mesh;

	public GameObject(final Vector3f position, final Vector3f rotation, final Vector3f scale, final Mesh mesh) {

		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
	}

	public Mesh getMesh() {

		return this.mesh;
	}

	public Vector3f getPosition() {

		return this.position;
	}

	public Vector3f getRotation() {

		return this.rotation;
	}

	public Vector3f getScale() {

		return this.scale;
	}

	public void update() {

		this.position.setZ(this.position.getZ() - 0.05F);
	}

}
