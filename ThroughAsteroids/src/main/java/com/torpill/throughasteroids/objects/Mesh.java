package com.torpill.throughasteroids.objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import com.torpill.throughasteroids.engine.graphics.Vertex;
import com.torpill.throughasteroids.engine.graphics.materials.IMaterial;

public class Mesh implements Cloneable {

	private final Vertex[] vertices;
	private final int[] indices;
	protected final IMaterial material;
	private int vao, pbo, ibo, cbo, tbo;

	public Mesh(final Vertex[] vertices, final int[] indices, final IMaterial material) {

		this.vertices = vertices;
		this.indices = indices;
		this.material = material;
	}

	public void create() {

		this.material.create();

		this.vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(this.vao);

		final FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(this.vertices.length * 3);
		final float[] positionData = new float[this.vertices.length * 3];
		for (int i = 0; i < this.vertices.length; i++) {

			positionData[i * 3] = this.vertices[i].getPosition().getX();
			positionData[i * 3 + 1] = this.vertices[i].getPosition().getY();
			positionData[i * 3 + 2] = this.vertices[i].getPosition().getZ();
		}
		positionBuffer.put(positionData).flip();

		this.pbo = this.storeData(positionBuffer, 0, 3);

		final FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(this.vertices.length * 2);
		final float[] textureData = new float[this.vertices.length * 2];
		for (int i = 0; i < this.vertices.length; i++) {

			textureData[i * 2] = this.vertices[i].getTextureCoord().getX();
			textureData[i * 2 + 1] = this.vertices[i].getTextureCoord().getY();
		}
		textureBuffer.put(textureData).flip();

		this.tbo = this.storeData(textureBuffer, 2, 2);

		final IntBuffer indicesBuffer = MemoryUtil.memAllocInt(this.indices.length);
		indicesBuffer.put(this.indices).flip();

		this.ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void destroy() {

		GL15.glDeleteBuffers(this.pbo);
		GL15.glDeleteBuffers(this.cbo);
		GL15.glDeleteBuffers(this.tbo);
		GL15.glDeleteBuffers(this.ibo);

		GL30.glDeleteVertexArrays(this.vao);

		this.material.destroy();
	}

	public int getCBO() {

		return this.cbo;
	}

	public int getIBO() {

		return this.ibo;
	}

	public int[] getIndices() {

		return this.indices;
	}

	public IMaterial getMaterial() {

		return this.material;
	}

	public int getPBO() {

		return this.pbo;
	}

	public int getTBO() {

		return this.tbo;
	}

	public int getVAO() {

		return this.vao;
	}

	public Vertex[] getVertices() {

		return this.vertices;
	}

	private int storeData(final FloatBuffer buffer, final int index, final int size) {

		final int bufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return bufferID;
	}

	@Override
	public Mesh clone() {

		final Mesh mesh = new Mesh(this.vertices, this.indices, this.material.clone());
		mesh.create();
		return mesh;
	}
}
