package com.example.elmarombero.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise",
    foreignKeys = [ForeignKey(
        entity = WorkoutDataClass::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("workoutId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExerciseDataClass(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val workoutId: Int,
    val name: String,
    val imageUrl: String,
    val observations: String
)