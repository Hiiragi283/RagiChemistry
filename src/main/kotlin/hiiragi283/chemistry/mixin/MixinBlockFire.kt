package hiiragi283.chemistry.mixin

import hiiragi283.chemistry.api.block.IHeatSource
import net.minecraft.block.BlockFire
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.spongepowered.asm.mixin.Mixin

@Mixin(BlockFire::class)
abstract class MixinBlockFire : IHeatSource {

    override fun getHeat(world: World, pos: BlockPos, state: IBlockState): Int = 1000 + 273

}