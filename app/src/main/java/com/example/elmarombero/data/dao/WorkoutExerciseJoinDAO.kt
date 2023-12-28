package com.example.elmarombero.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.WorkoutDataClass
import com.example.elmarombero.data.model.WorkoutExerciseJoin
import com.example.elmarombero.data.relationship.ExerciseWithWorkouts
import com.example.elmarombero.data.relationship.WorkoutWithExercises

@Dao
interface WorkoutExerciseJoinDAO {

    @Insert
    fun insert(workoutExerciseJoin: WorkoutExerciseJoin)

    @Delete
    fun deleteJoin(workoutExerciseJoin: WorkoutExerciseJoin)

    @Query("SELECT * FROM exercise INNER JOIN workoutExerciseJoin ON exercise.id = workoutExerciseJoin.exerciseId WHERE workoutExerciseJoin.workoutId = :workoutId")
    fun getExercisesForWorkout(workoutId: Int): List<ExerciseDataClass>

    @Query("SELECT * FROM workout INNER JOIN workoutExerciseJoin ON workout.id = workoutExerciseJoin.workoutId WHERE workoutExerciseJoin.exerciseId = :exerciseId")
    fun getWorkoutsForExercise(exerciseId: Int): List<WorkoutDataClass>
}

