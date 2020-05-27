package com.torpill.throughasteroids.engine.maths;

public class Vector3f {

	private float x, y, z;

	public Vector3f(final float x, final float y, final float z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void add(final float x, final float y, final float z) {

		this.x += x;
		this.y += y;
		this.z += z;
	}

	public float getX() {

		return this.x;
	}

	public float getY() {

		return this.y;
	}

	public float getZ() {

		return this.z;
	}

	public void set(final float x, final float y, final float z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(final float x) {

		this.x = x;
	}

	public void setY(final float y) {

		this.y = y;
	}

	public void setZ(final float z) {

		this.z = z;
	}
}
