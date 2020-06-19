package com.torpill.throughasteroids.engine.maths;

public class Intersectionf {

	public static boolean intersectRayAAB(final float originX, final float originY, final float originZ, final float dirX, final float dirY, final float dirZ, final float minX, final float minY, final float minZ, final float maxX, final float maxY, final float maxZ, final Vector2f result) {

		final float invDirX = 1.0f / dirX, invDirY = 1.0f / dirY, invDirZ = 1.0f / dirZ;
		float tNear, tFar, tymin, tymax, tzmin, tzmax;
		if (invDirX >= 0.0f) {
			tNear = (minX - originX) * invDirX;
			tFar = (maxX - originX) * invDirX;
		} else {
			tNear = (maxX - originX) * invDirX;
			tFar = (minX - originX) * invDirX;
		}
		if (invDirY >= 0.0f) {
			tymin = (minY - originY) * invDirY;
			tymax = (maxY - originY) * invDirY;
		} else {
			tymin = (maxY - originY) * invDirY;
			tymax = (minY - originY) * invDirY;
		}
		if (tNear > tymax || tymin > tFar) return false;
		if (invDirZ >= 0.0f) {
			tzmin = (minZ - originZ) * invDirZ;
			tzmax = (maxZ - originZ) * invDirZ;
		} else {
			tzmin = (maxZ - originZ) * invDirZ;
			tzmax = (minZ - originZ) * invDirZ;
		}
		if (tNear > tzmax || tzmin > tFar) return false;
		tNear = tymin > tNear || Float.isNaN(tNear) ? tymin : tNear;
		tFar = tymax < tFar || Float.isNaN(tFar) ? tymax : tFar;
		tNear = tzmin > tNear ? tzmin : tNear;
		tFar = tzmax < tFar ? tzmax : tFar;
		if (tNear < tFar && tFar >= 0.0f) {
			result.setX(tNear);
			result.setY(tFar);
			return true;
		}
		return false;
	}

	public static boolean intersectRayAAB(final Vector3f origin, final Vector3f dir, final Vector3f min, final Vector3f max, final Vector2f result) {

		return intersectRayAAB(origin.getX(), origin.getY(), origin.getZ(), dir.getX(), dir.getY(), dir.getZ(), min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ(), result);
	}
}
