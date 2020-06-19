package com.torpill.throughasteroids.engine.graphics.materials;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.torpill.throughasteroids.App;

public class BlockMaterial implements IMaterial {

	private Texture texture;

	private final String paths[] = new String[6];

	public BlockMaterial(final String path) {

		this(toArray(path));
	}

	public BlockMaterial(final String path0, final String path1, final String path2, final String path3, final String path4, final String path5) {

		this(new String[] {
				path0, path1, path2, path3, path4, path5
		});
	}

	public BlockMaterial(final String[] paths) {

		for (int i = 0; i < paths.length; i++) {

			this.paths[i] = paths[i];
		}
	}

	private static String[] toArray(final String path) {

		final String[] paths = new String[6];
		for (int i = 0; i < paths.length; i++) {

			paths[i] = path;
		}
		return paths;
	}

	@Override
	public BlockMaterial clone() {

		final BlockMaterial material = new BlockMaterial(this.paths);
		material.texture = this.texture;
		return material;
	}

	@Override
	public void create() {

		final BufferedImage[] imgs = new BufferedImage[6];
		for (int i = 0; i < imgs.length; i++) {

			imgs[i] = TextureLoader.loadImage("/assets/" + App.APP_ID + "/textures" + this.paths[i]);
		}

		final BufferedImage img = new BufferedImage(TextureLoader.WIDTH * 4, TextureLoader.HEIGHT * 2, BufferedImage.TYPE_INT_ARGB);
		final Graphics g = img.createGraphics();
		// TOP
		g.drawImage(imgs[0], TextureLoader.WIDTH, 0, null);
		// BOTTOM
		g.drawImage(imgs[1], TextureLoader.WIDTH * 2, 0, null);
		// FRONT
		g.drawImage(imgs[2], 0, TextureLoader.HEIGHT, null);
		// RIGHT
		g.drawImage(imgs[3], TextureLoader.WIDTH, TextureLoader.HEIGHT, null);
		// BACK
		g.drawImage(imgs[4], TextureLoader.WIDTH * 2, TextureLoader.HEIGHT, null);
		// LEFT
		g.drawImage(imgs[5], TextureLoader.WIDTH * 3, TextureLoader.HEIGHT, null);
		g.dispose();

		this.texture = TextureLoader.loadTexture(img);
		if (this.texture == null) {

			this.texture = TextureLoader.loadDefaultTexture();
		}
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

	public void set(final int face, final String path) {

		this.paths[face] = path;
	}

	public static class Wall extends BlockMaterial {

		public Wall(final String path) {

			super(path, "/blocks/wall.png", "/blocks/wall.png", "/blocks/wall.png", "/blocks/wall.png", "/blocks/wall.png");
		}
	}
}
