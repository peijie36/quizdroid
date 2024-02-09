package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TopicOverview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val topic = intent.getStringExtra("topic")
        val topicDescription = intent.getStringExtra("topicDescription")
        val topicQuestions = when(topic) {
            "Math" -> QuestionGenerator.generateMathQuestions()
            "Physics" -> QuestionGenerator.generatePhysicsQuestions()
            "Marvel Super Heroes" -> QuestionGenerator.generateMarvelQuestions()
            else -> emptyList()
        }

        val topicHeaderView = findViewById<TextView>(R.id.txt_topic)
        val topicDescriptionView = findViewById<TextView>(R.id.txt_topic_description)
        val totalQuestionsView = findViewById<TextView>(R.id.txt_total_questions)
        val beginButton = findViewById<Button>(R.id.btn_begin)

        topicHeaderView.text = topic
        topicDescriptionView.text = topicDescription
        totalQuestionsView.text = "Total number of questions: ${topicQuestions.size}"

        beginButton.setOnClickListener {
            val intent = Intent(this, Questions::class.java)
            intent.putExtra("topic", topic)
            startActivity(intent)
        }
    }
}

