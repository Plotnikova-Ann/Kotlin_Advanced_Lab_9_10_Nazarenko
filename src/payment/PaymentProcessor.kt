package payment
class PaymentProcessor {
    private val validator = PaymentValidator()
    private var transactionCounter = 0

    fun pay(payment: Payment): PaymentResult {
        transactionCounter++

        if (!validator.check(payment)) {
            return PaymentResult.Error("Ошибка валидации платежа")
        }

        return when (payment.type) {
            CardType.VISA -> {
                PaymentResult.Success("VISA-TRX-$transactionCounter-${System.currentTimeMillis()}")
            }
            CardType.MASTERCARD -> {
                PaymentResult.Processing
            }
            CardType.MIR -> {
                PaymentResult.Success("MIR-TRX-$transactionCounter-${System.currentTimeMillis()}")
            }
            CardType.UNKNOWN -> {
                PaymentResult.Error("Неизвестный тип карты")
            }
        }
    }

    fun show(result: PaymentResult) {
        when (result) {
            is PaymentResult.Success -> {
                println("Успех: транзакция ${result.id}")
            }
            is PaymentResult.Error -> {
                println("Ошибка: ${result.reason}")
            }
            PaymentResult.Processing -> {
                println(" Платеж в обработке...")
            }
        }
    }

    fun processMasterCardPayment(paymentId: String): PaymentResult {
        Thread.sleep(1000)
        return PaymentResult.Success("MASTERCARD-COMPLETED-$paymentId")
    }
}
