package com.torpill.throughasteroids;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.torpill.throughasteroids.engine.io.Window;

public class App implements Runnable {

	public static final String APP_NAME = "Through Asteroids";
	public static final String APP_ID = "throughasteroids";
	public static final Logger LOGGER = LogManager.getLogger(APP_NAME);

	public Thread game;
	public Window window;
	public final int WIDTH = 1280, HEIGHT = 720;

	public void start() {

		this.game = new Thread(this, APP_ID);
		this.game.start();
	}

	public void init() {

		LOGGER.debug("Initializing Game");
		this.window = new Window(this.WIDTH, this.HEIGHT, APP_NAME);
		this.window.create();
	}

	@Override
	public void run() {

		this.init();
		while (!this.window.shouldClose()) {

			this.update();
			this.render();
		}
	}

	private void update() {

		LOGGER.trace("Updating Game");
		this.window.update();
	}

	private void render() {

		LOGGER.trace("Rendering Game");
		this.window.swapBuffers();
	}

	public static void main(String[] args) {

		new App().start();
	}
}
