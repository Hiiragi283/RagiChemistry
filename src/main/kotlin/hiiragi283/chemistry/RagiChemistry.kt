package hiiragi283.chemistry

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.network.NetworkRegistry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(
    modid = RCReference.MOD_ID,
    name = RCReference.MOD_NAME,
    version = RCReference.VERSION,
    acceptedMinecraftVersions = "[1.12, 1.12.2]",
    dependencies = "required-after:forgelin_continuous;required-after:ragi_materials@[1.0.0,)",
    modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter"
)
object RagiChemistry {

    val LOGGER: Logger = LogManager.getLogger(RCReference.MOD_NAME)

    //Instanceの宣言
    @Mod.Instance(RCReference.MOD_ID)
    var INSTANCE: RagiChemistry = this

    @Mod.EventHandler
    fun onConstruct(event: FMLConstructionEvent) {
    }

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
    }


    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        RCBlocks.registerOreDict()
        RCBlocks.registerRecipe()
    }

    @Mod.EventHandler
    fun onPostInit(event: FMLPostInitializationEvent) {
    }

    @Mod.EventHandler
    fun onComplete(event: FMLLoadCompleteEvent) {
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, RCGuiHandler)
    }

}