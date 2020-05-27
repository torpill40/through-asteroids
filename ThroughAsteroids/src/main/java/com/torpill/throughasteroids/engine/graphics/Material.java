package com.torpill.throughasteroids.engine.graphics;

import org.lwjgl.opengl.GL11;

import com.torpill.throughasteroids.App;

public class Material {

	private final String path;
	private Texture texture;
	private float width, height;
	private int textureID;

	public Material(final String path) {

		this.path = path;
	}

	public void create() {

		this.texture = TextureLoader.loadTexture("/assets/" + App.APP_ID + "/textures" + this.path);
		if (this.texture == null) {

			this.texture = TextureLoader.loadDefaultTexture();
		}
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
		this.textureID = this.texture.getTextureID();
	}

	public void destroy() {

		GL11.glDeleteTextures(this.textureID);
	}

	public float getHeight() {

		return this.height;
	}

	public int getTextureID() {

		return this.textureID;
	}

	public float getWidth() {

		return this.width;
	}
}
