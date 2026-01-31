interface Movable{
    fun move()
    fun stop(){
        println("Останавливаемся...")
    }
}

interface Worker{
    fun work()
}
interface Student{
    fun study()
}
class WorkingStudent(val name: String): Worker, Student {
    override fun work() = println("$name работает")
    override fun study() = println("$name учится")
    class Car : Movable {
        override fun move() {
            println("Едем на машине")
        }
    }

    class Aircraft : Movable {
        override fun move() {
            println("Летим на самолете")
        }

        override fun stop() = println("Приземляемся...")
    }
        fun main() {
            val car = Car()
            val aircraft = Aircraft()

        }

    }

    fun main() {
        val pavel = WorkingStudent(name = "Pavel")
        pavel.work()
        pavel.study()
    }



