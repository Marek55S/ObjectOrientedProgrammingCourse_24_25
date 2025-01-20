package org.example.model

enum class MapDirection(val unitVector: Vector2d) {
    NORTH(Vector2d(0, 1)),
    EAST(Vector2d(1, 0)),
    SOUTH(Vector2d(0, -1)),
    WEST(Vector2d(-1, 0));



    override fun toString(): String {
        return when (this) {
            NORTH -> "N"
            EAST -> "E"
            SOUTH -> "S"
            WEST -> "W"
        }
    }

    fun next(): MapDirection {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }

    fun previous(): MapDirection {
        return when (this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }
    }
}