package com.torpill.throughasteroids.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.torpill.throughasteroids.App;
import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.engine.maths.Vector3f;

public class Window {

	private int width, height;
	private final String title;
	private long window;
	private int frames;
	private long time;
	private Input input;
	private Vector3f background = new Vector3f(0.0F, 0.0F, 0.0F);
	private GLFWWindowSizeCallback sizeCallback;
	private boolean isResized;
	private boolean isFullscreen;
	private final int[] windowPosX = new int[1], windowPosY = new int[1];
	private final Matrix4f projection;

	public Window(final int width, final int height, final String title) {

		this.width = width;
		this.height = height;
		this.title = title;
		this.projection = Matrix4f.projection(70.0F, (float) width / (float) height, 0.1F, 1000.0F);
	}

	public void create() {

		if (!GLFW.glfwInit()) {

			App.LOGGER.error("GLFW wasn't initialized");
			return;
		}

		this.input = new Input();
		this.window = GLFW.glfwCreateWindow(this.width, this.height, this.title, this.isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

		if (this.window == 0) {

			App.LOGGER.error("Window wasn't created");
			return;
		}

		final GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		this.windowPosX[0] = (videoMode.width() - this.width) / 2;
		this.windowPosY[0] = (videoMode.height() - this.height) / 2;
		GLFW.glfwSetWindowPos(this.window, this.windowPosX[0], this.windowPosY[0]);
		GLFW.glfwMakeContextCurrent(this.window);
		GL.createCapabilities();
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		this.createCallbacks();

		GLFW.glfwShowWindow(this.window);

		GLFW.glfwSwapInterval(1);

		this.time = System.currentTimeMillis();
	}

	private void createCallbacks() {

		this.sizeCallback = new GLFWWindowSizeCallback() {

			@Override
			public void invoke(final long window, final int width, final int height) {

				Window.this.width = width;
				Window.this.height = height;
				Window.this.isResized = true;
			}
		};

		GLFW.glfwSetKeyCallback(this.window, this.input.getKeyboardCallback());
		GLFW.glfwSetCursorPosCallback(this.window, this.input.getMouseMoveCallback());
		GLFW.glfwSetMouseButtonCallback(this.window, this.input.getMouseButtonsCallback());
		GLFW.glfwSetScrollCallback(this.window, this.input.getMouseScrollCallback());
		GLFW.glfwSetWindowSizeCallback(this.window, this.sizeCallback);
	}

	public void destroy() {

		this.input.destroy();
		this.sizeCallback.free();
		GLFW.glfwWindowShouldClose(this.window);
		GLFW.glfwDestroyWindow(this.window);
	}

	public int getHeight() {

		return this.height;
	}

	public Matrix4f getProjectionMatrix() {

		return this.projection;
	}

	public String getTitle() {

		return this.title;
	}

	public int getWidth() {

		return this.width;
	}

	public long getWindow() {

		return this.window;
	}

	public boolean isFullscreen() {

		return this.isFullscreen;
	}

	public void setBackgroundColor(final float r, final float g, final float b) {

		this.background = new Vector3f(r, g, b);
	}

	public void setBackgroundColor(final int r, final int g, final int b) {

		this.background = new Vector3f(r / 255.0F, g / 255.0F, b / 255.0F);
	}

	public void setFullscreen(final boolean isFullscreen) {

		this.isFullscreen = isFullscreen;
		this.isResized = true;
		if (this.isFullscreen) {

			GLFW.glfwGetWindowPos(this.window, this.windowPosX, this.windowPosY);
			GLFW.glfwSetWindowMonitor(this.window, GLFW.glfwGetPrimaryMonitor(), 0, 0, this.width, this.height, 0);

		} else {

			GLFW.glfwSetWindowMonitor(this.window, 0, this.windowPosX[0], this.windowPosY[0], this.width, this.height, 0);
		}
	}

	public boolean shouldClose() {

		return GLFW.glfwWindowShouldClose(this.window);
	}

	public void swapBuffers() {

		GLFW.glfwSwapBuffers(this.window);
	}

	public void update() {

		if (this.isResized) {

			GL11.glViewport(0, 0, this.width, this.height);
			this.isResized = false;
		}
		GL11.glClearColor(this.background.getX(), this.background.getY(), this.background.getZ(), 1.0F);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GLFW.glfwPollEvents();

		this.frames++;
		if (System.currentTimeMillis() > this.time + 1000) {

			App.LOGGER.trace("{} fps", this.frames);
			GLFW.glfwSetWindowTitle(this.window, this.title + " | " + this.frames + " FPS");
			this.time = System.currentTimeMillis();
			this.frames = 0;
		}
	}
}
