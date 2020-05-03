package sharkbound.forge.shared.util.objects

import sharkbound.forge.shared.util.vec3D

object Vectors {
    val ZERO = vec3D(0)
    val ONE = vec3D(1)
    val UP = vec3D(y = 1)
    val DOWN = vec3D(y = -1)
    val LEFT = vec3D(x = -1)
    val RIGHT = vec3D(x = 1)
    val FORWARD = vec3D(z = 1)
    val BACK = vec3D(z = -1)
}