package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Answer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val selectedAns = intent.getStringExtra("chosenAnswer")
        val correctAns = intent.getStringExtra("correctAnswer")
        val totalCorrect = intent.getIntExtra("numQuestionsCorrect", 0)
        val lastQuestion = intent.getBooleanExtra("lastQuestion", false)
        val selectedTopic = intent.getStringExtra("topic")
        val currIndex = intent.getIntExtra("currentQuestionIndex", 0)
        val totalNumQuestions = intent.getIntExtra("totalQuestions", 0)
        val questionsFile = intent.getStringExtra("customQuestions")


        val selectedAnswerText = findViewById<TextView>(R.id.txt_chosen_answer)
        val correctAnswerText = findViewById<TextView>(R.id.txt_correct_answer)
        val scoreReport = findViewById<TextView>(R.id.txt_report)
        val nextButton = findViewById<Button>(R.id.btn_next)

        selectedAnswerText.text = "Your answer: ${selectedAns}"
        correctAnswerText.text = "The correct answer is: ${correctAns}"
        scoreReport.text = "You have ${totalCorrect} out of ${totalNumQuestions} correct"
        if(lastQuestion) {
            nextButton.text = "Finish"
            nextButton.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            nextButton.setOnClickListener{
                val intent = Intent(this, Questions::class.java)
                intent.putExtra("topic", selectedTopic)
                intent.putExtra("currentQuestionIndex", currIndex+1)
                intent.putExtra("numQuestionsCorrect", totalCorrect)
                intent.putExtra("customQuestions", questionsFile)
                startActivity(intent)
            }
        }


    }
}