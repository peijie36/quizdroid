package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TopicOverview : AppCompatActivity() {
    private lateinit var topics: List<Topic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        // get list of Topic objects
        topics = (application as QuizApp).topicRepository.getTopics()

        val selectedTopic = intent.getStringExtra("topic")
        // get the Topic domain object equal to the topic clicked
        val topicObject = topics.find { it.title == selectedTopic }

        val topicHeaderView = findViewById<TextView>(R.id.txt_topic)
        val topicDescriptionView = findViewById<TextView>(R.id.txt_topic_description)
        val totalQuestionsView = findViewById<TextView>(R.id.txt_total_questions)
        val beginButton = findViewById<Button>(R.id.btn_begin)

        topicHeaderView.text = selectedTopic
        topicDescriptionView.text = topicObject?.desc
        totalQuestionsView.text = "Total number of questions: ${topicObject?.questions?.size}"

        beginButton.setOnClickListener {
            val intent = Intent(this, Questions::class.java)
            intent.putExtra("topic", selectedTopic)
            intent.putExtra("currentQuestionIndex", 0)
            intent.putExtra("numQuestionsCorrect", 0)
            startActivity(intent)
        }
    }
}

