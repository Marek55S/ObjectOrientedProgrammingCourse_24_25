package org.example.model

class IncorrectPositionException(position: Vector2d) : Exception("Position $position is not correct")