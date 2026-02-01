package sealedclasses
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
        println("Тема изменена: ${if (isDarkMode) "Тёмная" else "Светлая"}")
    }
}
object MathUtils {
    fun add(a: Int, b: Int): Int = a + b
    fun subtract(a: Int, b: Int): Int = a - b
    fun multiply(a: Int, b: Int): Int = a * b
    fun divide(a: Int, b: Int): Double = a.toDouble() / b
}
fun main() {
    println("=== Демонстрация object в Kotlin ===\n")

    println("1. GameSession (ленивая инициализация):")
    println("Программа запущена")
    println("Теперь запускаем игру...")
    GameSession.start()
    println("Активна ли сессия: ${GameSession.isActive}")
    GameSession.end()

    println("\n2. Logger (синглтон):")
    Logger.log("Первое сообщение")
    Logger.log("Второе сообщение")
    val logger1 = Logger
    val logger2 = Logger
    println("logger1 и logger2 - один объект? ${logger1 === logger2}")

    println("\n3. AppSettings (глобальные настройки):")
    println("Версия приложения: ${AppSettings.version}")
    println("Тёмная тема: ${AppSettings.isDarkMode}")
    AppSettings.toggleTheme()

    println("\n4. MathUtils (утилиты):")
    println("10 + 5 = ${MathUtils.add(10, 5)}")
    println("10 * 5 = ${MathUtils.multiply(10, 5)}")

    println("\n5. Object expression (анонимный объект):")
    val anonymousObject = object {
        val name = "Анонимный объект"
        val createdAt = System.currentTimeMillis()
        fun greet() = println("Привет из $name, создан в $createdAt")
    }
    anonymousObject.greet()
}