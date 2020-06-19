package com.torpill.throughasteroids.objects.guis;

public class GUIObjects {

	public static final GUIButton CLASSIC_TOP_BUTTON = new GUIButton(0, 0, "CLASSIC_TOP");
	public static final GUIButton CLASSIC_BUTTON = new GUIButton(1, 0, "CLASSIC");
	public static final GUIButton CLASSIC_BOTTOM_BUTTON = new GUIButton(2, 0, "CLASSIC_BOTTOM");

	public static final GUIButton GREY_TOP_BUTTON = new GUIButton(0, 1, "GREY_TOP");
	public static final GUIButton GREY_BUTTON = new GUIButton(1, 1, "GREY");
	public static final GUIButton GREY_BOTTOM_BUTTON = new GUIButton(2, 1, "GREY_BOTTOM");

	public static void create() {

		create(CLASSIC_TOP_BUTTON);
		create(CLASSIC_BUTTON);
		create(CLASSIC_BOTTOM_BUTTON);

		create(GREY_TOP_BUTTON);
		create(GREY_BUTTON);
		create(GREY_BOTTOM_BUTTON);
	}

	public static void create(final GUIObject object) {

		object.getMesh().create();
	}

	public static void destroy() {

		destroy(CLASSIC_TOP_BUTTON);
		destroy(CLASSIC_BUTTON);
		destroy(CLASSIC_BOTTOM_BUTTON);

		destroy(GREY_TOP_BUTTON);
		destroy(GREY_BUTTON);
		destroy(GREY_BOTTOM_BUTTON);
	}

	public static void destroy(final GUIObject object) {

		object.getMesh().destroy();
	}
}
