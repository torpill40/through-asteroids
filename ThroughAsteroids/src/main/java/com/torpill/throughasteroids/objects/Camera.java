package com.torpill.throughasteroids.objects;

import org.lwjgl.glfw.GLFW;

import com.torpill.throughasteroids.App;
import com.torpill.throughasteroids.App.GameState;
import com.torpill.throughasteroids.engine.io.Input;
import com.torpill.throughasteroids.engine.maths.Vector3f;

public class Camera {

	private Vector3f position, rotation;
	private final float moveSpeed = 0.15F, mouseSensitivity = 0.15f;
	private float distance = 2.0F, horizontalAngle = 0, verticalAngle = 0;
	private double oldMouseX = 0, oldMouseY = 0, newMouseX, newMouseY;

	public Camera(final Vector3f position, final Vector3f rotation) {

		this.position = position;
		this.rotation = rotation;
	}

	public Vector3f getPosition() {

		return this.position;
	}

	public Vector3f getRotation() {

		return this.rotation;
	}

	public void update() {

		this.newMouseX = Input.getMouseX();
		this.newMouseY = Input.getMouseY();

		if (App.instance.state == GameState.BUILD) {

			final float x = (float) Math.sin(Math.toRadians(this.rotation.getY())) * this.moveSpeed;
			final float z = (float) Math.cos(Math.toRadians(this.rotation.getY())) * this.moveSpeed;

			if (Input.isKeyDown(GLFW.GLFW_KEY_A)) {
				this.position = Vector3f.add(this.position, new Vector3f(-z, 0, x));
			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_D)) {
				this.position = Vector3f.add(this.position, new Vector3f(z, 0, -x));
			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_W)) {
				this.position = Vector3f.add(this.position, new Vector3f(-x, 0, -z));
			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_S)) {
				this.position = Vector3f.add(this.position, new Vector3f(x, 0, z));
			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
				this.position = Vector3f.add(this.position, new Vector3f(0, this.moveSpeed, 0));
			}
			if (Input.isKeyDown(GLFW.GLFW_KEY_Q)) {
				this.position = Vector3f.add(this.position, new Vector3f(0, -this.moveSpeed, 0));
			}

			final float dx = (float) (this.newMouseX - this.oldMouseX);
			final float dy = (float) (this.newMouseY - this.oldMouseY);

			if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {

				this.rotation = Vector3f.add(this.rotation, new Vector3f(-dy * this.mouseSensitivity, -dx * this.mouseSensitivity, 0));
				if (this.rotation.getX() < -90.0F) {

					this.rotation.setX(-90.0F);

				} else if (this.rotation.getX() > 90.0F) {

					this.rotation.setX(90.0F);
				}
			}
		}

		this.oldMouseX = this.newMouseX;
		this.oldMouseY = this.newMouseY;
	}

	public void update(final GameObject object) {

		this.newMouseX = Input.getMouseX();
		this.newMouseY = Input.getMouseY();

		final float dx = (float) (this.newMouseX - this.oldMouseX);
		final float dy = (float) (this.newMouseY - this.oldMouseY);

		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {

			this.verticalAngle -= dy * this.mouseSensitivity;
			if (this.verticalAngle < -90.0F) {

				this.verticalAngle = -90.0F;

			} else if (this.verticalAngle > 90.0F) {

				this.verticalAngle = 90.0F;
			}

			this.horizontalAngle += dx * this.mouseSensitivity;
		}
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {

			if (this.distance > 0) {

				this.distance += dy * this.mouseSensitivity / 4;

			} else {

				this.distance = 0.1F;
			}
		}

		final float horizontalDistance = (float) (this.distance * Math.cos(Math.toRadians(this.verticalAngle)));
		final float verticalDistance = (float) (this.distance * Math.sin(Math.toRadians(this.verticalAngle)));

		final float xOffset = (float) (horizontalDistance * Math.sin(Math.toRadians(-this.horizontalAngle)));
		final float zOffset = (float) (horizontalDistance * Math.cos(Math.toRadians(-this.horizontalAngle)));

		this.position.set(object.getPosition().getX() + xOffset, object.getPosition().getY() - verticalDistance, object.getPosition().getZ() + zOffset);

		this.rotation.set(this.verticalAngle, -this.horizontalAngle, 0);

		this.oldMouseX = this.newMouseX;
		this.oldMouseY = this.newMouseY;
	}
}
