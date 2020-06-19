package com.torpill.throughasteroids.engine.graphics;

import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;

public class Vertex {

	private final Vector3f position;
	private final Vector2f textureCoord;

	public Vertex(final Vector3f position, final Vector2f textureCoord) {

		this.position = position;
		this.textureCoord = textureCoord;
	}

	public Vector3f getPosition() {

		return this.position;
	}

	public Vector2f getTextureCoord() {

		return this.textureCoord;
	}
}
