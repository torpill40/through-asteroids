package com.torpill.throughasteroids.engine.maths;

public class Vector2f {

	private float x, y;

	public Vector2f(final float x, final float y) {

		this.x = x;
		this.y = y;
	}

	public float getX() {

		return this.x;
	}

	public float getY() {

		return this.y;
	}

	public void set(final float x, final float y) {

		this.x = x;
		this.y = y;
	}

	public void setX(final float x) {

		this.x = x;
	}

	public void setY(final float y) {

		this.y = y;
	}
}
