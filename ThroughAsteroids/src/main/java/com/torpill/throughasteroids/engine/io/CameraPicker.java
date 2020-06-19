package com.torpill.throughasteroids.engine.io;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.torpill.throughasteroids.App;
import com.torpill.throughasteroids.App.GameState;
import com.torpill.throughasteroids.engine.maths.Intersectionf;
import com.torpill.throughasteroids.engine.maths.Matrix4f;
import com.torpill.throughasteroids.engine.maths.MoreMath;
import com.torpill.throughasteroids.engine.maths.Vector2f;
import com.torpill.throughasteroids.engine.maths.Vector3f;
import com.torpill.throughasteroids.engine.maths.Vector4f;
import com.torpill.throughasteroids.objects.Camera;
import com.torpill.throughasteroids.objects.GameObject;
import com.torpill.throughasteroids.objects.blocks.Blocks;
import com.torpill.throughasteroids.objects.guis.GUIObject;

public class CameraPicker {

	private final Vector3f max = new Vector3f(0, 0, 0);
	private final Vector3f min = new Vector3f(0, 0, 0);
	private final Vector2f nearFar = new Vector2f(0, 0);

	protected final Vector3f currentRay = new Vector3f(0, 0, 0);
	protected Matrix4f view;

	private final Camera camera;

	public CameraPicker(final Camera camera) {

		this.camera = camera;
		this.view = Matrix4f.view(camera.getPosition(), camera.getRotation());
	}

	protected void calculateView() {

		this.view = Matrix4f.view(this.camera.getPosition(), this.camera.getRotation());
	}

	public Vector3f getCurrentRay() {

		return this.currentRay;
	}

	protected Vector2f getNormalizedDeviceCoords(final float mouseX, final float mouseY) {

		final float x = 2F * mouseX / App.instance.window.getWidth() - 1F;
		final float y = 1F - 2F * mouseY / App.instance.window.getHeight();

		return new Vector2f(x, y);
	}

	public void selectGameObject(final float closestDistance, final ArrayList<? extends GameObject> objects) {

		GameObject selected = null;
		float currentClosest = closestDistance;

		final float mouseX = (float) Input.getMouseX();
		final float mouseY = (float) Input.getMouseY();

		final Vector2f mouse = this.getNormalizedDeviceCoords(mouseX, mouseY);

		App.LOGGER.debug("M({} {})", mouse.getX(), mouse.getY());

		for (final GameObject object : objects) {

			object.setSelected(false);

			if (object instanceof GUIObject) {

				final float widthRatio = (float) App.instance.window.getHeight() / (float) App.instance.window.getWidth();

				final GUIObject guiobject = (GUIObject) object;
				this.min.set(guiobject.getPosOnScreen().getX() * widthRatio, guiobject.getPosOnScreen().getY(), 0F);
				this.max.set(guiobject.getPosOnScreen().getX() * widthRatio, guiobject.getPosOnScreen().getY(), 0F);

				this.min.set(Vector3f.subtract(this.min, new Vector3f(guiobject.getSizeOnScreen().getX() * widthRatio, guiobject.getSizeOnScreen().getY(), 0F)));
				this.max.set(Vector3f.add(this.max, new Vector3f(guiobject.getSizeOnScreen().getX() * widthRatio, guiobject.getSizeOnScreen().getY(), 0F)));

				App.LOGGER.debug("X : {} / {} / {}", this.min.getX(), guiobject.getPosOnScreen().getX(), this.max.getX());

				if (mouse.getX() > this.min.getX() && mouse.getX() < this.max.getX() && mouse.getY() > this.min.getY() && mouse.getY() < this.max.getY()) {

					App.LOGGER.debug(guiobject.getName());
					selected = guiobject;
					break;
				}

			} else {

				this.min.set(object.getPosition());
				this.max.set(object.getPosition());
				this.min.set(Vector3f.subtract(this.min, new Vector3f(object.getScale().getX() / 2.0F, object.getScale().getY() / 2.0F, object.getScale().getZ() / 2.0F)));
				this.max.set(Vector3f.add(this.max, new Vector3f(object.getScale().getX() / 2.0F, object.getScale().getY() / 2.0F, object.getScale().getZ() / 2.0F)));
				if (Intersectionf.intersectRayAAB(this.camera.getPosition(), this.currentRay, this.min, this.max, this.nearFar) && this.nearFar.getX() < currentClosest) {

					currentClosest = this.nearFar.getX();
					selected = object;
				}
			}
		}

		if (selected != null) {

			selected.setSelected(true);

			if (App.instance.state == GameState.BUILD) {

				currentClosest = closestDistance;
				int face = 0;
				final Vector3f intersection = new Vector3f(0, 0, 0);

				for (int i = 0; i < 6; i++) {

					final Matrix4f faceMatrix = selected.getHitBoxFace(i);
					final Vector3f vector0 = new Vector3f(faceMatrix.get(0, 0), faceMatrix.get(0, 1), faceMatrix.get(0, 2));
					final Vector3f vector1 = new Vector3f(faceMatrix.get(1, 0), faceMatrix.get(1, 1), faceMatrix.get(1, 2));
					final Vector3f vector3 = new Vector3f(faceMatrix.get(3, 0), faceMatrix.get(3, 1), faceMatrix.get(3, 2));

					final Vector4f plane = MoreMath.cartesianPlaneEquation(vector0, vector1, vector3);
					if (plane != null) {

						App.LOGGER.trace("P{}: {}x + {}y + {}z + {}", i, plane.getX(), plane.getY(), plane.getZ(), plane.getW());
						final Vector3f m = MoreMath.intersectRayAab(this.camera.getPosition(), this.currentRay, plane);
						if (m != null) {

							if (MoreMath.isInPlanePart(plane, faceMatrix, m)) {

								final float distance = Vector3f.length(Vector3f.subtract(m, this.camera.getPosition()));
								if (distance < currentClosest) {

									currentClosest = distance;
									intersection.set(m);
									face = i;
								}
							}
						}
					}
				}

				App.LOGGER.trace("F{}: M({} {} {})", face, intersection.getX(), intersection.getY(), intersection.getZ());
				if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {

					final Vector3f pos = new Vector3f(selected.getPosition());
					switch (face) {
					case 0:
						pos.setY(pos.getY() + 1.0F);
						break;

					case 1:
						pos.setY(pos.getY() - 1.0F);
						break;

					case 2:
						pos.setZ(pos.getZ() + 1.0F);
						break;

					case 3:
						pos.setX(pos.getX() + 1.0F);
						break;

					case 4:
						pos.setZ(pos.getZ() - 1.0F);
						break;

					case 5:
						pos.setX(pos.getX() - 1.0F);
						break;

					default:
						return;
					}

					App.instance.getWorld().setBlock(Blocks.WALL_ORE, pos);
				}

			} else if (App.instance.state == GameState.PAUSE) {

				if (selected instanceof GUIObject) {

					final GUIObject guiobject = (GUIObject) selected;
					if (!Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {

						guiobject.onMouseHover();

					} else {

						guiobject.onMouseClicked();
					}
				}
			}
		}
	}

	public void update(final ArrayList<? extends GameObject> arrayList) {

		this.calculateView();
		Matrix4f.positiveZ(this.view, this.currentRay);
		Vector3f.negate(this.currentRay);

		this.selectGameObject(20.0F, arrayList);
	}
}
