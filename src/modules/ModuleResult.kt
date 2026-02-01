package modules
sealed class ModuleResult {
    data class Success(val message: String) : ModuleResult()
    data class ResourceProduced(
        val resourceName: String,
        val amount: Int
    ) : ModuleResult()
    data class NotEnoughResources(
        val resourceName: String,
        val required: Int,
        val available: Int
    ) : ModuleResult()
    data class Error(val reason: String) : ModuleResult()
}
fun handleModuleResult(result: ModuleResult) {
    when (result) {
        is ModuleResult.Success -> {
            println("УСПЕХ: ${result.message}")
        }
        is ModuleResult.ResourceProduced -> {
            println("Произведено: ${result.resourceName} +${result.amount}")
        }
        is ModuleResult.NotEnoughResources -> {
            println(" Недостаточно ресурса '${result.resourceName}'. " +
                    "Нужно: ${result.required}, есть: ${result.available}")
        }
        is ModuleResult.Error -> {
            println("ОШИБКА: ${result.reason}")
        }
    }
}