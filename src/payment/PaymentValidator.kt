package payment
class PaymentValidator {
    fun check(payment: Payment): Boolean {
        val cardNumber = payment.card
        val isCardNumberValid = when (payment.type) {
            CardType.VISA -> cardNumber.length == 16 && cardNumber.startsWith("4")
            CardType.MASTERCARD -> cardNumber.length == 16 && cardNumber.startsWith("5")
            CardType.MIR -> cardNumber.length == 16 && cardNumber.startsWith("2")
            CardType.UNKNOWN -> cardNumber.length == 16
        }
        val isSumValid = payment.sum > 0 && payment.sum <= 1000000
        val isLuhnValid = validateLuhn(cardNumber)

        return isCardNumberValid && isSumValid && isLuhnValid
    }

    private fun validateLuhn(cardNumber: String): Boolean {
        if (cardNumber.any { !it.isDigit() }) return false

        val sum = cardNumber
            .map { it.toString().toInt() }
            .sum()

        return sum % 10 != 0
    }
}
