package com.torpill.throughasteroids.engine.graphics.materials;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.torpill.throughasteroids.App;

public class GUIButtonMaterial implements IMaterial {

	private final Texture[] texture = new Texture[4];
	private int state;
	private final int type, color;

	public GUIButtonMaterial(final int type, final int color) {

		this.type = type;
		this.color = color;
	}

	@Override
	public GUIButtonMaterial clone() {

		final GUIButtonMaterial material = new GUIButtonMaterial(this.type, this.color);
		for (int i = 0; i < this.texture.length; i++) {

			material.texture[i] = this.texture[i];
		}
		return material;
	}

	@Override
	public void create() {

		final BufferedImage base = TextureLoader.loadImage("/assets/" + App.APP_ID + "/textures/gui/button.png");
		final BufferedImage imgs[] = new BufferedImage[4];
		final int startX = 80 * this.type;
		final int startY = 96 * this.color;

		for (int i = 0; i < 3; i++) {

			imgs[i] = new BufferedImage(80, 24, BufferedImage.TYPE_INT_ARGB);

			final Graphics g = imgs[i].createGraphics();
			g.drawImage(base, 0, 0, 79, 23, startX, 24 * i + startY, startX + 79, 24 * i + startY + 23, null);
			g.dispose();

			this.texture[i] = TextureLoader.loadTexture(imgs[i]);
		}

		imgs[3] = new BufferedImage(80, 24, BufferedImage.TYPE_INT_ARGB);
		final Graphics g = imgs[3].createGraphics();
		g.drawImage(base, 0, 0, 79, 23, startX, 24 * 3, startX + 79, 24 * 3 + 23, null);
		g.dispose();

		this.texture[3] = TextureLoader.loadTexture(imgs[3]);

		for (int i = 0; i < this.texture.length; i++) {

			if (this.texture[i] == null) {

				this.texture[i] = TextureLoader.loadDefaultTexture();
			}
		}
	}

	public void setState(final int state) {

		this.state = state;
	}

	@Override
	public float getHeight() {

		return this.texture[this.state].getHeight();
	}

	@Override
	public int getTextureID() {

		return this.texture[this.state].getTextureID();
	}

	@Override
	public float getWidth() {

		return this.texture[this.state].getWidth();
	}

}
