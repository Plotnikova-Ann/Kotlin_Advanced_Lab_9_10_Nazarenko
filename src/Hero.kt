class Hero(val name: String) {
    var health: Int = 100
        set(value) {
            field = value.coerceIn(0..100)
        }
    var stamina: Int = 50
        get() = field + 10
    var level: Int = 1
        private set

    fun levelUp() {
        level++
        println("$name повышает уровень: $level")
    }
}




fun main() {
    val hero = Hero("Утер")
    hero.health = 150
    println(hero.health)
    println(hero.stamina)
    // hero.level = 10
    hero.levelUp()
}
