package hiiragi283.chemistry.block

import hiiragi283.chemistry.RCReference
import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.util.OreDictUtil
import net.minecraft.block.Block
import net.minecraft.block.material.Material

class RCBlockOre(material: Material) : Block(material), HiiragiEntry.BLOCK {

    init {
        setRegistryName(RCReference.MOD_ID, "ore1")
    }

    //    HiiragiEntry    //

    override fun registerOreDict() {
        OreDictUtil.register("oreCopper", getObject(), 0)
    }

}