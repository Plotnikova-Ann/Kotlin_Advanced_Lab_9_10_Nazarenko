package example

import kotlin.properties.Delegates
import kotlin.random.Random

class GameHero(startName: String) {
    var name: String by Delegates.observable(initialValue = startName) { _, old, new ->
        println(" Имя героя изменено: '$old' -> '$new'")
        if (new.contains("Тёмный") || new.contains("Темный")) {
            println(" Герой перешел на темную сторону!")
            alignment = "Темная"
        } else if (new.contains("Света") || new.contains("Светлый")) {
            println("  Герой перешел на светлую сторону!")
            alignment = "Светлая"
        }
    }
    var mana: Int by Delegates.observable(initialValue = 100) { _, old, new ->
        println("Мана изменена: $old -> $new")

        when {
            new < 20 -> println("  Внимание! Мана на исходе!")
            new > 150 -> println("  Мана переполнена! (+${new - 150} избыточной)")
        }

        updateManaState()
    }

    var health: Int by Delegates.observable(initialValue = 100) { _, old, new ->
        println("Здоровье изменено: $old -> $new")

        when {
            new < 30 -> println(" Критическое состояние здоровья!")
            new <= 0 -> die()
        }
        updateHealthState()
    }
    val ultimate: String by lazy {
        println("\n Загружаем супер-способность для $name...")
        Thread.sleep(1500)
        println(" Анимация и звуки загружены!")
        when {
            name.contains("Маг") || name.contains("Волшебник") -> "Метеоритный дождь"
            name.contains("Воин") || name.contains("Рыцарь") -> "Берсерк"
            name.contains("Лучник") || name.contains("Охотник") -> "Шквал стрел"
            else -> "Сокрушительный удар"
        }
    }
    val inventory: Map<String, Int> by lazy {
        println("\n Собираем инвентарь для $name...")
        mapOf(
            "Зелье лечения" to Random.nextInt(1, 5),
            "Зелье маны" to Random.nextInt(1, 5),
            "Золотые монеты" to Random.nextInt(10, 100)
        )
    }
    private var _killCount: Int = 0
    var killCount: Int
        get() = _killCount
        set(value) {
            val old = _killCount
            _killCount = value
            println(" Счетчик убийств: $old -> $value")
            when (value) {
                10 -> println(" Достижение: Новичок-охотник")
                50 -> println(" Достижение: Опытный воин")
                100 -> println(" Достижение: Легенда")
            }
        }
    var level: Int by Delegates.observable(initialValue = 1) { _, old, new ->
        println("Уровень повышен: $old -> $new")
        println(" +20 к максимальному здоровью")
        println(" +10 к максимальной мане")
        health += 20
        mana += 10
    }
    var alignment: String = "Нейтральная"
    private var manaState: String = "Норма"
    private var healthState: String = "Здоров"
    private fun updateManaState() {
        manaState = when (mana) {
            in 0..20 -> "Истощена"
            in 21..50 -> "Низкая"
            in 51..100 -> "Норма"
            else -> "Избыток"
        }
    }

    private fun updateHealthState() {
        healthState = when (health) {
            in 0..20 -> "Критическое"
            in 21..50 -> "Ранен"
            in 51..80 -> "Легко ранен"
            else -> "Здоров"
        }
    }

    private fun die() {
        println("\nГЕРОЙ $name ПОГИБ!")
        println("   Игра окончена...")
    }

    fun attack(target: String): Int {
        val damage = Random.nextInt(10, 25)
        println("\n $name атакует $target и наносит $damage урона!")

        mana -= 5
        return damage
    }

    fun castSpell(spellName: String): Boolean {
        val manaCost = when (spellName) {
            "Огненный шар" -> 30
            "Ледяная стрела" -> 20
            "Исцеление" -> 40
            else -> 25
        }

        if (mana >= manaCost) {
            println("\n $name кастует '$spellName' (стоимость: $manaCost маны)")
            mana -= manaCost
            return true
        } else {
            println("\nНедостаточно маны для заклинания '$spellName'")
            return false
        }
    }

    fun rest() {
        println("\n$name отдыхает...")
        health = (health + 30).coerceAtMost(100)
        mana = (mana + 50).coerceAtMost(200)
    }
    fun getHeroInfo(): String {
        return """
         ИНФОРМАЦИЯ О ГЕРОЕ 
        Имя: $name
        Уровень: $level
        Здоровье: $health ($healthState)
        Мана: $mana ($manaState)
        Выравнивание: $alignment
        Убийств: $killCount
        Супер-способность: $ultimate
        Инвентарь: ${inventory.entries.joinToString { "${it.key} x${it.value}" }}
        """.trimMargin()
    }
}

fun main() {
    println(" ИГРОВОЙ ПЕРСОНАЖ С ДЕЛЕГИРОВАНИЕМ \n")

    println("1. Создаём героя...")
    val hero = GameHero(startName = "Воин Света")

    println("\n2. Информация о герое при создании:")
    println(hero.getHeroInfo())

    println("\n3. Герой пытается использовать способность (первый раз - загрузка):")
    println("   Способность: ${hero.ultimate}")

    println("\n4. Повторный вызов способности (должна быть мгновенной):")
    println("   Способность: ${hero.ultimate}")

    println("\n5. Открываем инвентарь (первый раз - загрузка):")
    println("   Инвентарь: ${hero.inventory}")

    println("\n6. Повторный просмотр инвентаря (должен быть мгновенным):")
    println("   Инвентарь: ${hero.inventory}")

    println("\n7. Меняем имя героя:")
    hero.name = "Тёмный Паладин"

    println("\n8. Герой атакует:")
    hero.attack("Гоблин")

    println("\n9. Герой восстанавливает ману:")
    hero.mana = 200

    println("\n10. Герой получает урон:")
    hero.health = 70
    hero.health = 40
    hero.health = 15

    println("\n11. Герой использует заклинания:")
    hero.castSpell("Огненный шар")
    hero.castSpell("Ледяная стрела")

    println("\n12. Герой повышает уровень:")
    hero.level = 2
    hero.level = 3

    println("\n13. Герой увеличивает счетчик убийств:")
    hero.killCount = 8
    hero.killCount = 15
    hero.killCount = 52
    hero.killCount = 105

    println("\n14. Герой отдыхает:")
    hero.rest()

    println("\n15. Финальная информация о герое:")
    println(hero.getHeroInfo())

    println("\n ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ")
}