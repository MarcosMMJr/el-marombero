package com.example.elmarombero.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.elmarombero.data.model.WorkoutDataClass

@Dao
interface WorkoutDAO {

    @Insert
    suspend fun insert(workout: WorkoutDataClass): Long

    @Update
    fun updateWorkout(workout: WorkoutDataClass)

    @Delete
    fun deleteWorkout(workout: WorkoutDataClass)

    @Query("SELECT * FROM workout")
    suspend fun getAllWorkouts(): List<WorkoutDataClass>

    @Query("SELECT * FROM workout WHERE id = :id")
    fun getWorkoutById(id: Int): WorkoutDataClass?
    }


