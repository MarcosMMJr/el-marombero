package com.example.elmarombero.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.WorkoutExerciseJoin

@Dao
interface ExerciseDAO {
    @Insert
    suspend fun insert(exercise: ExerciseDataClass): Long

    @Update
    fun updateExercise(exercise: ExerciseDataClass)

    @Delete
    fun deleteExercise(exercise: ExerciseDataClass)

    @Query("SELECT * FROM exercise")
    suspend fun getAllExercises(): List<ExerciseDataClass>

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExerciseById(id: Int): ExerciseDataClass?

//    @Query("SELECT * FROM exercise WHERE id IN (SELECT * FROM exercise WHERE workoutId = :workoutId)")
//    suspend fun getExercisesForWorkout(workoutId: Long): List<ExerciseDataClass>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExerciseIntoWorkout(join: WorkoutExerciseJoin)

    @Delete
    suspend fun deleteExerciseFromWorkout(join: WorkoutExerciseJoin)
}