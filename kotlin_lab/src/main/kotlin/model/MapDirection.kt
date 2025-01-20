package org.example.model

enum class MapDirection() {
    NORTH,
    EAST,
    SOUTH,
    WEST;



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