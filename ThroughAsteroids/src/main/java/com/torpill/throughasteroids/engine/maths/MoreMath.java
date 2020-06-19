package com.torpill.throughasteroids.engine.maths;

public class MoreMath {

	public static Vector4f cartesianPlaneEquation(final Vector3f vector0, final Vector3f vector1, final Vector3f vector2) {

		final Vector3f normal = planeNormalVector(vector0, vector1, vector2);

		if (normal == null) return null;

		final float d = -normal.getX() * vector0.getX() - normal.getY() * vector0.getY() - normal.getZ() * vector0.getZ();

		return new Vector4f(normal, d);
	}

	public static float fma(final float a, final float b, final float c) {

		return a * b + c;
	}

	public static Vector3f intersectRayAab(final Vector3f origin, final Vector3f ray, final Vector4f plane) {

		final Vector3f normal = new Vector3f(plane.getX(), plane.getY(), plane.getZ());
		final float normalScRay = normal.getX() * ray.getX() + normal.getY() * ray.getY() + normal.getZ() * ray.getZ();

		if (normalScRay == 0) return null;

		final float t = (-plane.getX() * origin.getX() - plane.getY() * origin.getY() - plane.getZ() * origin.getZ() - plane.getW()) / normalScRay;

		return new Vector3f(origin.getX() + ray.getX() * t, origin.getY() + ray.getY() * t, origin.getZ() + ray.getZ() * t);
	}

	public static boolean isInPlanePart(final Vector4f plane, final Matrix4f part, final Vector3f vector) {

		final Vector3f[] vectors = new Vector3f[4];
		for (int i = 0; i < vectors.length; i++) {

			vectors[i] = new Vector3f(part.get(i, 0), part.get(i, 1), part.get(i, 2));
		}

		float sum = 0;
		for (int i = 0; i < vectors.length; i++) {

			final int j = i + 1 >= vectors.length ? 0 : i + 1;

			final Vector3f vectorIV = Vector3f.subtract(vector, vectors[i]);
			final Vector3f vectorJV = Vector3f.subtract(vector, vectors[j]);

			final float scalar = vectorIV.getX() * vectorJV.getX() + vectorIV.getY() * vectorJV.getY() + vectorIV.getZ() * vectorJV.getZ();
			sum += Math.acos(scalar / (Vector3f.length(vectorIV) * Vector3f.length(vectorJV)));
		}

		return Math.round(Math.toDegrees(sum)) == 360;
	}

	public static Vector3f planeNormalVector(final Vector3f vector0, final Vector3f vector1, final Vector3f vector2) {

		final Vector3f vector01 = Vector3f.subtract(vector1, vector0);
		final Vector3f vector02 = Vector3f.subtract(vector2, vector0);

		float a = 1F, b = 1F, c = 1F;

		if (vector01.getX() != 0) {

			final float yx = vector02.getY() * vector01.getX() - vector02.getX() * vector01.getY();
			final float zx = vector02.getZ() * vector01.getX() - vector02.getX() * vector01.getZ();

			if (yx != 0) {

				b = -c * zx / yx;

			} else if (zx != 0) {

				c = -b * yx / zx;

			} else return null;

			a = (-vector01.getY() * b - vector01.getZ() * c) / vector01.getX();

		} else if (vector01.getY() != 0) {

			final float xy = vector02.getX() * vector01.getY() - vector02.getY() * vector01.getX();
			final float zy = vector02.getZ() * vector01.getY() - vector02.getY() * vector01.getZ();

			if (xy != 0) {

				a = -c * zy / xy;

			} else if (zy != 0) {

				c = -a * xy / zy;

			} else return null;

			b = (-vector01.getX() * a - vector01.getZ() * c) / vector01.getY();

		} else if (vector01.getZ() != 0) {

			final float xz = vector02.getX() * vector01.getZ() - vector02.getZ() * vector01.getX();
			final float yz = vector02.getY() * vector01.getZ() - vector02.getZ() * vector01.getY();

			if (xz != 0) {

				a = -b * yz / xz;

			} else if (yz != 0) {

				b = -a * xz / yz;

			} else return null;

			c = (-vector01.getX() * a - vector01.getY() * b) / vector01.getZ();

		} else return null;

		return new Vector3f(a, b, c);
	}
}
