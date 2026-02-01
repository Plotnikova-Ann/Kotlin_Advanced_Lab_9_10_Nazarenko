## Геттеры и сеттеры
В Kotlin геттеры и сеттеры генерируются автоматически для свойств, но можно создавать кастомные реализации.

Примеры:

    var validatedValue: Int = 0
        set(value) {
            if (value >= 0) {
                field = value
            } else {
                println("Значение не может быть отрицательным")
            }
        }
}

## Инкапсуляция
Инкапсуляция - это сокрытие внутренней реализации и предоставление безопасного интерфейса для работы с объектом.

Пример:

    fun withdraw(amount: Double): Boolean {
        if (amount > 0 && _balance >= amount) {
            _balance -= amount
            return true
        }
        return false
    }
}


## Data-классы
Data-классы предназначены для хранения данных и автоматически генерируют полезные методы.

Пример:
// data class User(
val id: Int,
val name: String,
val email: String,
val age: Int = 18 // Значение по умолчанию
)

## Абстрактные классы
Абстрактные классы определяют общий интерфейс для наследников, но не могут быть инстанциированы напрямую.

Пример:

    fun printInfo() {
        println("Площадь фигуры: $area")
    }
}

## Интерфейсы
Интерфейсы определяют контракт, который должны реализовать классы. В Kotlin интерфейсы могут содержать реализации методов по умолчанию.

Пример:

    fun printInfo() {
        println("Это графический объект")
    }
}


## Galaxy Outpost Manager
___
Учебный проект на Kotlin, демонстрирующий основы объектно-ориентированного программированияиархитектурные приёмы языка. 
___
## Sealed-классы
___ 
Sealed-классы используются для представления ограниченного набора состояний или результатов, которыеизвестны на этапе компиляции. Они позволяют:
гарантировать обработку всех возможных вариантов;
безопасно использовать конструкцию when без else;
удобно описывать состояния, события и результаты действий. Пример: результат работы модуля
sealed-class ModuleResult {
data class Success(val message: String) : ModuleResult()
data class ResourceProduced(val resourceName: String, val amount: Int) : ModuleResult()
data class NotEnoughResources(
val resourceName: String, val required: Int, val available: Int
) : ModuleResult()
data class Error(val reason: String) : ModuleResult()
}
## Object в Kotlin
___

object — это специальная конструкция Kotlin, которая создаёт единственный экземпляр класса (Singleton).Особенности:
создаётся при первом обращении;
существует в одном экземпляре;
не имеет конструктора. 
## Пример: глобальный логгер
object Logger {
private var counter = 0
fun log(message: String) {
counter++
println("[$counter] $message")
}
}
## Использование:
Logger.log("Инициализация системы")
Logger.log("Модуль запущен")
object удобно использовать для:
логгеров;
конфигураций;
состояний без данных в sealed-классах;
утилитарных классов.