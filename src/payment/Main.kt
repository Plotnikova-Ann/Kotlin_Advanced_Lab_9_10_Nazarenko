package payment

import payment.CardType
import payment.Payment
import payment.PaymentProcessor
import payment.PaymentResult

fun main() {
    println("=== СИСТЕМА ОБРАБОТКИ ПЛАТЕЖЕЙ ===\n")

    val processor = PaymentProcessor()
    val testPayments = listOf(
        Payment("4111111111111111", 1000, CardType.VISA),
        Payment("5111111111111111", 2000, CardType.MASTERCARD),
        Payment("2111111111111111", 1500, CardType.MIR),

        // Невалидные платежи
        Payment("3111111111111111", 500, CardType.UNKNOWN),
        Payment("411111", 1000, CardType.VISA),
        Payment("4111111111111111", 0, CardType.VISA),
        Payment("4111111111111111", -100, CardType.VISA),
        Payment("4111111111111111", 2000000, CardType.VISA)
    )

    println("1. Обработка тестовых платежей:")
    println("-" * 50)

    testPayments.forEachIndexed { index, payment ->
        println("\nТест ${index + 1}: $payment")
        val result = processor.pay(payment)
        processor.show(result)
        if (result is PaymentResult.Processing) {
            println("   Завершаем обработку MasterCard...")
            val finalResult = processor.processMasterCardPayment("TRX-$index")
            processor.show(finalResult)
        }
    }

    println("\n2. Демонстрация возможностей enum CardType:")
    println("-" * 50)

    val allCardTypes = CardType.values()
    println("Все доступные типы карт:")
    allCardTypes.forEachIndexed { i, type ->
        println("  ${i + 1}. $type (ordinal: ${type.ordinal}, name: '${type.name}')")
    }

    println("\nПоиск типа карты по имени 'VISA':")
    val visaType = enumValueOf<CardType>("VISA")
    println("   Найден: $visaType")

    println("\nБезопасный поиск типа карты по имени 'AMEX':")
    val amexType = enumValues<CardType>().find { it.name == "AMEX" }
    println("   Результат: ${amexType ?: "Не найден"}")

    println("\n3. Демонстрация data class Payment:")
    println("-" * 50)
    val originalPayment = Payment("4111111111111111", 1000, CardType.VISA)
    println("Оригинальный платеж: $originalPayment")
    val modifiedPayment = originalPayment.copy(
        sum = 2000,
        type = CardType.MASTERCARD
    )
    println("Модифицированная копия: $modifiedPayment")
    val anotherPayment = Payment("4111111111111111", 1000, CardType.VISA)
    println("\nСравнение платежей:")
    println("   originalPayment == anotherPayment: ${originalPayment == anotherPayment}")
    println("   originalPayment === anotherPayment: ${originalPayment === anotherPayment}")
    val (card, sum, type) = originalPayment
    println("\nДеструктуризация платежа:")
    println("   card: ****${card.takeLast(4)}")
    println("   sum: $sum")
    println("   type: $type")

    println("\n=== ОБРАБОТКА ПЛАТЕЖЕЙ ЗАВЕРШЕНА ===")
}
operator fun String.times(n: Int): String {
    return repeat(n)
}