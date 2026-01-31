package resources

class ResourceManager {
    private val resources = mutableListOf<OutpostResource>()
    fun add(resource: OutpostResource) {
        resources.add(resource)
        println("Добавлен ресурc: ${resource.name}")
        fun get(name: String): OutpostResource? {
            return resources.find { it.name == name }

        }
        fun printAll() {
            println("Peсурсы базы")
            resources.forEach { println("${it.name}: ${it.amount}") }
        }
    }
}