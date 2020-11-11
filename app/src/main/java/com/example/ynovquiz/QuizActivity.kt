package com.example.ynovquiz

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*
import kotlin.collections.ArrayList


var quizs = ArrayList<Quiz>()
    var numberOfGoodAnswers: Int = 0
    var currentQuizIndex: Int = 0


class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizs.add(Quiz("Quelle est la capitale de la France ?", "Paris", "Marseille", "Alger",1 ))
        quizs.add(Quiz("Quelle est la capitale de l'Espagne ?", "Paris", "Madrid", "Alger",2 ))
        quizs.add(Quiz("Quelle est la capitale de l'Allemagne ?", "Paris", "Marseille", "Berlin",3 ))
        quizs.add(Quiz("Quelle est la capitale des USA ?", "Paris", "Marseille", "Washington",3 ))
        quizs.add(Quiz("Quelle est la capitale du Canada ?", "Ottawa", "Marseille", "Alger",1 ))

        quizs.shuffle()

        showQuestion(quizs[currentQuizIndex])

    }

    private fun showQuestion(quiz: Quiz){
        txtQuestion.text = quiz.question
        answer1.text = quiz.answer1
        answer2.text = quiz.answer2
        answer3.text = quiz.answer3
    }

    fun handleAnswer(answerID: Int){
        val quiz = quizs[currentQuizIndex]

        if (quiz.isCorrect(answerID)){
            numberOfGoodAnswers++
            Toast.makeText(this, "+1", Toast.LENGTH_SHORT).show()
    } else {
            Toast.makeText(this, "+0", Toast.LENGTH_SHORT).show()
    }

        currentQuizIndex++

        if(currentQuizIndex >= quizs.size){

            val sharedPreferences = getSharedPreferences("com.example.ynovquiz", MODE_PRIVATE)
            sharedPreferences.edit().putInt("userScore", numberOfGoodAnswers).apply()

            var alert = AlertDialog.Builder(this)
            alert.setTitle("Partie terminée !")
            alert.setMessage("Tu as obtenu : " + numberOfGoodAnswers + " de bonne(s) réponse(s)")
            alert.setPositiveButton("Ok") {dialogInterface: DialogInterface?, i:Int ->
                finish()
            }
                alert.show()
        } else {
            showQuestion(quizs[currentQuizIndex])
        }
    }

    fun onClickAnswerOne(view: View){
        handleAnswer(1)
    }

    fun onClickAnswerTwo(view: View){
        handleAnswer(2)
    }

    fun onClickAnswerThree(view: View){
        handleAnswer(3)
    }


}