package com.torpill.throughasteroids.objects;

import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.engine.maths.Vector3f;

public class GameObject {

	private final Vector3f position, rotation, scale;
	private final Mesh mesh;
	private boolean selected = false;
	private final String name;
	private final HitBox hitbox;
	double temp = 0;

	public GameObject(final Vector3f position, final Vector3f rotation, final Vector3f scale, final Mesh mesh, final String name) {

		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
		this.name = name;
		this.hitbox = new HitBox(this);
	}

	public Matrix4f getHitBoxFace(final int face) {

		switch (face) {
		case 0:
			return new Matrix4f(this.hitbox.getCorner(3), this.hitbox.getCorner(7), this.hitbox.getCorner(4), this.hitbox.getCorner(0));
		case 1:
			return new Matrix4f(this.hitbox.getCorner(2), this.hitbox.getCorner(6), this.hitbox.getCorner(5), this.hitbox.getCorner(1));
		case 2:
			return new Matrix4f(this.hitbox.getCorner(7), this.hitbox.getCorner(6), this.hitbox.getCorner(5), this.hitbox.getCorner(4));
		case 3:
			return new Matrix4f(this.hitbox.getCorner(4), this.hitbox.getCorner(5), this.hitbox.getCorner(1), this.hitbox.getCorner(0));
		case 4:
			return new Matrix4f(this.hitbox.getCorner(0), this.hitbox.getCorner(1), this.hitbox.getCorner(2), this.hitbox.getCorner(3));
		case 5:
			return new Matrix4f(this.hitbox.getCorner(3), this.hitbox.getCorner(2), this.hitbox.getCorner(6), this.hitbox.getCorner(7));
		}

		return null;
	}

	public Mesh getMesh() {

		return this.mesh;
	}

	public String getName() {

		return this.name;
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

	public boolean isSelected() {

		return this.selected;
	}

	public void setSelected(final boolean selected) {

		this.selected = selected;
	}

	public void update() {

		this.hitbox.update();
	}
}
