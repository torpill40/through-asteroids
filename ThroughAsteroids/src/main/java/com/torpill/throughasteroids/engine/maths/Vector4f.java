package com.torpill.throughasteroids.engine.maths;

public class Vector4f {

	private float x, y, z, w;

	public Vector4f(final float x, final float y, final float z, final float w) {

		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vector4f(final Vector3f vector, final float w) {

		this.x = vector.getX();
		this.y = vector.getY();
		this.z = vector.getZ();
		this.w = w;
	}

	public Vector4f(final Vector4f vector) {

		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		this.w = vector.w;
	}

	public static Vector4f add(final Vector4f vector, final Vector4f other) {

		return new Vector4f(vector.getX() + other.getX(), vector.getY() + other.getY(), vector.getZ() + other.getZ(), vector.getW() + other.getW());
	}

	public static Vector4f divide(final Vector4f vector, final Vector4f other) {

		return new Vector4f(vector.getX() / other.getX(), vector.getY() / other.getY(), vector.getZ() / other.getZ(), vector.getW() / other.getW());
	}

	public static float dot(final Vector4f vector, final Vector4f other) {

		return vector.getX() * other.getX() + vector.getY() * other.getY() + vector.getZ() * other.getZ() + vector.getW() * other.getW();
	}

	public static float length(final Vector4f vector) {

		return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ() + vector.getW() * vector.getW());
	}

	public static Vector4f multiply(final Matrix4f matrix, final Vector4f vector) {

		final float x = vector.x, y = vector.y, z = vector.z, w = vector.w;
		final Vector4f result = new Vector4f(0, 0, 0, 0);

		result.x = MoreMath.fma(matrix.get(0, 0), x, MoreMath.fma(matrix.get(1, 0), y, MoreMath.fma(matrix.get(2, 0), z, matrix.get(3, 0) * w)));
		result.y = MoreMath.fma(matrix.get(0, 1), x, MoreMath.fma(matrix.get(1, 1), y, MoreMath.fma(matrix.get(2, 1), z, matrix.get(3, 1) * w)));
		result.z = MoreMath.fma(matrix.get(0, 2), x, MoreMath.fma(matrix.get(1, 2), y, MoreMath.fma(matrix.get(2, 2), z, matrix.get(3, 2) * w)));
		result.w = MoreMath.fma(matrix.get(0, 3), x, MoreMath.fma(matrix.get(1, 3), y, MoreMath.fma(matrix.get(2, 3), z, matrix.get(3, 3) * w)));

		return result;
	}

	public static Vector4f multiply(final Vector4f vector, final Vector4f other) {

		return new Vector4f(vector.getX() * other.getX(), vector.getY() * other.getY(), vector.getZ() * other.getZ(), vector.getW() * other.getW());
	}

	public static void negate(final Vector4f vector) {

		vector.x = -vector.x;
		vector.y = -vector.y;
		vector.z = -vector.z;
		vector.w = -vector.w;
	}

	public static Vector4f normalize(final Vector4f vector) {

		final float len = Vector4f.length(vector);
		return Vector4f.divide(vector, new Vector4f(len, len, len, len));
	}

	public static Vector4f subtract(final Vector4f vector, final Vector4f other) {

		return new Vector4f(vector.getX() - other.getX(), vector.getY() - other.getY(), vector.getZ() - other.getZ(), vector.getW() - other.getW());
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		final Vector4f other = (Vector4f) obj;
		if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) return false;
		if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y)) return false;
		if (Float.floatToIntBits(this.z) != Float.floatToIntBits(other.z)) return false;
		if (Float.floatToIntBits(this.w) != Float.floatToIntBits(other.w)) return false;
		return true;
	}

	public float getW() {

		return this.w;
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
		result = prime * result + Float.floatToIntBits(this.w);
		return result;
	}

	public void set(final float x, final float y, final float z, final float w) {

		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public void set(final Vector4f vector) {

		this.set(vector.getX(), vector.getY(), vector.getZ(), vector.getW());
	}

	public void setW(final float w) {

		this.w = w;
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
