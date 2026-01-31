package actions

import resources.ResourceManager

interface ModuleAction {
    fun execute(manager: ResourceManager)
}