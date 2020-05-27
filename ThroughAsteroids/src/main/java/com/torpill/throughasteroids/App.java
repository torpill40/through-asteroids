package com.torpill.throughasteroids;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import com.torpill.throughasteroids.engine.graphics.Material;
import com.torpill.throughasteroids.engine.graphics.Mesh;
import com.torpill.throughasteroids.engine.graphics.Renderer;
import com.torpill.throughasteroids.engine.graphics.Shader;
import com.torpill.throughasteroids.engine.graphics.Vertex;
import com.torpill.throughasteroids.engine.io.Input;
import com.torpill.throughasteroids.engine.io.Window;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.GameObject;

public class App implements Runnable {

	public static final String APP_NAME = "Through Asteroids";
	public static final String APP_ID = "throughasteroids";
	public static final Logger LOGGER = LogManager.getLogger(APP_NAME);
	public static final int WIDTH = 1280, HEIGHT = 720;

	public Thread game;
	public Window window;
	public Renderer renderer;
	public Shader shader;

	// @formatter:off

	public Mesh mesh = new Mesh(new Vertex[] {
			new Vertex(new Vector3f(-0.5F, 0.5F, 0.0F), new Vector3f(1.0F, 0.0f, 0.0f), new Vector2f(0.0F, 0.0F)),
			new Vertex(new Vector3f(-0.5F, -0.5F, 0.0F), new Vector3f(0.0F, 1.0f, 0.0f), new Vector2f(0.0F, 1.0F)),
			new Vertex(new Vector3f(0.5F, -0.5F, 0.0F), new Vector3f(0.0F, 0.0f, 1.0f), new Vector2f(1.0F, 1.0F)),
			new Vertex(new Vector3f(0.5F, 0.5F, 0.0F), new Vector3f(1.0F, 1.0f, 0.0f), new Vector2f(1.0F, 0.0F))
	}, new int[] {
			0, 1, 2,
			0, 3, 2
	}, new Material("/blocks/wall_brick.png"));

	// @formatter:on

	public GameObject object = new GameObject(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), this.mesh);

	public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));

	public static void main(final String[] args) {

		new App().start();
	}

	private void close() {

		this.window.destroy();
		this.mesh.destroy();
		this.shader.destroy();
	}

	public void init() {

		LOGGER.debug("Initializing Game");
		this.window = new Window(WIDTH, HEIGHT, APP_NAME);
		this.shader = new Shader("/mainVertex.glsl", "/mainFragment.glsl");
		this.renderer = new Renderer(this.window, this.shader);
		this.window.setBackgroundColor(30, 30, 38);
		this.window.create();
		this.mesh.create();
		this.shader.create();
	}

	private void render() {

		LOGGER.trace("Rendering Game");
		this.renderer.renderMesh(this.object, this.camera);
		this.window.swapBuffers();
	}

	@Override
	public void run() {

		this.init();
		while (!this.window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {

			this.update();
			this.render();
			if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
				this.window.setFullscreen(!this.window.isFullscreen());
			}
		}
		App.LOGGER.debug("Terminating Game");
		this.close();
	}

	public void start() {

		this.game = new Thread(this, APP_ID);
		this.game.start();
	}

	private void update() {

		LOGGER.trace("Updating Game");
		this.window.update();
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			LOGGER.debug("x{} y{} sx{} sy{}", Input.getMouseX(), Input.getMouseY(), Input.getScrollX(), Input.getScrollY());
		}
	}
}
