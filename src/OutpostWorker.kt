class OutpostWorker(val name: String) {

    var maxEnergy: Int = 100
        private set

    var energy: Int = maxEnergy
        set(value) {
            field = value.coerceIn(0..maxEnergy)
        }

    var mood: Int = 50
        get() = field + (energy / 10)

    var level: Int = 1
        private set

    fun work() {
        println("$name начинает работать...")
        energy -= 15
        if (energy < 20) println(" $name устал!")
    }

    fun rest() {
        println("$name отдыхает...")
        energy += 20
    }

    fun levelUp() {
        level++
        maxEnergy += 20
        energy = maxEnergy
        println("$name повышает уровень: $level | Максимальная энергия увеличена до: $maxEnergy")
    }
}