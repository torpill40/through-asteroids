
package com.torpill.throughasteroids.engine.maths;

import java.util.Arrays;

public class Matrix4f {

	public static final int SIZE = 4;
	private final float[] elements = new float[SIZE * SIZE];

	public Matrix4f() {

	}

	public Matrix4f(final Vector4f vector0, final Vector4f vector1, final Vector4f vector2, final Vector4f vector3) {

		this.set(0, 0, vector0.getX());
		this.set(0, 1, vector0.getY());
		this.set(0, 2, vector0.getZ());
		this.set(0, 3, vector0.getW());

		this.set(1, 0, vector1.getX());
		this.set(1, 1, vector1.getY());
		this.set(1, 2, vector1.getZ());
		this.set(1, 3, vector1.getW());

		this.set(2, 0, vector2.getX());
		this.set(2, 1, vector2.getY());
		this.set(2, 2, vector2.getZ());
		this.set(2, 3, vector2.getW());

		this.set(3, 0, vector3.getX());
		this.set(3, 1, vector3.getY());
		this.set(3, 2, vector3.getZ());
		this.set(3, 3, vector3.getW());
	}

	public static Matrix4f add(final Matrix4f matrix, final Matrix4f other) {

		final Matrix4f result = new Matrix4f();

		for (int i = 0; i < SIZE; i++) {

			for (int j = 0; j < SIZE; j++) {

				result.set(i, j, matrix.get(i, j) + other.get(i, j));
			}
		}

		return result;
	}

	public static Matrix4f identity() {

		final Matrix4f result = new Matrix4f();

		for (int i = 0; i < SIZE; i++) {

			for (int j = 0; j < SIZE; j++) {

				result.set(i, j, 0);
			}
		}

		result.set(0, 0, 1);
		result.set(1, 1, 1);
		result.set(2, 2, 1);
		result.set(3, 3, 1);

		return result;
	}

