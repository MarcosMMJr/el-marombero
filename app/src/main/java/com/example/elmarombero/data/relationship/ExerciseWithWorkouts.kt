package com.example.elmarombero.data.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.WorkoutDataClass
import com.example.elmarombero.data.model.WorkoutExerciseJoin

data class ExerciseWithWorkouts(
    @Embedded val exercise: ExerciseDataClass,
    @Relation(
        parentColumn = "id", // Coluna do ID na tabela Exercise
        entityColumn = "id", // Coluna do ID na tabela Workout
        associateBy = Junction(
            value = WorkoutExerciseJoin::class,
            parentColumn = "exerciseId", // Coluna na tabela de junção referenciando Exercise
            entityColumn = "workoutId" // Coluna na tabela de junção referenciando Workout
        )
    )
    val workouts: List<WorkoutDataClass>
)