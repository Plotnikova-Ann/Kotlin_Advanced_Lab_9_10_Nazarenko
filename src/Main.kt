fun main() {
    val manager = ResourceManager()
    val minerals = OutpostResource (id = 1, name = "Minerals", amount = 300)
    val gas = OutpostResource (id = 2, name = "Gas", amount = 100)
    manager.add(minerals)
    manager.add(gas)
    val bonus = minerals.copy(amount = minerals.amount + 50)
    println("Копия минералов с бонусом: $bonus")
}
