package com.example.elmarombero.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.ExerciseJoinTable

@Dao
interface ExerciseDAO {
    @Insert
    suspend fun insert(exercise: ExerciseDataClass)

    @Query("SELECT * FROM exercise")
    suspend fun getAllExercises(): List<ExerciseDataClass>


    @Query("SELECT * FROM exercise WHERE id IN (SELECT exerciseId FROM exercise_join_table WHERE workoutId = :workoutId)")
    suspend fun getExercisesByWorkoutId(workoutId: Long): List<ExerciseDataClass>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExerciseIntoWorkout(join: ExerciseJoinTable)

    @Delete
    suspend fun deleteExerciseFromWorkout(join: ExerciseJoinTable)
}