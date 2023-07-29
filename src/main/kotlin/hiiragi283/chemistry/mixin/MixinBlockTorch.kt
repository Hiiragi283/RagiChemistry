package hiiragi283.chemistry.mixin

import hiiragi283.chemistry.api.block.IHeatSource
import net.minecraft.block.BlockTorch
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.spongepowered.asm.mixin.Mixin

@Mixin(BlockTorch::class)
abstract class MixinBlockTorch : IHeatSource {

    override fun getHeat(world: World, pos: BlockPos, state: IBlockState): Int = 400 + 273

}