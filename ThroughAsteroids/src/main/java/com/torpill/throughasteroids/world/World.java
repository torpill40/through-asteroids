package com.torpill.throughasteroids.world;

import java.util.ArrayList;
import java.util.HashMap;

import com.torpill.throughasteroids.engine.graphics.Renderer;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.GameObject;
import com.torpill.throughasteroids.objects.blocks.Block;

public class World {

	private final HashMap<Vector3f, Block> blocks = new HashMap<>();

	public ArrayList<Block> getAll() {

		return new ArrayList<>(this.blocks.values());
	}

	public Block getBlock(final Vector3f position) {

		return this.blocks.get(position);
	}

	public void render(final Renderer renderer, final Camera camera) {

		for (final GameObject object : this.blocks.values()) {

			final float distance = Vector3f.length(Vector3f.subtract(object.getPosition(), camera.getPosition()));
			if (distance < 60.0F) {
// App.LOGGER.debug("Rendering {}", object.getName());
				renderer.renderMesh(object, camera);
			}
		}
	}

	public void setBlock(final Block block, final Vector3f position) {

		final Block block0 = new Block(position, block);
		this.blocks.put(position, block0);
	}

	public void update() {

		for (final GameObject object : this.blocks.values()) {

			object.update();
		}
	}
}
