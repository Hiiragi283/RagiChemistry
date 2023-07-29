package hiiragi283.chemistry.tile

import hiiragi283.chemistry.RCReference
import hiiragi283.chemistry.api.block.IHeatSource
import hiiragi283.chemistry.api.capability.RCCapabilityProvider
import hiiragi283.chemistry.api.capability.fluid.RCFluidTank
import hiiragi283.chemistry.api.capability.fluid.RCFluidTankWrapper
import hiiragi283.chemistry.api.capability.item.RCItemHandler
import hiiragi283.chemistry.api.capability.item.RCItemHandlerWrapper
import hiiragi283.chemistry.api.tile.RCTIleProvider
import hiiragi283.chemistry.api.tile.RCTileBase
import hiiragi283.chemistry.api.tile.RCTileContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

class RCTileCrucible : RCTileBase(), RCTIleProvider.Inventory, RCTIleProvider.Tank, RCTileContainer {

    private fun getHeat(world: World, pos: BlockPos): Int {
        val state = world.getBlockState(pos)
        val block = state.block
        return if (block is IHeatSource) block.getHeat(world, pos, state) else 273
    }

    //    RCTileBase    //

    override fun onTileActivated(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ): Boolean {
        if (!world.isRemote) {
            openGui(player)
            return true
        }
        return false
    }

    //    RCTIleProvider    //

    lateinit var invInput: RCItemHandler
    lateinit var tankOutput: RCFluidTank

    override fun createInventory(): RCCapabilityProvider<IItemHandler> {
        invInput = RCItemHandler(1, this)
        inventory = RCItemHandlerWrapper(invInput)
        return RCCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory)
    }

    override fun createTank(): RCCapabilityProvider<IFluidHandler> {
        tankOutput = RCFluidTank(144 * 9)
        tank = RCFluidTankWrapper(tankOutput)
        return RCCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank)
    }

    //    RCTileContainer    //

    override fun createContainer(playerInventory: InventoryPlayer, playerIn: EntityPlayer): Container {
        TODO("Not yet implemented")
    }

    override fun getDisplayName(): TextComponentTranslation = super<RCTileContainer>.getDisplayName()

    override fun getGuiID(): String = "${RCReference.MOD_ID}.crucible"

}