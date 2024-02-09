package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Question(val question: String, val choices: List<String>, val answer: String)


class Questions : AppCompatActivity() {
    private var currentQuestionIndex: Int = 0
    private var totalCorrectQuestions: Int = 0
    private lateinit var topic: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        if (savedInstanceState != null) {
            // Restore the saved state
            currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex", 0)
            totalCorrectQuestions = savedInstanceState.getInt("totalCorrectQuestions", 0)
            topic = savedInstanceState.getString("topic") ?: ""
        }

        val topic = intent.getStringExtra("topic")
        val topicQuestions = when(topic) {
            "Math" -> QuestionGenerator.generateMathQuestions()
            "Physics" -> QuestionGenerator.generatePhysicsQuestions()
            "Marvel Super Heroes" -> QuestionGenerator.generateMarvelQuestions()
            else -> emptyList()
        }

        val questionText = findViewById<TextView>(R.id.txt_question)
        val choices = findViewById<RadioGroup>(R.id.rg_choices)
        val submitButton = findViewById<Button>(R.id.btn_submit)

        if(currentQuestionIndex < topicQuestions.size) {
            displayQuestion(currentQuestionIndex, topicQuestions, questionText, choices)
        }

        choices.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                submitButton.visibility = Button.VISIBLE
            } else {
                submitButton.visibility = Button.GONE
            }
        }

        submitButton.setOnClickListener {
            val selectedChoice = findViewById<RadioButton>(choices.checkedRadioButtonId).text.toString()
            val lastQuestion = currentQuestionIndex == topicQuestions.size-1
            if(selectedChoice == topicQuestions[currentQuestionIndex].answer) {
                totalCorrectQuestions++
            }
            val intent = Intent(this, Answer::class.java)
            intent.putExtra("chosenAnswer", selectedChoice)
            intent.putExtra("correctAnswer", topicQuestions[currentQuestionIndex].answer)
            intent.putExtra("totalQuestions", topicQuestions.size)
            intent.putExtra("numQuestionsCorrect", totalCorrectQuestions)
            intent.putExtra("lastQuestion", lastQuestion)
            if(!lastQuestion) {
                currentQuestionIndex++
            }
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the index to the bundle
        outState.putInt("currentQuestionIndex", currentQuestionIndex)
        outState.putInt("totalCorrectQuestions", totalCorrectQuestions)
        super.onSaveInstanceState(outState)
    }

    fun displayQuestion(currentQuestionIndex: Int, topicQuestions: List<Question>, questionText: TextView, choices: RadioGroup) {
        // Display the question text
        questionText.text = topicQuestions[currentQuestionIndex].question

        // Clear existing radio buttons
        choices.removeAllViews()

        // Add new radio buttons for choices
        for (choice in topicQuestions[currentQuestionIndex].choices) {
            val radioButton = RadioButton(questionText.context)
            radioButton.text = choice
            choices.addView(radioButton)
        }
    }

}