	public static Matrix4f invert(final Matrix4f matrix) {

		final float a = matrix.get(0, 0) * matrix.get(1, 1) - matrix.get(0, 1) * matrix.get(1, 0);
		final float b = matrix.get(0, 0) * matrix.get(1, 2) - matrix.get(0, 2) * matrix.get(1, 0);
		final float c = matrix.get(0, 0) * matrix.get(1, 3) - matrix.get(0, 3) * matrix.get(1, 0);
		final float d = matrix.get(0, 1) * matrix.get(1, 2) - matrix.get(0, 2) * matrix.get(1, 1);
		final float e = matrix.get(0, 1) * matrix.get(1, 3) - matrix.get(0, 3) * matrix.get(1, 1);
		final float f = matrix.get(0, 2) * matrix.get(1, 3) - matrix.get(0, 3) * matrix.get(1, 2);
		final float g = matrix.get(2, 0) * matrix.get(3, 1) - matrix.get(2, 1) * matrix.get(3, 0);
		final float h = matrix.get(2, 0) * matrix.get(3, 2) - matrix.get(2, 2) * matrix.get(3, 0);
		final float i = matrix.get(2, 0) * matrix.get(3, 3) - matrix.get(2, 3) * matrix.get(3, 0);
		final float j = matrix.get(2, 1) * matrix.get(3, 2) - matrix.get(2, 2) * matrix.get(3, 1);
		final float k = matrix.get(2, 1) * matrix.get(3, 3) - matrix.get(2, 3) * matrix.get(3, 1);
		final float l = matrix.get(2, 2) * matrix.get(3, 3) - matrix.get(2, 3) * matrix.get(3, 2);
		float det = a * l - b * k + c * j + d * i - e * h + f * g;
		det = 1.0f / det;

		final Matrix4f result = new Matrix4f();
		result.set(0, 0, MoreMath.fma(matrix.get(1, 1), l, MoreMath.fma(-matrix.get(1, 2), k, matrix.get(1, 3) * j)) * det);
		result.set(0, 1, MoreMath.fma(-matrix.get(0, 1), l, MoreMath.fma(matrix.get(0, 2), k, -matrix.get(0, 3) * j)) * det);
		result.set(0, 2, MoreMath.fma(matrix.get(3, 1), f, MoreMath.fma(-matrix.get(3, 2), e, matrix.get(3, 3) * d)) * det);
		result.set(0, 3, MoreMath.fma(-matrix.get(2, 1), f, MoreMath.fma(matrix.get(2, 2), e, -matrix.get(2, 3) * d)) * det);
		result.set(1, 0, MoreMath.fma(-matrix.get(1, 0), l, MoreMath.fma(matrix.get(1, 2), i, -matrix.get(1, 3) * h)) * det);
		result.set(1, 1, MoreMath.fma(matrix.get(0, 0), l, MoreMath.fma(-matrix.get(0, 2), i, matrix.get(0, 3) * h)) * det);
		result.set(1, 2, MoreMath.fma(-matrix.get(3, 0), f, MoreMath.fma(matrix.get(3, 2), c, -matrix.get(3, 3) * b)) * det);
		result.set(1, 3, MoreMath.fma(matrix.get(2, 0), f, MoreMath.fma(-matrix.get(2, 2), c, matrix.get(2, 3) * b)) * det);
		result.set(2, 0, MoreMath.fma(matrix.get(1, 0), k, MoreMath.fma(-matrix.get(1, 1), i, matrix.get(1, 3) * g)) * det);
		result.set(2, 1, MoreMath.fma(-matrix.get(0, 0), k, MoreMath.fma(matrix.get(0, 1), i, -matrix.get(0, 3) * g)) * det);
		result.set(2, 2, MoreMath.fma(matrix.get(3, 0), e, MoreMath.fma(-matrix.get(3, 1), c, matrix.get(3, 3) * a)) * det);
		result.set(2, 3, MoreMath.fma(-matrix.get(2, 0), e, MoreMath.fma(matrix.get(2, 1), c, -matrix.get(2, 3) * a)) * det);
		result.set(3, 0, MoreMath.fma(-matrix.get(1, 0), j, MoreMath.fma(matrix.get(1, 1), h, -matrix.get(1, 2) * g)) * det);
		result.set(3, 1, MoreMath.fma(matrix.get(0, 0), j, MoreMath.fma(-matrix.get(0, 1), h, matrix.get(0, 2) * g)) * det);
		result.set(3, 2, MoreMath.fma(-matrix.get(3, 0), d, MoreMath.fma(matrix.get(3, 1), b, -matrix.get(3, 2) * a)) * det);
		result.set(3, 3, MoreMath.fma(matrix.get(2, 0), d, MoreMath.fma(-matrix.get(2, 1), b, matrix.get(2, 2) * a)) * det);

		return result;
	}

