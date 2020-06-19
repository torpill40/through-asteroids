#version 460 core

in vec3 position;
in vec3 color;
in vec2 textureCoord;

out vec3 passColor;
out vec2 passTextureCoord;
out float visibility;
out int outSelected;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
uniform int selected;

const float density = 0.025;
const float gradient = 5.0;

void main() {
	vec4 worldPosition = model * vec4(position, 1.0);
	vec4 positionRelativeToCam = view * worldPosition;
	gl_Position = projection * positionRelativeToCam;

	passColor = color;
	passTextureCoord = textureCoord;
	outSelected = selected;

	float distance = length(positionRelativeToCam.xyz);
	visibility = exp(-pow((distance * density), gradient));
	visibility = clamp(visibility, 0.0, 1.0);
}
