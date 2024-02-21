package edu.uw.ischool.peijie36.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.InputStream

// Domain objects
data class Topic(
    val title: String,
    val desc: String,
    val questions: List<Question>
)

data class Question(val text: String, val answer: Int, val answers: List<String>)


// Repository
interface ITopicRepository {
    fun getTopics(fileName: String): List<Topic>
}

class TopicRepository(private val context: Context) : ITopicRepository {
    override fun getTopics(fileName: String): List<Topic> {
        val topics = mutableListOf<Topic>()
        val jsonString = readJSONFromAsset(fileName)
        jsonString?.let {
            try {
                val jsonArray = JSONArray(it)
                for (i in 0 until jsonArray.length()) {
                    val topicObject = jsonArray.getJSONObject(i)
                    val topicTitle = topicObject.getString("title")
                    val topicDesc = topicObject.getString("desc")
                    val topicQuestionsJSON = topicObject.getJSONArray("questions")

                    val questions = mutableListOf<Question>()
                    for (j in 0 until topicQuestionsJSON.length()) {
                        val questionObject = topicQuestionsJSON.getJSONObject(j)
                        val questionText = questionObject.getString("text")
                        val questionAnswer = questionObject.getInt("answer")
                        val questionAnswers = questionObject.getJSONArray("answers")
                        val answers = mutableListOf<String>()
                        for (k in 0 until questionAnswers.length()) {
                            answers.add(questionAnswers.getString(k))
                        }
                        val question = Question(questionText, questionAnswer, answers)
                        questions.add(question)
                    }
                    val topic = Topic(topicTitle, topicDesc, questions)
                    topics.add(topic)
                }
            } catch (e: Exception) {
                Log.e("QuizApp", "Error: ", e)
            }
        }
        return topics
    }

    private fun readJSONFromAsset(fileName: String): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}


class QuizApp : Application() {
    lateinit var topicRepository: ITopicRepository

    override fun onCreate() {
        super.onCreate()
        topicRepository = TopicRepository(applicationContext)
        Log.d("QuizApp", "QuizApp is loading and running")
    }
}