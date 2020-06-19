#version 330 core

in vec3 passColor;
in vec2 passTextureCoord;
in float visibility;
flat in int outSelected;

out vec4 outColor;

uniform sampler2D tex;
uniform vec3 skycolor;

void main() {
	outColor = texture(tex, passTextureCoord);

	if (outSelected > 0) {

		outColor = vec4(outColor.x * 0.75, outColor.y * 0.75, outColor.z * 0.75, 1);

	} else {

		outColor = mix(vec4(skycolor, 1.0), outColor, visibility);
	}
}
