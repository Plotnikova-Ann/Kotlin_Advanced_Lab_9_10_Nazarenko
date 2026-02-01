package example
import kotlin.properties.Delegates
interface Base {
    fun printMessage()
    fun printMessageLine()
}
class BaseImpl(val x: Int) : Base {
    override fun printMessage() { print(x) }
    override fun printMessageLine() { println(x) }
}
class Derived(b: Base) : Base by b {
    override fun printMessage() { print("Derived: ") }
}
interface Messenger {
    fun send(message: String)
    fun sendTextMessage(text: String) {
        println("Отправляем текстовое сообщение: $text")
    }
}

class InstantMessenger : Messenger {
    override fun send(message: String) {
        println("Отправляем сообщение через Instant Messenger: $message")
    }
}

interface PhotoDevice {
    fun takePhoto()
}
class PhotoCamera : PhotoDevice {
    override fun takePhoto() {
        println("Делаем снимок на камеру")
    }
}

class SmartPhone(
    private val m: Messenger,
    private val p: PhotoDevice
) : Messenger by m, PhotoDevice by p {
    override fun sendTextMessage(text: String) {
        println("Отправляем сообщение через смартфон: $text")
    }
    fun makeCall(number: String) {
        println("Звоним по номеру: $number")
    }
}

fun main() {
    println("=== Пример делегирования интерфейсов ===")
    val baseImpl = BaseImpl(10)
    val derived = Derived(baseImpl)
    derived.printMessage()
    derived.printMessageLine()

    println("\n=== Пример делегирования в смартфоне ===")

    val messenger = InstantMessenger()
    val camera = PhotoCamera()
    val smartphone = SmartPhone(messenger, camera)
    smartphone.send("Привет!")
    smartphone.takePhoto()
    smartphone.sendTextMessage("Как дела?")
    smartphone.makeCall("+7 999 123-45-67")

    println("\n=== Пример делегирования свойств ===")
    var counter: Int by Delegates.observable(initialValue = 0) { _, old, new ->
        println("Счётчик изменился: $old -> $new")
    }

    counter = 1
    counter = 5
    counter = 10
    class User {
        var name: String by Delegates.observable(initialValue = "<no name>") { _, old, new ->
            println("Имя изменено: '$old' -> '$new'")
        }
    }

    val user = User()
    user.name = "Алексей"
    user.name = "Александр"

    println("\n=== Пример с Vetoable (дополнительно) ===")
    var age: Int by Delegates.vetoable(initialValue = 18) { _, old, new ->
        if (new < 0) {
            println("Возраст не может быть отрицательным! ($new)")
            false
        } else if (new > 150) {
            println("Возраст не может быть больше 150! ($new)")
            false
        } else {
            println("Возраст изменен: $old -> $new")
            true
        }
    }

    age = 25
    age = -5
    age = 200
    age = 30
}
