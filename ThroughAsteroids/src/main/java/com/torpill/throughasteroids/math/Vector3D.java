package com.torpill.throughasteroids.math;

public class Vector3D {

	private final double vector[];

	public Vector3D() {

		this(0.0, 0.0, 0.0);
	}

	public Vector3D(double x, double y, double z) {

		this.vector = new double[3];
		this.vector[0] = x;
		this.vector[1] = y;
		this.vector[2] = z;
	}
}
