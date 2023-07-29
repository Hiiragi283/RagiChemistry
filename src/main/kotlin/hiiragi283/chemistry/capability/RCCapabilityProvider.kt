package hiiragi283.chemistry.capability

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.INBTSerializable

/**
 * Thanks to SkyTheory!
 * Source: https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/capability/DataProvider.java
 */

class RCCapabilityProvider<T>(
    private val capability: Capability<T>,
    private val instance: T
) : ICapabilitySerializable<NBTTagCompound> where T : Any, T : INBTSerializable<NBTTagCompound> {

    //    ICapability    //

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability == this.capability

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) this.capability.cast(instance) else null
    }

    //    INBTSerializable<NBTTagCompound>    //

    override fun serializeNBT(): NBTTagCompound = instance.serializeNBT()

    override fun deserializeNBT(nbt: NBTTagCompound) {
        instance.deserializeNBT(nbt)
    }
}