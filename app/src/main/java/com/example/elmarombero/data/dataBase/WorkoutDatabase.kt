package com.example.elmarombero.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elmarombero.data.dao.ExerciseDAO
import com.example.elmarombero.data.dao.WorkoutDAO
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.WorkoutDataClass

@Database(entities = [WorkoutDataClass::class, ExerciseDataClass::class], version = 1)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDAO(): WorkoutDAO
    abstract fun exerciseDAO(): ExerciseDAO

    companion object {
        @Volatile
        private var INSTANCE: WorkoutDatabase? = null

        fun getInstance(context: Context): WorkoutDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutDatabase::class.java,
                    "workout_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}