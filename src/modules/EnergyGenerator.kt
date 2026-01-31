package modules

import resources.ResourceManager
import modules.OutpostModule

class EnergyGenerator: OutpostModule(name = "Генератор энергии") {
    override fun performAction(manager: ResourceManager) {
        println("Генератор работает... Производит 20 энергии")

    }
}