package hiiragi283.chemistry.api.tile

import hiiragi283.chemistry.RCGuiHandler
import hiiragi283.chemistry.RagiChemistry
import hiiragi283.chemistry.api.capability.energy.RCEnergyStorage
import hiiragi283.chemistry.api.capability.fluid.RCFluidTankWrapper
import hiiragi283.chemistry.api.capability.item.RCItemHandlerWrapper
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class RCTileBase : TileEntity() {

    open lateinit var inventory: RCItemHandlerWrapper
    open lateinit var tank: RCFluidTankWrapper
    open lateinit var energy: RCEnergyStorage

    //    General    //

    fun getState(): IBlockState = if (hasWorld()) world.getBlockState(pos) else Blocks.AIR.defaultState

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound = writeToNBT(NBTTagCompound()) //オーバーライドしないと正常に更新されない

    //    Packet    //

    override fun getUpdatePacket(): SPacketUpdateTileEntity = SPacketUpdateTileEntity(pos, 0, updateTag) //NBTタグの情報を送る

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        readFromNBT(pkt.nbtCompound) //受け取ったパケットのNBTタグを書き込む
    }

    /**
     * Thanks to defeatedcrow!
     * Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/TileIBC.java#L93
     */

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean =
        oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue

    //    CommonEvent    //

    open fun onTileActivated(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ): Boolean = false

    open fun onTilePlaced(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
    }

    fun readNBTFromStack(stack: ItemStack) {
        //ItemStackに保存されたNBTタグを取得し，nullでない場合
        stack.getSubCompound("BlockEntityTag")?.let {
            //TileEntityからNBTタグを取得
            val tagTile = updateTag
            //tagTileにtagStackを合併
            tagTile.merge(it)
            //座標を修正する
            tagTile.setInteger("x", this.pos.x)
            tagTile.setInteger("y", this.pos.y)
            tagTile.setInteger("z", this.pos.z)
            //座標が異なる場合
            if (tagTile != updateTag.copy()) {
                readFromNBT(tagTile)
                markDirty()
            }
        }
    }

    open fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {}

    //    Container    //

    fun openGui(player: EntityPlayer) {
        player.openGui(RagiChemistry.INSTANCE, RCGuiHandler.TileID, world, pos.x, pos.y, pos.z)
    }

}