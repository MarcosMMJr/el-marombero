package com.example.elmarombero.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["workoutId", "exerciseId"],
)
data class WorkoutExerciseJoin(
    val workoutId: Int,
    val exerciseId: Int
)