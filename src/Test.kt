var age: Int = 18
set(value) {
    if ((value > 0) and (value < 110))
        field = value
}
fun main() {
        println(age)
    age = 45
    println(age)
    age = -345
    println(age)
}