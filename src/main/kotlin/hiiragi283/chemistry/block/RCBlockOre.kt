package hiiragi283.chemistry.block

import hiiragi283.chemistry.api.block.RCBlockBase
import hiiragi283.chemistry.api.item.RCItemBlockBase
import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.util.OreDictUtil
import net.minecraft.block.material.Material

class RCBlockOre(material: Material) : RCBlockBase("ore1", material, -1), HiiragiEntry.BLOCK {

    override val itemBlock: RCItemBlockBase = RCItemBlockBase(this)

    //    HiiragiEntry    //

    override fun registerOreDict() {
        OreDictUtil.register("oreCopper", getObject(), 0)
    }

}