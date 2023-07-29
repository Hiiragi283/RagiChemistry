package hiiragi283.chemistry.tile

import hiiragi283.chemistry.RCReference
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IInteractionObject

interface RCTileContainer : IInteractionObject {

    override fun getDisplayName() = TextComponentTranslation(this.name)

    override fun getName() = "gui.${RCReference.MOD_ID}.${guiID.split(":")[1]}"

    override fun hasCustomName() = false

}