package com.example.elmarombero

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.elmarombero.data.dao.ExerciseDAO
import com.example.elmarombero.data.dao.WorkoutDAO
import com.example.elmarombero.data.dao.WorkoutExerciseJoinDAO
import com.example.elmarombero.data.dataBase.WorkoutDatabase
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.WorkoutDataClass
import com.example.elmarombero.data.model.WorkoutExerciseJoin
import com.example.elmarombero.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: WorkoutDatabase
    private lateinit var workoutDao: WorkoutDAO
    private lateinit var exerciseDao: ExerciseDAO
    private lateinit var workoutExerciseJoinDao: WorkoutExerciseJoinDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            WorkoutDatabase::class.java,
            "workout_database"
        )
//            .fallbackToDestructiveMigration()
            .build()

        workoutDao = database.workoutDAO()
        exerciseDao = database.exerciseDAO()
        workoutExerciseJoinDao = database.workoutExerciseJoinDAO()

        // Operações de banco de dados
        GlobalScope.launch {
            val workoutId = workoutDao.insert(
                WorkoutDataClass(
                    id = 1,
                    name = "Treino A",
                    description = "Descrição do Treino A",
                    date = "2023-12-27"
                )
            )
            val exerciseId1 = exerciseDao.insert(
                ExerciseDataClass(
                    id = 1,
                    workoutId = 1,
                    name = "Exercício 1",
                    imageUrl = "http://example.com/image1.jpg",
                    observations = "Observações do Exercício 1"
                )
            )
            val exerciseId2 = exerciseDao.insert(
                ExerciseDataClass(
                    id = 2,
                    workoutId = 1,
                    name = "Exercício 2",
                    imageUrl = "http://example.com/image2.jpg",
                    observations = "Observações do Exercício 2"
                )
            )

            // Associar exercício ao treino
            workoutExerciseJoinDao.insert(
                WorkoutExerciseJoin(
                    workoutId = workoutId.toInt(),
                    exerciseId = exerciseId1.toInt()
                )
            )
            workoutExerciseJoinDao.insert(
                WorkoutExerciseJoin(
                    workoutId = workoutId.toInt(),
                    exerciseId = exerciseId2.toInt()
                )
            )

            // Consultar e exibir os resultados
            val workoutWithExercises =
                workoutExerciseJoinDao.getExercisesForWorkout(workoutId.toInt())
            val workouts = workoutDao.getAllWorkouts()
            val exercises = exerciseDao.getAllExercises()

            workoutWithExercises.forEach {
                Log.d("MainActivity", "Exercise in Workout: ${it.workout}, ${it.exercises}")
            }

            workouts.forEach {
                Log.d("MainActivity", "Exercise: ${it.name}, ${it.description}, ${it.date}")
            }

            exercises.forEach {
                Log.d("MainActivity", "Exercise: ${it.name}, ${it.imageUrl}, ${it.observations}")
            }
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}