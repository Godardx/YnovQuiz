package com.example.ynovquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("com.example.ynovquiz", MODE_PRIVATE)

        val userScore = sharedPreferences.getInt("userScore", -1)

        if (userScore > -1){
            textView.text = "Ton dernier score est de $userScore ! Bien joué :D"
        }
    }

    fun onClickBtnPlay(view:View){
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }

}