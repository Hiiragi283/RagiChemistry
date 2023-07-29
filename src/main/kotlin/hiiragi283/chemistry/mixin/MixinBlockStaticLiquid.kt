package hiiragi283.chemistry.mixin

import hiiragi283.chemistry.api.block.IHeatSource
import net.minecraft.block.BlockLiquid
import net.minecraft.block.BlockStaticLiquid
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidRegistry
import org.spongepowered.asm.mixin.Mixin

@Mixin(BlockStaticLiquid::class)
abstract class MixinBlockStaticLiquid(material: Material) : BlockLiquid(material), IHeatSource {

    override fun getHeat(world: World, pos: BlockPos, state: IBlockState): Int {
        return when (this.material) {
            Material.LAVA -> FluidRegistry.LAVA.temperature
            Material.WATER -> FluidRegistry.WATER.temperature
            else -> 273
        }
    }

}