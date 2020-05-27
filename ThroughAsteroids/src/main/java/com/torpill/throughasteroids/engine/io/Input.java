package com.torpill.throughasteroids.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class Input {

	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	private static double mouseX, mouseY;
	private static double scrollX, scrollY;

	private final GLFWKeyCallback keyboard;
	private final GLFWCursorPosCallback mouseMove;
	private final GLFWMouseButtonCallback mouseButtons;
	private final GLFWScrollCallback mouseScroll;

	public Input() {

		this.keyboard = new GLFWKeyCallback() {

			@Override
			public void invoke(final long window, final int key, final int scancode, final int action, final int mods) {

				Input.keys[key] = action != GLFW.GLFW_RELEASE;
			}
		};

		this.mouseMove = new GLFWCursorPosCallback() {

			@Override
			public void invoke(final long window, final double xpos, final double ypos) {

				Input.mouseX = xpos;
				Input.mouseY = ypos;
			}
		};

		this.mouseButtons = new GLFWMouseButtonCallback() {

			@Override
			public void invoke(final long window, final int button, final int action, final int mods) {

				Input.buttons[button] = action != GLFW.GLFW_RELEASE;
			}
		};

		this.mouseScroll = new GLFWScrollCallback() {

			@Override
			public void invoke(final long window, final double xoffset, final double yoffset) {

				Input.scrollX += xoffset;
				Input.scrollY += yoffset;
			}
		};
	}

	public static double getMouseX() {

		return mouseX;
	}

	public static double getMouseY() {

		return mouseY;
	}

	public static double getScrollX() {

		return scrollX;
	}

	public static double getScrollY() {

		return scrollY;
	}

	public static boolean isButtonDown(final int button) {

		return buttons[button];
	}

	public static boolean isKeyDown(final int key) {

		return keys[key];
	}

	public void destroy() {

		this.keyboard.free();
		this.mouseMove.free();
		this.mouseButtons.free();
		this.mouseScroll.free();
	}

	public GLFWKeyCallback getKeyboardCallback() {

		return this.keyboard;
	}

	public GLFWMouseButtonCallback getMouseButtonsCallback() {

		return this.mouseButtons;
	}

	public GLFWCursorPosCallback getMouseMoveCallback() {

		return this.mouseMove;
	}

	public GLFWScrollCallback getMouseScrollCallback() {

		return this.mouseScroll;
	}
}
