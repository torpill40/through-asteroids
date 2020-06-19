package com.torpill.throughasteroids;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import com.torpill.throughasteroids.engine.graphics.Renderer;
import com.torpill.throughasteroids.engine.graphics.guis.GUI;
import com.torpill.throughasteroids.engine.graphics.guis.MainTitleGUI;
import com.torpill.throughasteroids.engine.graphics.hud.HUD;
import com.torpill.throughasteroids.engine.graphics.hud.InGameHUD;
import com.torpill.throughasteroids.engine.io.CameraPicker;
import com.torpill.throughasteroids.engine.io.Input;
import com.torpill.throughasteroids.engine.io.MousePicker;
import com.torpill.throughasteroids.engine.io.Window;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.blocks.Blocks;
import com.torpill.throughasteroids.objects.guis.GUIObjects;
import com.torpill.throughasteroids.objects.hud.HUDObjects;
import com.torpill.throughasteroids.world.World;

public class App implements Runnable {

	public static final String APP_NAME = "Through Asteroids";
	public static final String APP_ID = "throughasteroids";
	public static final Logger LOGGER = LogManager.getLogger(APP_NAME);
	public static final int WIDTH = 1280, HEIGHT = 720;

	public static App instance;

	public GameState state;

	public Thread game;
	public Window window;
	public Renderer renderer;

	public World world;
	public HUD hud;
	public GUI gui;

	public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));
	public CameraPicker cameraPicker;
	public MousePicker mousePicker;

	public static void main(final String[] args) {

		instance = new App();
		instance.start();
	}

	private void close() {

		this.window.destroy();
		Blocks.destroy();
		HUDObjects.destroy();
		GUIObjects.destroy();
		this.renderer.destroy();
	}

	public void init() {

		LOGGER.debug("Initializing Game");

		this.state = GameState.PAUSE;
		this.camera.getRotation().set(-50, 0, 0);
		this.camera.getPosition().set(0, 15, 4);

		this.window = new Window(WIDTH, HEIGHT, APP_NAME);
		this.renderer = new Renderer(this.window);
		this.world = new World();
		this.hud = new InGameHUD();
		this.gui = new MainTitleGUI();
		this.cameraPicker = new CameraPicker(this.camera);
		this.mousePicker = new MousePicker(this.camera, this.window.getProjectionMatrix());
		this.window.setBackgroundColor(30, 30, 38);
		this.window.create();
		this.renderer.init();
		Blocks.create();
		HUDObjects.create();
		GUIObjects.create();

		this.world.setBlock(Blocks.WALL_BRICK, new Vector3f(0, 0, 0));
		this.world.setBlock(Blocks.WALL_BRICK, new Vector3f(0, 0, -1));
		this.world.setBlock(Blocks.WALL_BRICK, new Vector3f(1, 0, 0));
		this.world.setBlock(Blocks.WALL_BRICK, new Vector3f(1, 0, -1));

		this.hud.init();
		this.gui.init();
	}

	private void render() {

		LOGGER.trace("Rendering Game");
		this.world.render(this.renderer, this.camera);
// this.hud.render(this.renderer, this.camera);
		this.gui.render(this.renderer, this.camera);
		this.window.swapBuffers();
	}

	@Override
	public void run() {

		this.init();
		while (!this.window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {

			this.update();
			this.render();
			this.window.mouseState(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT));
		}
		App.LOGGER.debug("Terminating Game");
		this.close();
	}

	public void start() {

		this.game = new Thread(this, APP_ID);
		this.game.start();
	}

	public World getWorld() {

		return this.world;
	}

	private void update() {

		LOGGER.trace("Updating Game");
		this.window.update();
		this.camera.update();
		this.world.update();
		this.hud.update();
		this.gui.update();

		if (this.state == GameState.BUILD) {

			if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {

				this.cameraPicker.update(this.world.getAll());
				final Vector3f cameraRay = this.cameraPicker.getCurrentRay();
				LOGGER.trace("Camera ray [{} {} {}]", cameraRay.getX(), cameraRay.getY(), cameraRay.getZ());

			} else {

				this.mousePicker.update(this.world.getAll());
				final Vector3f mouseRay = this.mousePicker.getCurrentRay();
				LOGGER.trace("Mouse ray [{} {} {}]", mouseRay.getX(), mouseRay.getY(), mouseRay.getZ());
			}

		} else if (this.state == GameState.PAUSE) {

			this.mousePicker.update(this.gui.getAll());
			final Vector3f mouseRay = this.mousePicker.getCurrentRay();
			LOGGER.trace("Mouse ray [{} {} {}]", mouseRay.getX(), mouseRay.getY(), mouseRay.getZ());
		}
	}

	public enum GameState {

		PLAY, BUILD, PAUSE
	}
}
