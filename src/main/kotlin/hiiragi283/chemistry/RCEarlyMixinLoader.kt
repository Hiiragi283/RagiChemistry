package hiiragi283.chemistry

import hiiragi283.chemistry.RCReference
import hiiragi283.chemistry.RagiChemistry
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import zone.rong.mixinbooter.IEarlyMixinLoader

@IFMLLoadingPlugin.Name(RCReference.MOD_NAME)
@IFMLLoadingPlugin.MCVersion("1.12.2")
class RCEarlyMixinLoader : IFMLLoadingPlugin, IEarlyMixinLoader {

    init {
        RagiChemistry.LOGGER.info("Loading Mixin for RagiChemistry...")
    }

    //    IFMLLoadingPlugin    //

    override fun getASMTransformerClass(): Array<String> = arrayOf()

    override fun getModContainerClass(): String? = null

    override fun getSetupClass(): String? = null

    override fun injectData(data: MutableMap<String, Any>?) {}

    override fun getAccessTransformerClass(): String? = null

    //    IEarlyMixinLoader    //

    override fun getMixinConfigs(): MutableList<String> = mutableListOf("mixin.rc_early.json")

}