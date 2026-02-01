package example

import kotlin.properties.Delegates
import kotlin.random.Random

class UserProfile(initialName: String, initialEmail: String) {
    var name: String by Delegates.observable(initialValue = initialName) { _, old, new ->
        println("Имя изменено: '$old' -> '$new'")
        if (new.length < 2) {
            println("Внимание: имя слишком короткое!")
        }
    }
    var email: String by Delegates.observable(initialValue = initialEmail) { _, old, new ->
        println("Email изменен: '$old' -> '$new'")
        val isValid = new.contains("@") && new.contains(".")
        if (!isValid) {
            println("Внимание: некорректный формат email!")
        } else {
            println("Email успешно обновлен")
        }
    }
    val avatar: String by lazy {
        println("Загружаем аватар для $name...")
        Thread.sleep(1000)
        val avatarId = Random.nextInt(1, 100)
        "https://api.example.com/avatars/$avatarId.png"
    }
    val createdAt: Long = System.currentTimeMillis()
    var lastUpdated: Long by Delegates.observable(System.currentTimeMillis()) { _, _, _ ->
        println("Профиль обновлен")
    }
    var isOnline: Boolean by Delegates.observable(false) { _, old, new ->
        val status = if (new) "онлайн" else "оффлайн"
        println("$name теперь $status")
    }
    fun getProfileInfo(): String {
        return """
        ИНФОРМАЦИЯ О ПРОФИЛЕ 
        Имя: $name
        Email: $email
        Аватар: $avatar
        Создан: ${java.text.SimpleDateFormat("dd.MM.yyyy HH:mm").format(java.util.Date(createdAt))}
        Обновлен: ${java.text.SimpleDateFormat("dd.MM.yyyy HH:mm").format(java.util.Date(lastUpdated))}
        Статус: ${if (isOnline) "Онлайн" else "Оффлайн"}
        """.trimMargin()
    }
    fun updateProfile(newName: String? = null, newEmail: String? = null) {
        if (newName != null) name = newName
        if (newEmail != null) email = newEmail
        lastUpdated = System.currentTimeMillis()
    }
}

fun main() {
    println(" СИСТЕМА УПРАВЛЕНИЯ ПРОФИЛЕМ \n")
    println("1. Создаём профиль пользователя...")
    val user = UserProfile(
        initialName = "Алиса",
        initialEmail = "alice@example.com"
    )

    println("\n2. Получаем информацию о профиле:")
    println(user.getProfileInfo())

    println("\n3. Обращаемся к аватару впервые:")
    println("   Файл аватара: ${user.avatar}")

    println("\n4. Обращаемся к аватару снова (должен быть взят из кэша):")
    println("   Файл аватара: ${user.avatar}")

    println("\n5. Меняем email:")
    user.email = "alice_new@example.org"

    println("\n6. Меняем имя:")
    user.name = "Алиса К."

    println("\n7. Пытаемся установить некорректный email:")
    user.email = "некорректный-email"

    println("\n8. Устанавливаем слишком короткое имя:")
    user.name = "А"

    println("\n9. Меняем статус:")
    user.isOnline = true

    println("\n10. Обновляем профиль через метод updateProfile:")
    user.updateProfile(
        newName = "Алисия",
        newEmail = "alicia@company.com"
    )

    println("\n11. Финальная информация о профиле:")
    println(user.getProfileInfo())
}