#version 460

in vec3 position;

out vec2 passTextureCoord;

uniform mat4 model;
uniform mat4 projection;

void main(void) {
	gl_Position = projection * model * vec4(position, 1.0);
	passTextureCoord = vec2((position.x + 1.0) / 2.0, 1 - (position.y + 1.0) / 2.0);
}
