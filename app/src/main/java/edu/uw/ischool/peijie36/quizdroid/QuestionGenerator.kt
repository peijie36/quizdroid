package edu.uw.ischool.peijie36.quizdroid

object QuestionGenerator {

    fun generateMathQuestions(): List<Question> {
        return listOf(
            Question(
                "What is the value of the golden ratio (φ)?",
                listOf("1.618", "3.141", "2.718", "0.618"),
                "1.618"
            ),
            Question(
                "How many sides does a octagon have?",
                listOf("5", "6", "7", "8"),
                "8"
            ),
            Question(
                "What is the area of a square with side length 6 units?",
                listOf("24", "30", "36", "42"),
                "36"
            )
        )
    }

    fun generatePhysicsQuestions(): List<Question> {
        return listOf(
            Question(
                "What is the force that pulls objects towards the center of the Earth?",
                listOf("Gravity", "Magnetism", "Friction", "Tension"),
                "Gravity"
            ),
            Question(
                "Which scientist formulated the laws of motion?",
                listOf("Isaac Newton", "Albert Einstein", "Galileo Galilei", "Nikola Tesla"),
                "Isaac Newton"
            ),
            Question(
                "What is the study of the motion of air and other gases?",
                listOf("Thermodynamics", "Aerodynamics", "Hydrodynamics", "Electrodynamics"),
                "Aerodynamics"
            )
        )
    }

    fun generateMarvelQuestions(): List<Question> {
        return listOf(
            Question(
                "Who is the alter ego of Spider-Man?",
                listOf("Peter Parker", "Tony Stark", "Bruce Wayne", "Clark Kent"),
                "Peter Parker"
            ),
            Question(
                "What metal is Captain America's shield made of?",
                listOf("Vibranium", "Adamantium", "Titanium", "Uru"),
                "Vibranium"
            ),
            Question(
                "Which Marvel character is known as the Sorcerer Supreme?",
                listOf("Doctor Strange", "Scarlet Witch", "Ghost Rider", "Mystique"),
                "Doctor Strange"
            ),
        )
    }
}