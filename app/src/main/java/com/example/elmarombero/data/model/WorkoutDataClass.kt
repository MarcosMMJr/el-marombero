package com.example.elmarombero.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout")
data class WorkoutDataClass(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val date: String //TODO: Usar long para armazenar o timestamp
)
