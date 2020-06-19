package com.torpill.throughasteroids.objects.blocks;

import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.objects.GameObject;

public class Block extends GameObject {

	public Block(final BlockMesh mesh, final String name) {

		this(null, mesh, name);
	}

	public Block(final Vector3f position, final Block block) {

		this(position, (BlockMesh) block.getMesh(), block.getName());
	}

	public Block(final Vector3f position, final BlockMesh mesh, final String name) {

		super(position, new Vector3f(0, 0, 0), new Vector3f(1.0F, 1.0F, 1.0F), mesh, name);
	}
}
