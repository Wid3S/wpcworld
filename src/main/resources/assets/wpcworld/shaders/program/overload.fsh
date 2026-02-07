#version 150

uniform sampler2D DiffuseSampler;
in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec4 color = texture(DiffuseSampler, texCoord);

    color.rgb *= vec3(1.3, 0.8, 0.8);

    fragColor = color;
}