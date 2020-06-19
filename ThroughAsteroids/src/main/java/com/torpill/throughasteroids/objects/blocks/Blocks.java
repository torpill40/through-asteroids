package com.torpill.throughasteroids.objects.blocks;

import com.torpill.throughasteroids.engine.graphics.materials.BlockMaterial;

public class Blocks {

	public static final Block WALL = new Block(new BlockMesh(new BlockMaterial.Wall("/blocks/wall.png")), "WALL");
	public static final Block WALL_EDGE = new Block(new BlockMesh(new BlockMaterial.Wall("/blocks/wall_edge.png")), "WALL_EDGE");
	public static final Block WALL_DUST = new Block(new BlockMesh(new BlockMaterial.Wall("/blocks/wall_dust.png")), "WALL_DUST");
	public static final Block WALL_BRICK = new Block(new BlockMesh(new BlockMaterial.Wall("/blocks/wall_brick.png")), "WALL_BRICK");
	public static final Block WALL_ORE = new Block(new BlockMesh(new BlockMaterial.Wall("/blocks/wall_ore.png")), "WALL_ORE");

	public static void create() {

		create(WALL);
		create(WALL_EDGE);
		create(WALL_DUST);
		create(WALL_BRICK);
		create(WALL_ORE);
	}

	public static void create(final Block block) {

		block.getMesh().create();
	}

	public static void destroy() {

		destroy(WALL);
		destroy(WALL_EDGE);
		destroy(WALL_DUST);
		destroy(WALL_BRICK);
		destroy(WALL_ORE);
	}

	public static void destroy(final Block block) {

		block.getMesh().destroy();
	}
}
