package live.einfachgustaf.mods.smp.component

import live.einfachgustaf.mods.smp.LOGGER
import live.einfachgustaf.mods.smp.component.properties.PropertyDelegate

/**
 * A test component.
 * @param requirements The requirements of this component.
 */
@ComponentInfo(
    name = "Test Component",
    description = "A test component.",
    dependencies = [Dependency(AnotherComponent::class)]
)
class TestComponent(override val requirements: MutableMap<String, () -> Boolean>) : SMPComponent("test") {
    val testProperty: String by PropertyDelegate("test_value")
    val booleanProperty: Boolean by PropertyDelegate(false)

    override fun load() {
        LOGGER.info("Test component loaded! Value of testProperty is $testProperty. Value of booleanProperty is $booleanProperty.")
    }
}

/**
 * Another test component.
 * @param requirements The requirements of this component.
 */
@ComponentInfo(
    name = "Another Component",
    description = "Another test component.",
)
class AnotherComponent(override val requirements: MutableMap<String, () -> Boolean>) : SMPComponent("another")

/**
 * Initialize the components.
 */
fun initialize() {
    val registry = ComponentRegistry()

    registry.register(AnotherComponent(mutableMapOf()))
    registry.register(TestComponent(mutableMapOf()))

    registry.loadComponents()
    registry.postStartComponents()
    registry.preStopComponents()

    registry.components.forEach {
        LOGGER.info("Component ${it.getInfo()?.name} is loaded: ${it.isLoaded}")
    }
}