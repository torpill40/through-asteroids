package com.torpill.throughasteroids.objects;

import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.engine.maths.Vector4f;

public class HitBox {

	private static final Vector4f V0 = new Vector4f(0.5F, 0.5F, -0.5F, 1F);
	private static final Vector4f V1 = new Vector4f(0.5F, -0.5F, -0.5F, 1F);
	private static final Vector4f V2 = new Vector4f(-0.5F, -0.5F, -0.5F, 1F);
	private static final Vector4f V3 = new Vector4f(-0.5F, 0.5F, -0.5F, 1F);
	private static final Vector4f V4 = new Vector4f(0.5F, 0.5F, 0.5F, 1F);
	private static final Vector4f V5 = new Vector4f(0.5F, -0.5F, 0.5F, 1F);
	private static final Vector4f V6 = new Vector4f(-0.5F, -0.5F, 0.5F, 1F);
	private static final Vector4f V7 = new Vector4f(-0.5F, 0.5F, 0.5F, 1F);

	private final Vector4f[] V = new Vector4f[8];

	private final GameObject object;

	public HitBox(final GameObject object) {

		this.object = object;
		for (int i = 0; i < this.V.length; i++) {

			this.V[i] = new Vector4f(0, 0, 0, 0);
		}
	}

	public Vector4f getCorner(final int corner) {

		return this.V[corner];
	}

	public void update() {

		final Matrix4f transform = Matrix4f.transform(this.object.getPosition(), new Vector3f(0, 0, 0), this.object.getScale());
		this.V[0].set(Vector4f.multiply(transform, V0));
		this.V[1].set(Vector4f.multiply(transform, V1));
		this.V[2].set(Vector4f.multiply(transform, V2));
		this.V[3].set(Vector4f.multiply(transform, V3));
		this.V[4].set(Vector4f.multiply(transform, V4));
		this.V[5].set(Vector4f.multiply(transform, V5));
		this.V[6].set(Vector4f.multiply(transform, V6));
		this.V[7].set(Vector4f.multiply(transform, V7));
	}
}
