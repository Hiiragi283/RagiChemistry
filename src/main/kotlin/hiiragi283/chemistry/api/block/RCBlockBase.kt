package hiiragi283.chemistry.api.block

import hiiragi283.chemistry.RCReference
import hiiragi283.chemistry.api.item.RCItemBlockBase
import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.util.RMModelManager
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class RCBlockBase(id: String, material: Material, private val maxTips: Int) : Block(material),
    HiiragiEntry.BLOCK {

    abstract val itemBlock: RCItemBlockBase?

    init {
        setRegistryName(RCReference.MOD_ID, id)
        translationKey = id
    }

    //    BlockState    //

    fun getFacing(state: IBlockState): EnumFacing = state.getValue(BlockHorizontal.FACING)

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.path
        if (maxTips != -1) {
            tooltip.add("§e=== Info ===")
            for (i in 0..maxTips) {
                tooltip.add(I18n.format("tips.${RCReference.MOD_ID}.${path}.$i"))
            }
        }
    }

    //    HiiragiEntry    //

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        RMModelManager.setModel(this)
    }

}