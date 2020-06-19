package com.torpill.throughasteroids.objects.guis;

import com.torpill.throughasteroids.engine.graphics.Vertex;
import com.torpill.throughasteroids.engine.graphics.materials.IMaterial;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.objects.Mesh;

public class GUIMesh extends Mesh {

	// @formatter:off

	private static final Vertex[] VERTICES = new Vertex[] {

			new Vertex(new Vector3f(-1.0F,  1.0F, 0.0F), new Vector2f(0.0F, 0.0F)),
			new Vertex(new Vector3f(-1.0F, -1.0F, 0.0F), new Vector2f(0.0F, 1.0F)),
			new Vertex(new Vector3f( 1.0F, -1.0F, 0.0F), new Vector2f(1.0F, 1.0F)),
			new Vertex(new Vector3f( 1.0F,  1.0F, 0.0F), new Vector2f(1.0F, 0.0F)),
	};

	private static final int[] INDICES = new int[] {

			0, 1, 3,
			3, 1, 2,
	};

	// @formatter:on

	public GUIMesh(final IMaterial material) {

		super(VERTICES, INDICES, material);
	}

	@Override
	public GUIMesh clone() {

		final GUIMesh mesh = new GUIMesh(this.material.clone());
		mesh.create();
		return mesh;
	}
}
