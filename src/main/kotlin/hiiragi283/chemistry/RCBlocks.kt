package hiiragi283.chemistry

import hiiragi283.chemistry.block.RCBlockOre
import hiiragi283.material.api.HiiragiEntry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RCBlocks : HiiragiEntry.BLOCK {

    val ORE1 = RCBlockOre(Material.ROCK)

    override fun register(registry: IForgeRegistry<Block>) {
        registry.register(ORE1)
    }

    override fun registerOreDict() {
        ORE1.registerOreDict()
    }

    override fun registerRecipe() {
        ORE1.registerRecipe()
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        ORE1.registerColorBlock(blockColors)
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        ORE1.registerColorItem(itemColors)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        ORE1.registerModel()
    }

}