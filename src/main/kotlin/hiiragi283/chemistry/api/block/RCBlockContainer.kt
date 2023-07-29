package hiiragi283.chemistry.api.block

import hiiragi283.chemistry.RCReference
import hiiragi283.chemistry.api.item.RCItemBlockBase
import hiiragi283.chemistry.api.tile.RCTileBase
import hiiragi283.chemistry.util.getTile
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class RCBlockContainer<T : RCTileBase>(id: String, material: Material, val tile: Class<T>, maxTips: Int) :
    RCBlockBase(id, material, maxTips), ITileEntityProvider {

    override val itemBlock: RCItemBlockBase? = RCItemBlockBase(this)

    init {
        GameRegistry.registerTileEntity(tile, ResourceLocation(RCReference.MOD_ID, "te_$id"))
        translationKey = id
    }

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        getTile<RCTileBase>(world, pos)?.onTileRemoved(world, pos, state)
        super.breakBlock(world, pos, state)
    }

    override fun onBlockActivated(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): Boolean {
        if (hand == EnumHand.MAIN_HAND) {
            return getTile<RCTileBase>(world, pos)?.onTileActivated(
                world,
                pos,
                player,
                hand,
                facing
            ) ?: false
        }
        return false
    }

    override fun onBlockPlacedBy(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
        getTile<RCTileBase>(world, pos)?.onTilePlaced(world, pos, state, placer, stack)
        super.onBlockPlacedBy(world, pos, state, placer, stack)
    }

    //    ITileEntityProvider    //

    override fun createNewTileEntity(worldIn: World, meta: Int): T = tile.newInstance()

    abstract class Holdable<T : RCTileBase>(id: String, material: Material, tile: Class<T>, maxTips: Int) :
        RCBlockContainer<T>(id, material, tile, maxTips) {

        //    General    //

        override fun getDrops(
            drops: NonNullList<ItemStack>,
            world: IBlockAccess,
            pos: BlockPos,
            state: IBlockState,
            fortune: Int
        ) {
            val stack = ItemStack(this)
            world.getTileEntity(pos)?.let {
                stack.getOrCreateSubCompound("BlockEntityTag").merge(it.updateTag)
            }
            drops.add(stack)
        }

        /**
         * Reference: net.minecraft.block.BlockFlowerPot
         */

        override fun removedByPlayer(
            state: IBlockState,
            world: World,
            pos: BlockPos,
            player: EntityPlayer,
            willHarvest: Boolean
        ): Boolean {
            //破壊のタイミングを遅らせる
            return if (willHarvest) true else super.removedByPlayer(state, world, pos, player, false)
        }

        override fun harvestBlock(
            world: World,
            player: EntityPlayer,
            pos: BlockPos,
            state: IBlockState,
            te: TileEntity?,
            tool: ItemStack
        ) {
            super.harvestBlock(world, player, pos, state, te, tool)
            world.setBlockToAir(pos)
        }

    }

}