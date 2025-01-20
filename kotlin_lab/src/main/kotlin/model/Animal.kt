package org.example.model

class Animal(private var position: Vector2d = Vector2d(2, 2),private var orientation: MapDirection = MapDirection.NORTH) {

    fun getPosition(): Vector2d = position

    fun getOrientation(): MapDirection = orientation

    override fun toString(): String = orientation.toString()

    fun isAt(position: Vector2d): Boolean = this.position == position

    fun move(direction: MoveDirection,validator: WorldMap) {
        when (direction) {
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.FORWARD -> {
                val newPosition = position + orientation.unitVector
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
            MoveDirection.BACKWARD -> {
                val newPosition = position - orientation.unitVector
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
        }
    }
}