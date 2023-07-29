package hiiragi283.chemistry

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber(modid = RCReference.MOD_ID)
object RCEventHandler {

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        RCBlocks.register(event.registry)
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        RCBlocks.register(event.registry)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerBlockColorHandler(event: ColorHandlerEvent.Block) {
        RCBlocks.registerColorBlock(event.blockColors)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerItemColorHandler(event: ColorHandlerEvent.Item) {
        RCBlocks.registerColorItem(event.itemColors)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerModel(event: ModelRegistryEvent) {
        RCBlocks.registerModel()
    }

}