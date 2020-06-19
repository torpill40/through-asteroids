package com.torpill.throughasteroids.engine.graphics.materials;

public class Texture {

	private final int width, height;
	private final int textureID;

	public Texture(final int width, final int height, final int textureID) {

		this.width = width;
		this.height = height;
		this.textureID = textureID;
	}

	public int getHeight() {

		return this.height;
	}

	public int getTextureID() {

		return this.textureID;
	}

	public int getWidth() {

		return this.width;
	}
}
