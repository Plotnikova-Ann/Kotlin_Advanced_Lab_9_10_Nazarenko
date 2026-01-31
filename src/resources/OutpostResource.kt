package resources

data class OutpostResource(
    val id: Int,
    val name: String,
    var amount: Int
) {
    override fun toString(): String {
        return "Baw ресурс: $id | Имя: $name | Количество: $amount"
    }
}
fun main() {
    val gas = OutpostResource (id = 1, name = "Gas", amount = 100)
    val mineral = OutpostResource (id = 2, name = "Minerals", amount = 250)
    println("Успех! Вы добыли дополнительное количество минералов: ${mineral.amount + 50}")
    val bonusMineral = mineral.copy(id = 3, name = "Minerals Bonus", amount = mineral.amount + 50)
    println(gas.toString())
    println(mineral.toString())
    println(bonusMineral.toString())
}