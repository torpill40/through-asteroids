package com.torpill.throughasteroids.engine.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import com.torpill.throughasteroids.App;
import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.engine.utils.FileUtils;

public class Shader {

	private final String vertexFile, fragmentFile;
	private int vertexID, fragmentID, programID;

	public Shader(final String vertexPath, final String fragmentPath) {

		this.vertexFile = FileUtils.loadAsString("/shaders" + vertexPath);
		this.fragmentFile = FileUtils.loadAsString("/shaders" + fragmentPath);
	}

	public void bind() {

		GL20.glUseProgram(this.programID);
	}

	public void create() {

		this.programID = GL20.glCreateProgram();
		this.vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

		GL20.glShaderSource(this.vertexID, this.vertexFile);
		GL20.glCompileShader(this.vertexID);

		if (GL20.glGetShaderi(this.vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

			App.LOGGER.error("Vertex Shader: {}", GL20.glGetShaderInfoLog(this.vertexID));
			return;
		}

		this.fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

		GL20.glShaderSource(this.fragmentID, this.fragmentFile);
		GL20.glCompileShader(this.fragmentID);

		if (GL20.glGetShaderi(this.fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

			App.LOGGER.error("Fragment Shader: {}", GL20.glGetShaderInfoLog(this.fragmentID));
			return;
		}

		GL20.glAttachShader(this.programID, this.vertexID);
		GL20.glAttachShader(this.programID, this.fragmentID);

		GL20.glLinkProgram(this.programID);
		if (GL20.glGetProgrami(this.programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {

			App.LOGGER.error("Program Linking: {}", GL20.glGetProgramInfoLog(this.programID));
			return;
		}

		GL20.glValidateProgram(this.programID);
		if (GL20.glGetProgrami(this.programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {

			App.LOGGER.error("Program Validation: {}", GL20.glGetProgramInfoLog(this.programID));
			return;
		}
	}

	public void destroy() {

		GL20.glDetachShader(this.programID, this.vertexID);
		GL20.glDetachShader(this.programID, this.fragmentID);
		GL20.glDeleteProgram(this.programID);
		GL20.glDeleteShader(this.vertexID);
		GL20.glDeleteShader(this.fragmentID);
	}

	public int getUniformLocation(final String name) {

		return GL20.glGetUniformLocation(this.programID, name);
	}

	public void setUniform(final String name, final boolean value) {

		GL20.glUniform1i(this.getUniformLocation(name), value ? 1 : 0);
	}

	public void setUniform(final String name, final float value) {

		GL20.glUniform1f(this.getUniformLocation(name), value);
	}

	public void setUniform(final String name, final int value) {

		GL20.glUniform1i(this.getUniformLocation(name), value);
	}

	public void setUniform(final String name, final Matrix4f value) {

		final FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4f.SIZE * Matrix4f.SIZE);
		matrix.put(value.getAll()).flip();
		GL20.glUniformMatrix4fv(this.getUniformLocation(name), true, matrix);
	}

	public void setUniform(final String name, final Vector2f value) {

		GL20.glUniform2f(this.getUniformLocation(name), value.getX(), value.getY());
	}

	public void setUniform(final String name, final Vector3f value) {

		GL20.glUniform3f(this.getUniformLocation(name), value.getX(), value.getY(), value.getZ());
	}

	public void unbind() {

		GL20.glUseProgram(0);
	}
}