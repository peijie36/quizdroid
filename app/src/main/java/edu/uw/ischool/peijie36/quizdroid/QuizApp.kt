package edu.uw.ischool.peijie36.quizdroid
import android.app.Application
import android.util.Log

// Domain objects
data class Topic(val title: String, val shortDescription: String, val longDescription: String, val questions: List<Question>)

data class Question(val question: String, val choices: List<String>, val correctAnswer: Int)


// Repository
interface ITopicRepository {
    fun getTopics() : List<Topic>
}

class TopicRepostiory : ITopicRepository {

    override fun getTopics(): List<Topic> {
        return listOf(
            Topic("Math",
                "study of quality, structure, space, and change",
                "An area of knowledge that includes the topics of numbers, formulas and related structures, shapes and the spaces in which they are contained, and quantities and their changes.",
                listOf(
                    Question(
                        "What is the value of the golden ratio (φ)?",
                        listOf("1.618", "3.141", "2.718", "0.618"),
                        0
                    ),
                    Question(
                        "How many sides does a octagon have?",
                        listOf("5", "6", "7", "8"),
                        3
                    ),
                    Question(
                        "What is the area of a square with side length 6 units?",
                        listOf("24", "30", "36", "42"),
                        2
                    )
                )
            ),
            Topic("Physics",
                "study of nature, focusing on the physical plane of motion, force, and energy",
                "The natural science of matter, involving the study of matter, its fundamental constituents, its motion and behavior through space and time, and the related entities of energy and force.",
                listOf(
                    Question(
                        "What is the force that pulls objects towards the center of the Earth?",
                        listOf("Gravity", "Magnetism", "Friction", "Tension"),
                        0
                    ),
                    Question(
                        "Which scientist formulated the laws of motion?",
                        listOf("Albert Einstein", "Isaac Newton", "Galileo Galilei", "Nikola Tesla"),
                        1
                    ),
                    Question(
                        "What is the study of the motion of air and other gases?",
                        listOf("Thermodynamics", "Aerodynamics", "Hydrodynamics", "Electrodynamics"),
                        1
                    )
                )
            ),
            Topic("Marvel Super Heroes",
                "fictional characters franchised by Marvel",
                "fictional characters created by Marvel Comics, known for their heroic exploits, superhuman abilities, and memorable stories.",
                listOf(
                    Question(
                        "Who is the alter ego of Spider-Man?",
                        listOf("Peter Parker", "Tony Stark", "Bruce Wayne", "Clark Kent"),
                        0
                    ),
                    Question(
                        "What metal is Captain America's shield made of?",
                        listOf("Vibranium", "Adamantium", "Titanium", "Uru"),
                        0
                    ),
                    Question(
                        "Which Marvel character is known as the Sorcerer Supreme?",
                        listOf("Scarlet Witch", "Ghost Rider", "Doctor Strange", "Mystique"),
                        2
                    )
                )
            )
        )
    }
}



class QuizApp: Application() {
    lateinit var topicRepositiory : ITopicRepository

    override fun onCreate() {
        super.onCreate()
        topicRepositiory = TopicRepostiory()
        Log.d("QuizApp", "QuizApp is loading")
    }
}