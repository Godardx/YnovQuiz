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
        quizs.add(Quiz("De quelle ville française le cannelé est-il une spécialité ?", "Toulouse", "Marseille", "Bordeaux",3 ))
        quizs.add(Quiz("Que fête-t-on le 1er mai ?", "Le travail", "Le printemps", "Les mamans",1 ))
        quizs.add(Quiz("Quel arbre produit la noix de pécan ?", "Le macadamia", "Le noisetier", "Le pacanier",3 ))
        quizs.add(Quiz("Quel est le nom du principal indice boursier de la place de Paris ?", "Le Dax", "Le CAC 40", "Le Footsie",2 ))
        quizs.add(Quiz("Quelle est le meilleur smartphone ?", "Apple", "Samsung", "OnePlus", 1))
        quizs.add(Quiz("Quelle note méritons nous ?", "20", "18", "16", 1))
        quizs.add(Quiz("Quel groupe a interprété la chanson « Hotel California » ?", "Genesis", "Depeche Mode", "Eagle", 3))
        quizs.add(Quiz("Quel légume entre dans la composition du tzatziki", "Le concombre", "Le cornichon", "La câpre", 1))
        quizs.add(Quiz("Dans quel pays est né le tango ?", "Le Brésil", "L’Argentine", "Cuba", 2))


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
            alert.setMessage("Tu as obtenu : " + numberOfGoodAnswers + " de bonne(s) réponse(s) sur 10 !")
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