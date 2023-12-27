package com.example.elmarombero

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.elmarombero.data.dao.ExerciseDAO
import com.example.elmarombero.data.dao.WorkoutDAO
import com.example.elmarombero.data.dataBase.WorkoutDatabase
import com.example.elmarombero.data.model.ExerciseDataClass
import com.example.elmarombero.data.model.WorkoutDataClass
import com.example.elmarombero.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Declare uma instância do DAO
    private lateinit var workoutDAO: WorkoutDAO
    private lateinit var exerciseDAO: ExerciseDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialize as instâncias do DAO
        val database = WorkoutDatabase.getInstance(this)
        workoutDAO = database.workoutDAO()
        exerciseDAO = database.exerciseDAO()

        val navView: BottomNavigationView = binding.navView

        lifecycleScope.launch {
            val newWorkout = WorkoutDataClass(name = "Meu Treino", description = "Descrição do treino", date = System.currentTimeMillis())
            val newExercise = ExerciseDataClass(name = "Meu Exercício", imageUrl = "url_da_imagem", observations = "Observações do exercício")

            // Inserir o treino e o exercício no banco de dados
            workoutDAO.insert(newWorkout)
            exerciseDAO.insert(newExercise)

            // Puxar os resultados que você inseriu e exibi-los no log
            val treinos = workoutDAO.getAllWorkouts()
            val exercicios = exerciseDAO.getAllExercises()

            for (treino in treinos) {
                Log.d("MainActivity", "Treino: ${treino.name}, Descrição: ${treino.description}, Data: ${treino.date}")
            }

            for (exercicio in exercicios) {
                Log.d("MainActivity", "Exercício: ${exercicio.name}, URL da Imagem: ${exercicio.imageUrl}, Observações: ${exercicio.observations}")
            }
        }

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