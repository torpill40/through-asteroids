package com.torpill.throughasteroids.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import com.torpill.throughasteroids.App;

public class Window {

	private int width, height;
	private String title;
	private long window;
	public int frames;
	public long time;

	public Window(int width, int height, String title) {

		this.width = width;
		this.height = height;
		this.title = title;
	}

	public void create() {

		if (!GLFW.glfwInit()) {

			App.LOGGER.error("GLFW wasn't initialized");
			return;
		}

		this.window = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0, 0);

		if (this.window == 0) {

			App.LOGGER.error("Window wasn't created");
			return;
		}

		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(this.window, (videoMode.width() - this.width) / 2, (videoMode.height() - this.height) / 2);
		GLFW.glfwMakeContextCurrent(this.window);

		GLFW.glfwShowWindow(this.window);

		GLFW.glfwSwapInterval(1);

		this.time = System.currentTimeMillis();
	}

	public void update() {

		GLFW.glfwPollEvents();

		this.frames++;
		if (System.currentTimeMillis() > time + 1000) {

			App.LOGGER.debug("{} fps", frames);
			GLFW.glfwSetWindowTitle(this.window, this.title + " | " + this.frames + " FPS");
			this.time = System.currentTimeMillis();
			this.frames = 0;
		}
	}

	public void swapBuffers() {

		GLFW.glfwSwapBuffers(this.window);
	}

	public boolean shouldClose() {

		return GLFW.glfwWindowShouldClose(this.window);
	}
}
