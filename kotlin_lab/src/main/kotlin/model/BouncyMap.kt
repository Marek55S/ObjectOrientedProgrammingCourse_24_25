package org.example.model

import kotlin.random.Random

class BouncyMap( width: Int,height: Int,): WorldMap {
    private val animals = mutableMapOf<Vector2d,Animal>()
    val upperRightBound = Vector2d(width-1,height-1)
    val lowerLeftBound = Vector2d(0,0)
    var allPositions = mutableListOf<Vector2d>()

    init{
        getAllPositions()
    }

    private fun getAllPositions(){
        for (x in 0..upperRightBound.x){
            for (y in 0..upperRightBound.y){
                allPositions.add(Vector2d(x,y))
            }
        }
    }

    override val elements: Collection<Animal>
        get() = animals.values

    override fun canMoveTo(position: Vector2d): Boolean {
        return lowerLeftBound <= position && position <= upperRightBound
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animals.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Animal? {
        return animals[position]
    }

    @Throws(IncorrectPositionException::class)
    override fun place(animal: Animal) {
        val position = animal.getPosition()
        if (!canMoveTo(position)) {
            throw IncorrectPositionException(position)
        }
        if (isOccupied(position)) {
            handleCollision(animal)
        } else {
            animals[position] = animal
        }
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val currentPosition = animal.getPosition()
        animal.move(direction, this)
        val newPosition = animal.getPosition()
        if (currentPosition != newPosition) {
            animals.remove(currentPosition)
            if (isOccupied(newPosition)) {
                handleCollision(animal)
            } else {
                animals[newPosition] = animal
            }
        }
    }

    private fun handleCollision(animal: Animal) {
        val possiblePositions = allPositions.filter { !isOccupied(it) }
        if (possiblePositions.isNotEmpty()) {
            val newPosition = possiblePositions.random()
            animals.remove(animal.getPosition())
            animals[newPosition] = animal
        } else {
            val randomAnimalPosition = animals.keys.random()
            animals.remove(randomAnimalPosition)
            animals[animal.getPosition()] = animal
        }
    }


}