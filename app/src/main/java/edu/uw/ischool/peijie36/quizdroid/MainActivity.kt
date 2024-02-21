package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var topics: List<Topic>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.action_tool_bar))
        val questionsFile = "questions.json"

        // get list of Topic objects
        topics = (application as QuizApp).topicRepository.getTopics(questionsFile)
        // get list of Topic titles and short descriptions
        val topicHeaders = topics.map { it.title to it.desc }

        val topicListView = findViewById<ListView>(R.id.list_view_topics)
        val adapter = TopicAdapter(this, R.layout.list_item, topicHeaders)
        topicListView.adapter = adapter

        topicListView.setOnItemClickListener { _, _, position, _ ->
            val topic: String = topics[position].title
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("topic", topic)
            intent.putExtra("customQuestions", questionsFile)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_preferences -> {
                startActivity(Intent(this, Preferences::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}