@file:JvmName("TileUtil")

package hiiragi283.chemistry.util

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

@Suppress("UNCHECKED_CAST")
fun <T : TileEntity> getTile(world: World, pos: BlockPos): T? = world.getTileEntity(pos) as? T