package com.torpill.throughasteroids.engine.graphics.materials;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.torpill.throughasteroids.App;

public class TextureLoader {

	private static final int BYTES_PER_PIXEL = 4;
	public static int WIDTH = 16, HEIGHT = 16;

	public static BufferedImage loadDefaultImage() {

		final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		final int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < WIDTH; i++) {

			for (int j = 0; j < HEIGHT; j++) {

				if (i >= WIDTH / 2 ^ j < HEIGHT / 2) {

					pixels[i + j * WIDTH] = (255 << 16) + 255;
				}
			}
		}
		return img;
	}

	public static Texture loadDefaultTexture() {

		return loadTexture(loadDefaultImage());
	}

	public static BufferedImage loadImage(final String path) {

		try {

			final URL url = TextureLoader.class.getResource(path);
			if (url == null) throw new IOException();
			return ImageIO.read(url);

		} catch (final IOException e) {

			App.LOGGER.warn("Can't find texture at {}", path);
		}
		return loadDefaultImage();
	}

	public static Texture loadTexture(final BufferedImage image) {

		final int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		final ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL);
		for (int y = 0; y < image.getHeight(); y++) {

			for (int x = 0; x < image.getWidth(); x++) {

				final int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) (pixel >> 16 & 0xFF));
				buffer.put((byte) (pixel >> 8 & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				buffer.put((byte) (pixel >> 24 & 0xFF));
			}
		}
		buffer.flip();

		final int textureID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

		return new Texture(image.getWidth(), image.getHeight(), textureID);
	}

	public static Texture loadTexture(final String path) {

		return loadTexture(loadImage(path));
	}
}