package com.example.elmarombero.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "exercise_join_table",
    primaryKeys = ["workoutId", "exerciseId"],
    foreignKeys = [
        ForeignKey(entity = WorkoutDataClass::class, parentColumns = ["id"], childColumns = ["workoutId"]),
        ForeignKey(entity = ExerciseDataClass::class, parentColumns = ["id"], childColumns = ["exerciseId"])
    ])
data class ExerciseJoinTable(
    val workoutId: Long,
    val exerciseId: Long
)