package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var topics: List<Topic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get list of Topic objects
        topics = (application as QuizApp).topicRepository.getTopics()
        // get list of Topic titles and short descriptions
        val topicHeaders = topics.map { it.title to it.shortDescription }

        val topicListView = findViewById<ListView>(R.id.list_view_topics)
        val adapter = TopicAdapter(this, R.layout.list_item, topicHeaders)
        topicListView.adapter = adapter

        topicListView.setOnItemClickListener { _, _, position, _ ->
            val topic: String = topics[position].title
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("topic", topic)
            startActivity(intent)
        }
    }
}