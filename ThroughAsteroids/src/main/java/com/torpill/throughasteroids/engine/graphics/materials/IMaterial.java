package com.torpill.throughasteroids.engine.graphics.materials;

import org.lwjgl.opengl.GL11;

public interface IMaterial extends Cloneable {

	void create();

	IMaterial clone();

	int getTextureID();

	float getHeight();

	float getWidth();

	default void destroy() {

		GL11.glDeleteTextures(this.getTextureID());
	}
}
