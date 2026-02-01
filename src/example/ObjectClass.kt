object GameSession {
    init {
        println("Игровая сессия создана")
    }

    var isActive: Boolean = false

    fun start() {
        isActive = true
        println("Игра началась")
    }

    fun end() {
        isActive = false
        println("Игра завершена")
    }
}
object Logger {
    private var count = 0

    fun log(message: String) {
        count++
        println("[$count] $message")
    }
}
object AppSettings {
    val version = "1.0.0"
    var isDarkMode = true

    fun toggleTheme() {
        isDarkMode = !isDarkMode
    }
}

fun main() {
    println("Программа запущена")
    println("Теперь запускаем игру")
    GameSession.start()
    println("Активна ли сессия: ${GameSession.isActive}")
    Logger.log("Первое сообщение")
    Logger.log("Второе сообщение")
    val logger1 = Logger
    val logger2 = Logger
    println(logger1 === logger2)
    val anonymousObject = object {
        val name = "Анонимный объект"
        fun greet() = println("Привет из $name")
    }
    anonymousObject.greet()
}
