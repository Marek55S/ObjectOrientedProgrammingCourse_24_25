package org.example.model

import java.util.*

interface WorldMap {
    @Throws(IncorrectPositionException::class)
    fun place(animal: Animal)

    fun move(animal: Animal, direction: MoveDirection)

    fun canMoveTo(position: Vector2d): Boolean

    fun isOccupied(position: Vector2d): Boolean

    fun objectAt(position: Vector2d): Animal?

    val elements: Collection<Animal>

    val orderedAnimals: Collection<Animal>

    val id: UUID

    val currentBounds: Boolean
}