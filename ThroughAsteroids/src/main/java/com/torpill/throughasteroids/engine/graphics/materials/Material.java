package com.torpill.throughasteroids.engine.graphics.materials;

import com.torpill.throughasteroids.App;

public class Material implements IMaterial {

	private final String path;
	private Texture texture;

	public Material(final String path) {

		this.path = path;
	}

	@Override
	public void create() {

		this.texture = TextureLoader.loadTexture("/assets/" + App.APP_ID + "/textures" + this.path);
	}

	@Override
	public Material clone() {

		final Material material = new Material(this.path);
		material.texture = this.texture;
		return material;
	}

	@Override
	public float getHeight() {

		return this.texture.getHeight();
	}

	@Override
	public int getTextureID() {

		return this.texture.getTextureID();
	}

	@Override
	public float getWidth() {

		return this.texture.getWidth();
	}
}
