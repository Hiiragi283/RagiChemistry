package hiiragi283.chemistry.api.block

import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface IHeatSource {

    fun getHeat(world: World, pos: BlockPos, state: IBlockState): Int

}