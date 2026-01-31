fun main() {
    val manager = ResourceManager()
    val minerals = OutpostResource (1 ,  "Minerals",  300)
    val gas = OutpostResource ( 2,  "Gas",  100)
    manager.add(minerals)
    manager.add(gas)
    val bonus = minerals.copy(amount = minerals.amount + 50)
    println("Копия минералов с бонусом: $bonus")
}
