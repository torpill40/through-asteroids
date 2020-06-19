package com.torpill.throughasteroids.engine.maths;

public class Vector3f {

	private float x, y, z;

	public Vector3f(final float x, final float y, final float z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(final Vector3f vector) {

		this.x = vector.getX();
		this.y = vector.getY();
		this.z = vector.getZ();
	}

	public static Vector3f add(final Vector3f vector, final Vector3f other) {

		return new Vector3f(vector.getX() + other.getX(), vector.getY() + other.getY(), vector.getZ() + other.getZ());
	}

	public static Vector3f divide(final Vector3f vector, final Vector3f other) {

		return new Vector3f(vector.getX() / other.getX(), vector.getY() / other.getY(), vector.getZ() / other.getZ());
	}

	public static float dot(final Vector3f vector, final Vector3f other) {

		return vector.getX() * other.getX() + vector.getY() * other.getY() + vector.getZ() * other.getZ();
	}

	public static float length(final Vector3f vector) {

		return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
	}

	public static Vector3f multiply(final Vector3f vector, final Vector3f other) {

		return new Vector3f(vector.getX() * other.getX(), vector.getY() * other.getY(), vector.getZ() * other.getZ());
	}

	public static void negate(final Vector3f vector) {

		vector.x = -vector.x;
		vector.y = -vector.y;
		vector.z = -vector.z;
	}

	public static Vector3f normalize(final Vector3f vector) {

		final float len = Vector3f.length(vector);
		return Vector3f.divide(vector, new Vector3f(len, len, len));
	}

	public static Vector3f subtract(final Vector3f vector, final Vector3f other) {

		return new Vector3f(vector.getX() - other.getX(), vector.getY() - other.getY(), vector.getZ() - other.getZ());
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		final Vector3f other = (Vector3f) obj;
		if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) return false;
		if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) return false;
		if (Float.floatToIntBits(this.z) != Float.floatToIntBits(other.z)) return false;
		return true;
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

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.x);
		result = prime * result + Float.floatToIntBits(this.y);
		result = prime * result + Float.floatToIntBits(this.z);
		return result;
	}

	public void set(final float x, final float y, final float z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(final Vector3f vector) {

		this.set(vector.getX(), vector.getY(), vector.getZ());
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
