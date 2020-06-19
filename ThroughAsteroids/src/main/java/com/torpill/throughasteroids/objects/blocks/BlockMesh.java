package com.torpill.throughasteroids.objects.blocks;

import java.util.ArrayList;

import com.torpill.throughasteroids.engine.graphics.Vertex;
import com.torpill.throughasteroids.engine.graphics.materials.IMaterial;
import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.engine.maths.Vector4f;
import com.torpill.throughasteroids.objects.Mesh;

public class BlockMesh extends Mesh {

	// @formatter:off

	private static Vector3f V0 = new Vector3f( 0.5f,  0.5f, -0.5f);
	private static Vector3f V1 = new Vector3f( 0.5f, -0.5f, -0.5f);
	private static Vector3f V2 = new Vector3f(-0.5f, -0.5f, -0.5f);
	private static Vector3f V3 = new Vector3f(-0.5f,  0.5f, -0.5f);
	private static Vector3f V4 = new Vector3f( 0.5f,  0.5f,  0.5f);
	private static Vector3f V5 = new Vector3f( 0.5f, -0.5f,  0.5f);
	private static Vector3f V6 = new Vector3f(-0.5f, -0.5f,  0.5f);
	private static Vector3f V7 = new Vector3f(-0.5f,  0.5f,  0.5f);

	private static final Vertex[] VERTICES = new Vertex[] {
			//Back face
			new Vertex(V0, new Vector2f(0.5f , 0.5f)),
			new Vertex(V1, new Vector2f(0.5f , 1.0f)),
			new Vertex(V2, new Vector2f(0.75f, 1.0f)),
			new Vertex(V3, new Vector2f(0.75f, 0.5f)),

			//Front face
			new Vertex(V7, new Vector2f(0.0f , 0.5f)),
			new Vertex(V6, new Vector2f(0.0f , 1.0f)),
			new Vertex(V5, new Vector2f(0.25f, 1.0f)),
			new Vertex(V4, new Vector2f(0.25f, 0.5f)),

			//Right face
			new Vertex(V4, new Vector2f(0.25f, 0.5f)),
			new Vertex(V5, new Vector2f(0.25f, 1.0f)),
			new Vertex(V1, new Vector2f(0.5f , 1.0f)),
			new Vertex(V0, new Vector2f(0.5f , 0.5f)),

			//Left face
			new Vertex(V3, new Vector2f(0.75f, 0.5f)),
			new Vertex(V2, new Vector2f(0.75f, 1.0f)),
			new Vertex(V6, new Vector2f(1.0f , 1.0f)),
			new Vertex(V7, new Vector2f(1.0f , 0.5f)),

			//Top face
			new Vertex(V3, new Vector2f(0.25f, 0.0f)),
			new Vertex(V7, new Vector2f(0.25f, 0.5f)),
			new Vertex(V4, new Vector2f(0.5f , 0.5f)),
			new Vertex(V0, new Vector2f(0.5f , 0.0f)),

			//Bottom face
			new Vertex(V2, new Vector2f(0.5f , 0.0f)),
			new Vertex(V6, new Vector2f(0.5f , 0.5f)),
			new Vertex(V5, new Vector2f(0.75f, 0.5f)),
			new Vertex(V1, new Vector2f(0.75f, 0.0f)),
	};

	private static final int[] INDICES = new int[] {
			//Back face
			0, 1, 3,
			3, 1, 2,

			//Front face
			4, 5, 7,
			7, 5, 6,

			//Right face
			8, 9, 11,
			11, 9, 10,

			//Left face
			12, 13, 15,
			15, 13, 14,

			//Top face
			16, 17, 19,
			19, 17, 18,

			//Bottom face
			20, 21, 23,
			23, 21, 22
	};

	// @formatter:on

	private final ArrayList<Matrix4f> faces = new ArrayList<>();

	public BlockMesh(final IMaterial material) {

		super(VERTICES, INDICES, material);

		this.faces.add(new Matrix4f(new Vector4f(V3, 0F), new Vector4f(V7, 0F), new Vector4f(V4, 0F), new Vector4f(V0, 0F)));
		this.faces.add(new Matrix4f(new Vector4f(V2, 0F), new Vector4f(V6, 0F), new Vector4f(V5, 0F), new Vector4f(V1, 0F)));
		this.faces.add(new Matrix4f(new Vector4f(V7, 0F), new Vector4f(V6, 0F), new Vector4f(V5, 0F), new Vector4f(V4, 0F)));
		this.faces.add(new Matrix4f(new Vector4f(V4, 0F), new Vector4f(V5, 0F), new Vector4f(V1, 0F), new Vector4f(V0, 0F)));
		this.faces.add(new Matrix4f(new Vector4f(V0, 0F), new Vector4f(V1, 0F), new Vector4f(V2, 0F), new Vector4f(V3, 0F)));
		this.faces.add(new Matrix4f(new Vector4f(V3, 0F), new Vector4f(V2, 0F), new Vector4f(V6, 0F), new Vector4f(V7, 0F)));
	}

	@Override
	public BlockMesh clone() {

		final BlockMesh mesh = new BlockMesh(this.material.clone());
		mesh.create();
		return mesh;
	}
}
