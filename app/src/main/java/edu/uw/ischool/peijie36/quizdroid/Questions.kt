package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Questions : AppCompatActivity() {
    private lateinit var topicQuestions: List<Question>
    private var totalNumQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val questionsFile = intent.getStringExtra("customQuestions")
        val currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)
        var totalCorrectQuestions = intent.getIntExtra("numQuestionsCorrect", 0)
        val selectedTopic = intent.getStringExtra("topic")

        // get list of Topic objects
        val topics = (application as QuizApp).topicRepository.getTopics(questionsFile!!)


        val topicObject = topics.find { it.title == selectedTopic }
        // get a list of all Questions from selected topic
        topicQuestions = topicObject!!.questions
        totalNumQuestions = topicQuestions.size

        val questionText = findViewById<TextView>(R.id.txt_question)
        val choices = findViewById<RadioGroup>(R.id.rg_choices)
        val submitButton = findViewById<Button>(R.id.btn_submit)

        displayQuestion(currentQuestionIndex, topicObject.questions, questionText, choices)

        // show submit button if a radio button is selected
        choices.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                submitButton.visibility = Button.VISIBLE
            } else {
                submitButton.visibility = Button.GONE
            }
        }

        // handles submit button clicked
        submitButton.setOnClickListener {
            val currQuestion = topicQuestions[currentQuestionIndex]
            // Get the ID of the selected RadioButton
            val selectedRadioButtonId = choices.checkedRadioButtonId
            // Find the index of the selected RadioButton within the RadioGroup
            val selectedChoice = choices.indexOfChild(findViewById(selectedRadioButtonId))
            val lastQuestion = currentQuestionIndex == totalNumQuestions-1
            if(selectedChoice == currQuestion.answer-1) {
                totalCorrectQuestions++
            }
            val intent = Intent(this, Answer::class.java)
            intent.putExtra("chosenAnswer", currQuestion.answers[selectedChoice])
            intent.putExtra("correctAnswer", currQuestion.answers[currQuestion.answer-1])
            intent.putExtra("numQuestionsCorrect", totalCorrectQuestions)
            intent.putExtra("lastQuestion", lastQuestion)
            intent.putExtra("topic", selectedTopic)
            intent.putExtra("currentQuestionIndex", currentQuestionIndex)
            intent.putExtra("totalQuestions", totalNumQuestions)
            intent.putExtra("customQuestions", questionsFile)

            startActivity(intent)
        }
    }

    private fun displayQuestion(currentQuestionIndex: Int, topicQuestions: List<Question>, questionText: TextView, choices: RadioGroup) {
        // Display the question text
        questionText.text = topicQuestions[currentQuestionIndex].text

        // Clear existing radio buttons
        choices.removeAllViews()

        // Add new radio buttons for choices
        for (choice in topicQuestions[currentQuestionIndex].answers) {
            val radioButton = RadioButton(questionText.context)
            radioButton.text = choice
            choices.addView(radioButton)
        }
    }

}