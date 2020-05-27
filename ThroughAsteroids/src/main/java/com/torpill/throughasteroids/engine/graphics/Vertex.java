package com.torpill.throughasteroids.engine.graphics;

import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;

public class Vertex {

	private final Vector3f position, color;
	private final Vector2f textureCoord;

	public Vertex(final Vector3f position, final Vector3f color, final Vector2f textureCoord) {

		this.position = position;
		this.textureCoord = textureCoord;
		this.color = color;
	}

	public Vector3f getColor() {

		return this.color;
	}

	public Vector3f getPosition() {

		return this.position;
	}

	public Vector2f getTextureCoord() {

		return this.textureCoord;
	}
}
