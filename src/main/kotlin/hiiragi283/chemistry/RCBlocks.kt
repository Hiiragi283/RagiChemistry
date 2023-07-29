package hiiragi283.chemistry

import hiiragi283.chemistry.api.block.RCBlockBase
import hiiragi283.chemistry.block.RCBlockCrucible
import hiiragi283.material.api.HiiragiEntry
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RCBlocks : HiiragiEntry.BLOCK {

    //val ORE1 = RCBlockOre(Material.ROCK)
    val CRUCIBLE = RCBlockCrucible

    var list: List<RCBlockBase> = this::class.java.declaredFields
        .map { it.also { it.isAccessible = true } }
        .map { it.get(this) }
        .filterIsInstance<RCBlockBase>()

    override fun register(registry: IForgeRegistry<Block>) {
        list.forEach { registry.register(it) }
    }

    fun register(registry: IForgeRegistry<Item>) {
        list.map { it.itemBlock }.forEach { registry.register(it) }
    }

    override fun registerOreDict() {
        list.forEach { it.registerOreDict() }
    }

    override fun registerRecipe() {
        list.forEach { it.registerRecipe() }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        list.forEach { it.registerColorBlock(blockColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        list.forEach { it.registerColorItem(itemColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        list.forEach { it.registerModel() }
    }

}