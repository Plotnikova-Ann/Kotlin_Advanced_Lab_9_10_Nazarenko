package payment
sealed class PaymentResult {
    data class Success(val id: String) : PaymentResult()
    data class Error(val reason: String) : PaymentResult()
    object Processing : PaymentResult()
}
enum class CardType {
    VISA, MASTERCARD, MIR, UNKNOWN
}

data class Payment(
    val card: String,
    val sum: Int,
    val type: CardType = CardType.UNKNOWN
) {
    override fun toString(): String {
        return "Payment(card=****${card.takeLast(4)}, sum=$sum, type=$type)"
    }
}