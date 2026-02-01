// Пример 1: Игровая сессия как синглтон
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

// Пример 2: Логгер
object Logger {
    private var count = 0

    fun log(message: String) {
        count++
        println("[$count] $message")
    }
}

// Пример 3: Настройки приложения
object AppSettings {
    val version = "1.0.0"
    var isDarkMode = true

    fun toggleTheme() {
        isDarkMode = !isDarkMode
    }
}

// Точка входа
fun main() {
    // GameSession создается только при первом обращении
    println("Программа запущена")
    println("Теперь запускаем игру")
    GameSession.start() // Создается объект GameSession
    println("Активна ли сессия: ${GameSession.isActive}") // true

    // Logger - всегда один экземпляр
    Logger.log("Первое сообщение")
    Logger.log("Второе сообщение")
    val logger1 = Logger
    val logger2 = Logger
    println(logger1 === logger2) // true - один и тот же объект

    // Object expression (анонимный объект)
    val anonymousObject = object {
        val name = "Анонимный объект"
        fun greet() = println("Привет из $name")
    }
    anonymousObject.greet()
}
