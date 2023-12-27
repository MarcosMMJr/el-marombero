package com.example.elmarombero.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.elmarombero.data.model.WorkoutExerciseJoin
import com.example.elmarombero.data.relationship.ExerciseWithWorkouts
import com.example.elmarombero.data.relationship.WorkoutWithExercises

@Dao
interface WorkoutExerciseJoinDAO {

    @Insert
    fun insert(workoutExerciseJoin: WorkoutExerciseJoin)

    @Transaction
    @Query("SELECT * FROM workout WHERE id = :workoutId")
    fun getExercisesForWorkout(workoutId: Int): List<WorkoutWithExercises>

    @Transaction
    @Query("SELECT * FROM exercise WHERE id = :exerciseId")
    fun getWorkoutsForExercise(exerciseId: Int): List<ExerciseWithWorkouts>
}

