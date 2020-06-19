package com.torpill.throughasteroids.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.torpill.throughasteroids.engine.io.Window;
import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.GameObject;
import com.torpill.throughasteroids.objects.guis.GUIObject;
import com.torpill.throughasteroids.objects.hud.HUDObject;

public class Renderer {

	private final Shader mainShader = new Shader("/mainVertex.glsl", "/mainFragment.glsl");
	private final Shader hudShader = new Shader("/hudVertex.glsl", "/hudFragment.glsl");
	private final Window window;

	public Renderer(final Window window) {

		this.window = window;
	}

	public void destroy() {

		this.mainShader.destroy();
		this.hudShader.destroy();
	}

	public void init() {

		this.mainShader.create();
		this.hudShader.create();
	}

	public void renderHUD(final HUDObject object, final Camera camera) {

		GL30.glBindVertexArray(object.getMesh().getVAO());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, object.getMesh().getMaterial().getTextureID());

		this.hudShader.bind();
		this.hudShader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
		this.hudShader.setUniform("projection", this.window.getProjectionMatrix());
		GL11.glDrawElements(GL11.GL_TRIANGLES, object.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		this.hudShader.unbind();

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	public void renderGUI(final GUIObject object, final Camera camera) {

		GL30.glBindVertexArray(object.getMesh().getVAO());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, object.getMesh().getMaterial().getTextureID());

		this.hudShader.bind();
		this.hudShader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
		this.hudShader.setUniform("projection", this.window.getProjectionMatrix());
		GL11.glDrawElements(GL11.GL_TRIANGLES, object.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		this.hudShader.unbind();

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	public void renderMesh(final GameObject object, final Camera camera) {

		GL30.glBindVertexArray(object.getMesh().getVAO());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, object.getMesh().getMaterial().getTextureID());

		this.mainShader.bind();
		this.mainShader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
		this.mainShader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
		this.mainShader.setUniform("projection", this.window.getProjectionMatrix());
		this.mainShader.setUniform("skycolor", this.window.getBackground());
		this.mainShader.setUniform("selected", object.isSelected());
		GL11.glDrawElements(GL11.GL_TRIANGLES, object.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		this.mainShader.unbind();

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
}