	public static Matrix4f multiply(final Matrix4f matrix, final Matrix4f other) {

		final Matrix4f result = Matrix4f.identity();

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				result.set(i, j, matrix.get(i, 0) * other.get(0, j) + matrix.get(i, 1) * other.get(1, j) + matrix.get(i, 2) * other.get(2, j) + matrix.get(i, 3) * other.get(3, j));
			}
		}

		return result;
	}

	public static void normalizedPositiveZ(final Matrix4f matrix, final Vector3f dir) {

		dir.set(matrix.get(0, 2), matrix.get(1, 2), matrix.get(2, 2));
	}

	public static void positiveZ(final Matrix4f matrix, final Vector3f dir) {

		normalizedPositiveZ(matrix, dir);
	}

	public static Matrix4f projection(final float fov, final float aspect, final float near, final float far) {

		final Matrix4f result = Matrix4f.identity();

		final float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
		final float range = far - near;

		result.set(0, 0, 1.0f / (aspect * tanFOV));
		result.set(1, 1, 1.0f / tanFOV);
		result.set(2, 2, -((far + near) / range));
		result.set(2, 3, -1.0f);
		result.set(3, 2, -(2 * far * near / range));
		result.set(3, 3, 0.0f);

		return result;
	}

	public static Matrix4f rotate(final float angle, final Vector3f axis) {

		final Matrix4f result = Matrix4f.identity();

		final float cos = (float) Math.cos(Math.toRadians(angle));
		final float sin = (float) Math.sin(Math.toRadians(angle));
		final float C = 1 - cos;

		result.set(0, 0, cos + axis.getX() * axis.getX() * C);
		result.set(0, 1, axis.getX() * axis.getY() * C - axis.getZ() * sin);
		result.set(0, 2, axis.getX() * axis.getZ() * C + axis.getY() * sin);
		result.set(1, 0, axis.getY() * axis.getX() * C + axis.getZ() * sin);
		result.set(1, 1, cos + axis.getY() * axis.getY() * C);
		result.set(1, 2, axis.getY() * axis.getZ() * C - axis.getX() * sin);
		result.set(2, 0, axis.getZ() * axis.getX() * C - axis.getY() * sin);
		result.set(2, 1, axis.getZ() * axis.getY() * C + axis.getX() * sin);
		result.set(2, 2, cos + axis.getZ() * axis.getZ() * C);

		return result;
	}

	public static Matrix4f scale(final Vector3f scalar) {

		final Matrix4f result = Matrix4f.identity();

		result.set(0, 0, scalar.getX());
		result.set(1, 1, scalar.getY());
		result.set(2, 2, scalar.getZ());

		return result;
	}

	public static Vector4f transform(final Matrix4f matrix, final Vector4f vector) {

		final Vector4f result = Vector4f.multiply(matrix, vector);
		return result;
	}

	public static Matrix4f transform(final Vector3f position, final Vector3f rotation, final Vector3f scale) {

		Matrix4f result = Matrix4f.identity();

		final Matrix4f translationMatrix = Matrix4f.translate(position);
		final Matrix4f rotXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
		final Matrix4f rotYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
		final Matrix4f rotZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));
		final Matrix4f scaleMatrix = Matrix4f.scale(scale);

		final Matrix4f rotationMatrix = Matrix4f.multiply(rotXMatrix, Matrix4f.multiply(rotYMatrix, rotZMatrix));

		result = Matrix4f.multiply(Matrix4f.multiply(rotationMatrix, translationMatrix), scaleMatrix);

		return result;
	}

	public static Matrix4f translate(final Vector3f translate) {

		final Matrix4f result = Matrix4f.identity();

		result.set(3, 0, translate.getX());
		result.set(3, 1, translate.getY());
		result.set(3, 2, translate.getZ());

		return result;
	}

	public static Matrix4f view(final Vector3f position, final Vector3f rotation) {

		Matrix4f result = Matrix4f.identity();

		final Vector3f negative = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
		final Matrix4f translationMatrix = Matrix4f.translate(negative);
		final Matrix4f rotXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
		final Matrix4f rotYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
		final Matrix4f rotZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));

		final Matrix4f rotationMatrix = Matrix4f.multiply(rotZMatrix, Matrix4f.multiply(rotYMatrix, rotXMatrix));

		result = Matrix4f.multiply(translationMatrix, rotationMatrix);

		return result;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		final Matrix4f other = (Matrix4f) obj;
		if (!Arrays.equals(this.elements, other.elements)) return false;
		return true;
	}

	public float get(final int x, final int y) {

		return this.elements[y * SIZE + x];
	}

	public float[] getAll() {

		return this.elements;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.elements);
		return result;
	}

	public void set(final int x, final int y, final float value) {

		this.elements[y * SIZE + x] = value;
	}

	public void set(final Matrix4f matrix) {

		for (int i = 0; i < SIZE * SIZE; i++) {

			this.elements[i] = matrix.elements[i];
		}
	}
}
