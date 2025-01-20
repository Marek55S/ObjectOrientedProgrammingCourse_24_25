package org.example.model


fun <K, V> Map<K, V>.randomPosition(): K? {
    return this.keys.randomOrNull()
}

fun Map<Vector2d, *>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val occupiedPositions = this.keys.toSet()
    val allPositions = (0 until mapSize.x).flatMap { x ->
        (0 until mapSize.y).map { y ->
            Vector2d(x, y)
        }
    }
    val freePositions = allPositions.filter { it !in occupiedPositions }
    return freePositions.randomOrNull()
}