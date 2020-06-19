package com.torpill.throughasteroids.engine.maths;

public class Vector2f {

	private float x, y;

	public Vector2f(final float x, final float y) {

		this.x = x;
		this.y = y;
	}

	public static Vector2f add(final Vector2f vector, final Vector2f other) {

		return new Vector2f(vector.getX() + other.getX(), vector.getY() + other.getY());
	}

	public static Vector2f divide(final Vector2f vector, final Vector2f other) {

		return new Vector2f(vector.getX() / other.getX(), vector.getY() / other.getY());
	}

	public static float dot(final Vector2f vector, final Vector2f other) {

		return vector.getX() * other.getX() + vector.getY() * other.getY();
	}

	public static float length(final Vector2f vector) {

		return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
	}

	public static Vector2f multiply(final Vector2f vector, final Vector2f other) {

		return new Vector2f(vector.getX() * other.getX(), vector.getY() * other.getY());
	}

	public static void negate(final Vector2f vector) {

		vector.x = -vector.x;
		vector.y = -vector.y;
	}

	public static Vector2f normalize(final Vector2f vector) {

		final float len = Vector2f.length(vector);
		return Vector2f.divide(vector, new Vector2f(len, len));
	}

	public static Vector2f subtract(final Vector2f vector, final Vector2f other) {

		return new Vector2f(vector.getX() - other.getX(), vector.getY() - other.getY());
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		final Vector2f other = (Vector2f) obj;
		if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) return false;
		if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) return false;
		return true;
	}

	public float getX() {

		return this.x;
	}

	public float getY() {

		return this.y;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.x);
		result = prime * result + Float.floatToIntBits(this.y);
		return result;
	}

	public void set(final float x, final float y) {

		this.x = x;
		this.y = y;
	}

	public void set(final Vector2f vector) {

		this.set(vector.getX(), vector.getY());
	}

	public void setX(final float x) {

		this.x = x;
	}

	public void setY(final float y) {

		this.y = y;
	}
}
