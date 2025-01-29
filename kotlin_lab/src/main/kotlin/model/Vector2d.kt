package org.example.model

data class Vector2d(val x: Int, val y: Int) {

    operator fun compareTo(other: Vector2d): Int {
        return if (x<=other.x && y<=other.y) -1 else if (x>=other.x && y>=other.y) 1 else 0
    }

    operator fun plus(other: Vector2d): Vector2d = Vector2d(this.x + other.x, this.y + other.y)

    operator fun minus(other: Vector2d): Vector2d = Vector2d(this.x - other.x, this.y - other.y)

    fun upperRight(other: Vector2d): Vector2d = Vector2d(this.x.coerceAtLeast(other.x), this.y.coerceAtLeast(other.y))

    fun lowerLeft(other: Vector2d): Vector2d = Vector2d(this.x.coerceAtMost(other.x), this.y.coerceAtMost(other.y))

    fun opposite(): Vector2d = Vector2d(-this.x, -this.y)

    fun MapDirection.toUnitVector(): Vector2d {
        return when (this) {
            MapDirection.NORTH -> Vector2d(0, 1)
            MapDirection.EAST -> Vector2d(1, 0)
            MapDirection.SOUTH -> Vector2d(0, -1)
            MapDirection.WEST -> Vector2d(-1, 0)
        }
    }

}