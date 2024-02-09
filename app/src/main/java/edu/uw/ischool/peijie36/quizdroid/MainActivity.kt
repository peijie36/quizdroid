package edu.uw.ischool.peijie36.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val topics = listOf("Math", "Physics", "Marvel Super Heroes")

    private val topicDescriptions = mapOf(
        "Math" to "An area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes.",
        "Physics" to "the natural science of matter, involving the study of matter, its fundamental constituents, its motion and behavior through space and time, and the related entities of energy and force.",
        "Marvel Super Heroes" to "fictional characters created by Marvel Comics, known for their heroic exploits, superhuman abilities, and memorable stories.",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topicListView = findViewById<ListView>(R.id.list_view_topics)

        val adapter = ArrayAdapter(this, R.layout.list_item, R.id.txt_item, topics)
        topicListView.adapter = adapter

        topicListView.setOnItemClickListener { _, _, position, _ ->
            val topic: String = topics[position]
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("topic", topic)
            intent.putExtra("topicDescription", topicDescriptions[topic])
            startActivity(intent)
        }
    }
}