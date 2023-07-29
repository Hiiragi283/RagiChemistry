package hiiragi283.chemistry.capability.item

import hiiragi283.chemistry.capability.CapabilityIO
import hiiragi283.chemistry.capability.IOType
import hiiragi283.chemistry.tile.RCTileBase
import net.minecraft.item.ItemStack
import net.minecraftforge.items.ItemStackHandler

open class RCItemHandler<T : RCTileBase>(slots: Int, val tile: T) : ItemStackHandler(slots), CapabilityIO<RCItemHandler<*>> {

    override fun onContentsChanged(slot: Int) {
        tile.markDirty()
    }

    //    ICapabilityIO    //

    override var ioType: IOType = IOType.INPUT

    override fun getIOType(): IOType = ioType

    override fun setIOType(type: IOType): RCItemHandler<T> = also { ioType = type }

    //    Custom    //

    open fun isEmpty(): Boolean {
        var result = 0
        for (slot in 0 until slots) {
            val stack = getStackInSlot(slot)
            if (stack.isEmpty) result++
        }
        return result == slots
    }

    open fun clear(): Unit = clear(0 until slots)

    open fun clear(range: IntRange) {
        for (slot in range) {
            setStackInSlot(slot, ItemStack.EMPTY)
        }
    }
}