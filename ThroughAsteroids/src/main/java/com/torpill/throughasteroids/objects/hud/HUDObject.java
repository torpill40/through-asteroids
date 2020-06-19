package com.torpill.throughasteroids.objects.hud;

import com.torpill.throughasteroids.engine.graphics.Vertex;
import com.torpill.throughasteroids.engine.graphics.materials.Material;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.objects.GameObject;
import com.torpill.throughasteroids.objects.Mesh;

public class HUDObject extends GameObject {

	public HUDObject(final Vector3f position, final Vector3f rotation, final Vector2f size, final String path, final String name) {

		super(position, rotation, new Vector3f(size.getX(), size.getY(), 1.0F), mesh(path), name);
	}

	private static Mesh mesh(final String path) {

		final Material material = new Material(path);

		// @formatter:off

		final Mesh mesh = new Mesh(new Vertex[] {

				new Vertex(new Vector3f(-1.0F,  1.0F, 0.0F), new Vector2f(0.0F, 0.0F)),
				new Vertex(new Vector3f(-1.0F, -1.0F, 0.0F), new Vector2f(0.0F, 1.0F)),
				new Vertex(new Vector3f( 1.0F, -1.0F, 0.0F), new Vector2f(1.0F, 1.0F)),
				new Vertex(new Vector3f( 1.0F,  1.0F, 0.0F), new Vector2f(1.0F, 0.0F)),

		}, new int[] {

				0, 1, 3,
				3, 1, 2

		}, material);

		// @formatter:on

		return mesh;
	}
}
