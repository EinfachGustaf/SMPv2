package live.einfachgustaf.smp.features.advancements.api

/**
 * Builds a tree structure of advancements starting from the given root advancement.
 *
 * @param root The root advancement from which to build the tree.
 * @param x The x-coordinate for the root advancement in the tree layout.
 * @param y The y-coordinate for the root advancement in the tree layout.
 * @param xStep The horizontal step size for child advancements.
 * @param yStep The vertical step size for child advancements.
 * @return An `AdvancementNode` representing the root of the advancement tree,
 *         with its position and children advancements.
 */
fun buildAdvancementTree(
    root: AbstractAdvancement,
    x: Double = 0.0,
    y: Double = 0.0,
    xStep: Double = 1.5,
    yStep: Double = 1.5
): AdvancementNode {
    val children = root.unlocks.mapIndexed { i, child ->
        buildAdvancementTree(child, x + xStep, y + i * yStep, xStep, yStep)
    }
    return AdvancementNode(root, x, y, children)
}