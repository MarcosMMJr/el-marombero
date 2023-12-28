package com.example.elmarombero.data.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.WorkoutDataClass
import com.example.elmarombero.data.model.WorkoutExerciseJoin

data class WorkoutWithExercises(
    @Embedded val workout: WorkoutDataClass,
    @Relation(
        parentColumn = "id", // Coluna do ID na tabela Workout
        entityColumn = "id", // Coluna do ID na tabela Exercise
        associateBy = Junction(
            value = WorkoutExerciseJoin::class,
            parentColumn = "workoutId", // Coluna na tabela de junção referenciando Workout
            entityColumn = "exerciseId" // Coluna na tabela de junção referenciando Exercise
        )
    )
    val exercises: List<ExerciseDataClass>
)