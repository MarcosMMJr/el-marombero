package com.example.elmarombero.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.elmarombero.data.model.WorkoutDataClass

@Dao
interface WorkoutDAO {

    @Insert
    suspend fun insert(workout: WorkoutDataClass)

    @Query("SELECT * FROM workout")
    suspend fun getAllWorkouts(): List<WorkoutDataClass>
    }


