package resources
import kotlin.properties.Delegates
class OutpostResource(
    val id: Int,
    val name: String,
    amountInit: Int = 0
) {
    var amount: Int by Delegates.observable(initialValue = amountInit) { _, old, new ->
        println(" Ресурс '$name' (ID: $id) изменён: $old → $new")
        when {
            new == 0 -> println(" Ресурс '$name' истощён!")
            new < 0 -> {
                println("  ОШИБКА: количество ресурса не может быть отрицательным!")
                amount = 0
            }
            new > 1000 -> println(" Ресурс '$name' в изобилии!")
            old < new -> println(" Ресурс '$name' увеличен на ${new - old}")
            old > new -> println("  Ресурс '$name' уменьшен на ${old - new}")
        }
        onAmountChanged?.invoke(old, new)
    }
    var onAmountChanged: ((old: Int, new: Int) -> Unit)? = null
    var minAmount: Int = 0
        set(value) {
            field = value
            println("  Минимальное количество для '$name' установлено: $value")
        }
    var maxCapacity: Int = 1000
        set(value) {
            field = value
            println("  Максимальная вместимость для '$name' установлена: $value")
        }
    fun add(quantity: Int): Boolean {
        if (quantity <= 0) {
            println(" Нельзя добавить отрицательное или нулевое количество")
            return false
        }

        val newTotal = amount + quantity
        if (newTotal > maxCapacity) {
            println(" Превышена максимальная вместимость! Доступно места: ${maxCapacity - amount}")
            return false
        }
        amount = newTotal
        return true
    }

    // Метод для использования ресурса
    fun use(quantity: Int): Boolean {
        if (quantity <= 0) {
            println("Нельзя использовать отрицательное или нулевое количество")
            return false
        }

        if (amount - quantity < minAmount) {
            println(" Нельзя опуститься ниже минимального значения $minAmount")
            return false
        }
        amount -= quantity
        return true
    }
    fun hasEnough(required: Int): Boolean {
        val hasEnough = amount >= required
        if (!hasEnough) {
            println("Недостаточно ресурса '$name'. Нужно: $required, есть: $amount")
        }
        return hasEnough
    }
    fun getInfo(): String {
        return "Ресурс #$id '$name': $amount/$maxCapacity (мин: $minAmount)"
    }

    override fun toString(): String {
        return getInfo()
    }
}