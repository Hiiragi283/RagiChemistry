package hiiragi283.chemistry.mixin

import hiiragi283.chemistry.api.block.IHeatSource
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.BlockFluidBase
import org.spongepowered.asm.mixin.Mixin

@Mixin(BlockFluidBase::class)
abstract class MixinFluidBlockBase : IHeatSource {

    override fun getHeat(world: World, pos: BlockPos, state: IBlockState): Int =
        (this as BlockFluidBase).fluid.temperature

}